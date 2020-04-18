package SEProject.OnlinePlatform.MainController.Users.UserController;

import SEProject.OnlinePlatform.MainController.Users.SignIn;
import SEProject.OnlinePlatform.MainController.Users.SignUp;
import SEProject.OnlinePlatform.MainController.Users.UserModel.User;
import SEProject.OnlinePlatform.MainController.Users.UserDatabase.UserData;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
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
    User user2 ;
    @PostMapping("/signup/{id}/{username}/{password}/{type}")
    public String signup(@PathVariable("id") int id, @PathVariable("username") String username , @PathVariable("password") String password, @PathVariable("type") String type){
        user2.setId(id);user2.setUsername(username); user2.setPassword(password);user2.setType(type);
        if(type.equals("Admin")){
            user2.setActive(true);
        }
        else{
            user2.setActive(false);
        }
        boolean check  = Up.addUser(user2);
//        Data.save(user);
        if (check){
            //activeUser = user.id;
            return "Your  signUp Request send successfully ^_^ ";
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
        User user1 ;
        user1 = Data.findByUsername(username);
        if (check && user1.getActive()==true){
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
            List<User> all = new ArrayList();
            List<User> accepted = new ArrayList();
            all= Data.findAll();
            for (int i=0 ; i<all.size();i++){
                if(all.get(i).getActive()==true){
                    accepted.add(all.get(i));
                }
            }
            return accepted ;

        }else {
            List<User> l = new ArrayList();
            User user2 = new User(404 , "Error" , "Error" , "You Can't show list !! should be Admin", false);
            l.add(user2);
            l.add(user1);
            return l;
        }
    }

    @RequestMapping("/acceptUser/{username}")
    public  String acceptUser(@PathVariable("username") String username){
        User user ;
        User isAdmin ;
        isAdmin = Data.findById(activeUser);
        user = Data.findByUsername(username);
        if((!user.getType().contentEquals("Admin")) && user.getActive()==false && isAdmin.getType().contentEquals("Admin")){
            user.setActive(true);
            Data.save(user);
            return "Accepted";
        }
        else
            return "Error" ;

    }


}
