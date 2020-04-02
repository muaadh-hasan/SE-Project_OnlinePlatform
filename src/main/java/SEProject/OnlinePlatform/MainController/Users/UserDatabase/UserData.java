package SEProject.OnlinePlatform.MainController.Users.UserDatabase;

import SEProject.OnlinePlatform.MainController.Users.UserModel.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserData extends CrudRepository<User, Integer > {
    public boolean existsByUsername(String username);
    public boolean existsByPassword(String username);
    public User findById(int id);
    public User findByUsername(String username);
    public User findByPassword(String password);
    public List<User> findAll();
}
