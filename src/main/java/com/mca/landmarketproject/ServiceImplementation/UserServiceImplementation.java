package com.mca.landmarketproject.ServiceImplementation;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mca.landmarketproject.dao.RoleRepository;
import com.mca.landmarketproject.dao.UserRepository;
import com.mca.landmarketproject.dto.UserDto;
import com.mca.landmarketproject.exception.LandMarketException;
import com.mca.landmarketproject.model.Role;
import com.mca.landmarketproject.model.User;
import com.mca.landmarketproject.security.SecurityConstants;
import com.mca.landmarketproject.service.UserService;
import com.mca.landmarketproject.util.GoogleOAuthUtil;
import com.mca.landmarketproject.util.UserUtil;
import com.nimbusds.jose.shaded.gson.JsonObject;
import com.nimbusds.jose.shaded.gson.JsonParser;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;

@Service
public class UserServiceImplementation implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public List<UserDto> getAllUser() throws LandMarketException {
        try {
            List<UserDto> listOfDto = userRepository.findAll().stream()
                    .map(UserUtil::convertUserEntityToDto)
                    .collect(Collectors.toList());
            return listOfDto;
        } catch (Exception exception) {
            throw new LandMarketException(exception.getLocalizedMessage());
        }
    }

    @Override
    public User getUserById(String email) throws LandMarketException {
        try {
            User user = userRepository.findByEmail(email).orElseThrow(() -> new LandMarketException("User not Found"));
            return user;
        } catch (Exception exception) {
            throw new LandMarketException(exception.getLocalizedMessage());
        }
    }

    @Override
    public String addNewUser(UserDto userDto) throws LandMarketException {
        try {
            User user = UserUtil.convertUserDtoToEntity(userDto);

            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setCreateAt(LocalDateTime.now());
            user.setLastModifiedAt(LocalDateTime.now());
            user.setIsEmailVarified(Boolean.FALSE);
            user.setIsPhoneNumberVarified(Boolean.FALSE);
            user.setEnabled(Boolean.TRUE);

            Role userRole = roleRepository.findByName("USER");
            user.getRoles().add(userRole);

            user = userRepository.save(user);
            return "User registered successfully";
        } catch (DataIntegrityViolationException exception) {
            logger.error(exception.getLocalizedMessage());
            throw new LandMarketException("User data has already been registered. " + exception.getLocalizedMessage());
        }
    }

    @Transactional
    @Override
    public String updateUser(Integer userId, UserDto dto) throws LandMarketException {
        User user = UserUtil.convertUserDtoToEntity(dto);
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new LandMarketException("User not Found"));

        // Update Email
        if (user.getEmail() != null && !user.getEmail().isEmpty()
                && !Objects.equals(user.getEmail(), existingUser.getEmail())) {
            existingUser.setEmail(user.getEmail());
        }

        // Update PhoneNumber
        if (user.getPhoneNumber() != null && !user.getPhoneNumber().isEmpty()
                && !Objects.equals(user.getPhoneNumber(), existingUser.getPhoneNumber())) {
            existingUser.setPhoneNumber(user.getPhoneNumber());
        }
        // Update FirstName
        if (user.getFirstName() != null && !user.getFirstName().isEmpty()
                && !Objects.equals(user.getFirstName(), existingUser.getFirstName())) {
            existingUser.setFirstName(user.getFirstName());
        }
        // Update LastName
        if (user.getLastName() != null && !user.getLastName().isEmpty()
                && !Objects.equals(user.getLastName(), existingUser.getLastName())) {
            existingUser.setLastName(user.getLastName());
        }

        userRepository.save(existingUser); // Save updated user
        return "User data updated successfully";
    }

    @Override
    public String deleteUser(Integer userId) throws LandMarketException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.deleteById(userId);
            String name = user.get().getFirstName();
            return "User " + name + " is deleted successfully.";
        } else {
            throw new LandMarketException("User doesn't exist.");
        }
    }

    @Override
    public String signIn(UserDto userDto) {
        String jwt = null;
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
        if (null != authentication) {
			User user = userRepository.findByEmail(userDto.getEmail()).get();
			SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
			jwt = Jwts.builder().setIssuer("AGRO_GENIUS").setSubject("JWT Token")
					.claim("userId", user.getId())
					.claim("username", authentication.getName()).claim("email", userDto.getEmail())
					.claim("authorities", populateAuthorities(authentication.getAuthorities())).setIssuedAt(new Date())
					.setExpiration(new Date((new Date()).getTime() + 30000000)).signWith(key).compact();
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
        return jwt;
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }

    @Override
    public String signInWithGoogle(String accessToken) {
        UserDto userDto = getUserDtoFromGoogleAccessToken(accessToken);
        Optional<User> userFromDB = userRepository.findByEmail(userDto.getEmail());
        if (userFromDB.isEmpty()) {
            try {
                this.addNewUser(userDto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return signIn(userDto);
    }

    private UserDto getUserDtoFromGoogleAccessToken(String accessToken) {
		UserDto usertDto = new UserDto();
		JsonObject jsonObject = GoogleOAuthUtil.getProfileDetailsGoogle(accessToken);
		return populateUserDetailsFromGoogleResponse(usertDto, jsonObject);

	}

    private UserDto populateUserDetailsFromGoogleResponse(UserDto userDto, JsonObject jsonObject) {
        userDto.setFirstName(jsonObject.get("given_name").getAsString());
        userDto.setLastName(jsonObject.get("family_name").getAsString());
        Double phone = Math.random();
        userDto.setPhoneNumber(phone.toString().substring(2, 12) + "_RANDOM");
        userDto.setEmail(jsonObject.get("email").getAsString());
        userDto.setPassword(userDto.getEmail());
        userDto.setIsGoogleUser(Boolean.TRUE);
        return userDto;
    }

    @Override
    public Optional<User> findById(Integer userId) {
        return userRepository.findById(userId);
    }
}
