package SEProject.OnlinePlatform.MainController.Service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
//import org.websparrow.entity.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import SEProject.OnlinePlatform.MainController.Users.UserModel.User;

public class MyUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    private int id;
    private String username;
    private String password;
//    private String type;
    private boolean isActive;
    private List<GrantedAuthority> authorities;

    public MyUserDetails(User user) {
        this.username = user.getUserName();
        this.password = user.getPassword();
//        this.type = user.getType();
        this.isActive = user.isActive();
        this.authorities = Arrays.stream(user.getType().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public MyUserDetails() {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() { return username; }

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
        return isActive;
    }

}
