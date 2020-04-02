package SEProject.OnlinePlatform.MainController.Users;

import SEProject.OnlinePlatform.MainController.Users.UserDatabase.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignIn {
    @Autowired
    UserData Data;

    public boolean isFindUser(String username , String password){
        if (Data.existsByUsername(username) && Data.existsByPassword(password))
            return true;
        else
            return false;
    }
}
