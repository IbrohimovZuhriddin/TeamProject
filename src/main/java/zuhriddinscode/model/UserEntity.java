package zuhriddinscode.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_entity")
public class UserEntity  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Integer userid;

    @Column(name = "user_name",unique = true)
    private String  userName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastname;

//    @Column(name = "email")
//    private String userEmail;

    @Column(name = "birthday")
    private LocalDate birthday;


    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    //    private Integer RoleId;
    private Boolean isActive;
    private String userPassword;

    @Column(name = "email", unique = true, nullable = false)
    private String email;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
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