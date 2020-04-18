package SEProject.OnlinePlatform.MainController.Service;


import java.util.Optional;

import SEProject.OnlinePlatform.MainController.Users.UserDatabase.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import SEProject.OnlinePlatform.MainController.Users.UserModel.User;


@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    UserData userData;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userData.findByUsername(username);

        user.orElseThrow(() -> new UsernameNotFoundException(username + " not found."));

        return user.map(MyUserDetails::new).get();
    }

}
