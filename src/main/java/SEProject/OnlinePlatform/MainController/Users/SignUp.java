package SEProject.OnlinePlatform.MainController.Users;

import SEProject.OnlinePlatform.MainController.Users.UserDatabase.UserData;
import SEProject.OnlinePlatform.MainController.Users.UserModel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUp {
    @Autowired
    UserData Data;

    public boolean addUser(User user){

        if (!Data.existsById(user.getId()) && !Data.existsByPassword(user.getPassword())){
            Data.save(user);
            return true;
        }
        else {
            return false;
        }

    }

}
