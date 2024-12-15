package com.Tework123.Myknowledge.entities;


import com.Tework123.Myknowledge.entities.enums.Access;
import com.Tework123.Myknowledge.entities.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @Size(min = 3, max = 30, message = "3 to 30")
    private String username;

    @Size(min = 3, max = 30, message = "3 to 30")
    private String name;

    @Size(min = 3, max = 30, message = "3 to 30")
    private String surname;

    @Column(unique = true)
    @Size(min = 3, max = 30, message = "3 to 30")
    private String email;

    @Column(length = 1000)
    @Size(min = 4, max = 1000, message = "min 4")
    private String password;

    private LocalDateTime dateJoined;

    @PrePersist
    private void init() {
        dateJoined = LocalDateTime.now();
    }

    private LocalDateTime dateLastEnter;

    private String information;

    private String avatar;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Book> books = new HashSet<>();

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true,
            fetch = FetchType.LAZY, mappedBy = "user1")
    private Set<Relationship> relationships;
//    maybe else one must be

    @Enumerated(EnumType.STRING)
    private Access access;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    private boolean active;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.roles.add(role);
    }

    //        must be for login else 403
    public User() {

    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}