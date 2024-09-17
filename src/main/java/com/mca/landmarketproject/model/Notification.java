package com.mca.landmarketproject.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "is_read")
    private String isRead;

    @Column(name = "property_status")
    private String propertyStatus;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    private User owner;

    // Getters and Setters for all fields

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public String getPropertyStatus() {
        return propertyStatus;
    }

    public void setPropertyStatus(String propertyStatus) {
        this.propertyStatus = propertyStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    // Override equals and hashCode to include 'owner' field

    @Override
    public int hashCode() {
        return Objects.hash(id, isRead, propertyStatus, user, owner);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Notification other = (Notification) obj;
        return Objects.equals(id, other.id) &&
               Objects.equals(isRead, other.isRead) &&
               Objects.equals(propertyStatus, other.propertyStatus) &&
               Objects.equals(user, other.user) &&
               Objects.equals(owner, other.owner);
    }

    // Override toString method

    @Override
    public String toString() {
        return "Notification [id=" + id + ", isRead=" + isRead + ", propertyStatus=" + propertyStatus +
               ", user=" + user + ", owner=" + owner + "]";
    }
}
