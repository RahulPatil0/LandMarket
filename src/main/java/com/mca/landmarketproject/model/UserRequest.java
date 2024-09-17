package com.mca.landmarketproject.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_requests")
public class UserRequest {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "message", length = 1000)
    private String message;

    @Column(name = "status")
    private String status;

    @Column(name = "request_date")
    private LocalDateTime requestDate;

    @ManyToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, email, message, status, requestDate);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserRequest other = (UserRequest) obj;
        return Objects.equals(id, other.id) && Objects.equals(name, other.name)
                && Objects.equals(phone, other.phone) && Objects.equals(email, other.email)
                && Objects.equals(message, other.message) && Objects.equals(status, other.status)
                && Objects.equals(requestDate, other.requestDate);
    }

    @Override
    public String toString() {
        return "Request [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email
                + ", message=" + message + ", status=" + status + ", requestDate=" + requestDate + "]";
    }
}
