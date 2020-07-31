package ro.msg.learning.shop.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.*;

@Data
@EqualsAndHashCode(exclude = "orders", callSuper = false)
@ToString(exclude = "orders")
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer extends BaseEntity implements UserDetails {

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String emailAddress;

    @OneToMany
    private List<Order> orders;

    public Customer(String firstName, String lastName, String username, String password, String emailAddress, List<Order> orders) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.orders = orders;
        authorities.add(new SimpleGrantedAuthority("USER"));
    }


    //necessary for security

    @Transient
    private final Set<GrantedAuthority> authorities = new HashSet<>();


    @Transient
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
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
