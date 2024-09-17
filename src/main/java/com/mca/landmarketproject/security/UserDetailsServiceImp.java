//package com.mca.landmarketproject.security;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import com.mca.landmarketproject.dao.UserRepository;
//import com.mca.landmarketproject.model.User;
//
//public class UserDetailsServiceImpl implements UserDetailsService {
//	
//	@Autowired
//	private UserRepository userRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(String value) throws UsernameNotFoundException {
//		Optional<User> user = userRepository.findByEmail(value);
//		
//		if(user.isEmpty()) {
//			throw new UsernameNotFoundException("Could not find user");
//		}
//		return new BrtsUserDetails(user.get());
//	}
//
//}
package com.mca.landmarketproject.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mca.landmarketproject.dao.UserRepository;
import com.mca.landmarketproject.model.User;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String value) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByEmail(value);

		if (user.isEmpty()) {
			throw new UsernameNotFoundException("Could not find user");
		}
		return new LandMarketUserDetails(user.get());
	}
}
