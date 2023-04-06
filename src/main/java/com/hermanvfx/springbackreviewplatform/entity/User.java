package com.hermanvfx.springbackreviewplatform.entity;

import com.hermanvfx.springbackreviewplatform.entity.enums.Speciality;
import com.hermanvfx.springbackreviewplatform.security.token.Token;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "uzr")
@Entity
public class User implements UserDetails {
    @Id
    @Column(name = "uzr_id", nullable = false)
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "uzr_first_name", nullable = false)
    private String firstName;

    @Column(name = "uzr_last_name")
    private String lastName;

    @Column(name = "uzr_email", nullable = false)
    private String email;

    @Column(name = "uzr_avatar")
    private String avatar;

    @Column(name = "uzr_password", nullable = false)
    private String password;

    @ManyToMany(cascade = {CascadeType.MERGE})
    private Set<Role> roles = new HashSet<>();

    @OneToMany(
            cascade = {CascadeType.MERGE},
            mappedBy = "user"
    )
    private List<Token> tokens;

    @Enumerated(EnumType.STRING)
    @Column(name = "uzr_specialities")
    private Speciality specialities;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Review> reviewsReceiving;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Review> reviewsStudent;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Social> socials;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Commentary> commentaries;

    @Column(name = "create_time", nullable = false)
    private LocalDate create;
    @Column(name = "update_time")
    private LocalDate update;
    @Column(name = "delete_time")
    private LocalDate delete;
    @Column(
            name = "is_active",
            nullable = false
    )
    private boolean isActive = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getUsername() {
        return email;
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
        return true;
    }
}
