package SEProject.OnlinePlatform.MainController.Users.UserController;

import SEProject.OnlinePlatform.MainController.Users.SignIn;
import SEProject.OnlinePlatform.MainController.Users.SignUp;
import SEProject.OnlinePlatform.MainController.Users.UserModel.User;
import SEProject.OnlinePlatform.MainController.Users.UserDatabase.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    SignUp Up;
    @Autowired
    SignIn In;
    @Autowired
    UserData Data;
    public int activeUser = -1;

    @PostMapping("/signup")
    public String signup(@RequestBody User user){
//        user.setId(5);
//        user.setUsername("Doaa");
//        user.setPassword("12322321");
//        user.setType("normal");
        boolean check  = Up.addUser(user);
//        Data.save(user);
        if (check){
            activeUser = user.id;
            return "You are signUp and login successfully ^_^ ";
        }else
            return "Error !!";
//        return user;
    }

    @GetMapping("/signin/{username}/{password}")
    public String signin(@PathVariable("username") String username , @PathVariable("password") String password){
        boolean check = In.isFindUser(username,password);
//        if (Data.existsByUsername(username) && Data.existsByPassword(password))
//            check =  true;
//        else
//            check = false;
        User user;
        if (check){
            user = Data.findByPassword(password);
            activeUser = user.getId();
            return "You are sign In successfully ^_^ ";
        }
        return "Error !!";
    }

    @RequestMapping("/listUsers")
    public  List<User> listUsers(){
        User user1 ;
        user1 = Data.findById(activeUser);
//        return user1;

        if (user1.getType().contentEquals("Admin")){
            return Data.findAll();
        }else {
            List<User> l = new ArrayList();
            User user2 = new User(404 , "Error" , "Error" , "You Can't show list !! should be Admin");
            l.add(user2);
            l.add(user1);
            return l;
        }
    }



}
