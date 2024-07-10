package elna.torla.tft.service;

import elna.torla.tft.entities.User;
import elna.torla.tft.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(int id) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public List<User> getUsers(String nickname) {
        if (nickname== null || nickname.isEmpty())
            return this.userRepository.findAll();
        else
            return this.userRepository.findByNicknameContaining(nickname);
    }

    public void createUser(User user){
        if (user.getEmail().isEmpty() || user.getEmail()==null)  {
            // erreur à gérer
        } else if (user.getLastname().isEmpty() || user.getLastname()==null) {
            // erreur à gérer
        } else if (user.getFirstname().isEmpty() || user.getFirstname()==null) {
            // erreur à gérer
        } else if (user.getNickname().isEmpty() || user.getNickname()==null) {
            // erreur à gérer
        } else if (user.getPassword().isEmpty() || user.getPassword() == null) {
            // erreur à gérer
        } else {
            User emailInDb = this.userRepository.findByEmail(user.getEmail());
            if (emailInDb!=null){
                // erreur à gérer
            } else {
                User nicknameInDb = this.userRepository.findByNickname(user.getNickname());
                if (nicknameInDb!=null){
                    // erreur à gérer
                } else {
                    this.userRepository.save(user);
                }
            }
        }
    }

    public void updateUser (User user, int id){
        User userAUpdate = this.getUser(id);
        if (userAUpdate.getId() == user.getId()) {
            if (userAUpdate.getEmail().equals(user.getEmail())) {
                if (user.getLastname().isEmpty() || user.getLastname() == null) {
                    // erreur à gérer
                } else if (user.getFirstname().isEmpty() || user.getFirstname() == null) {
                    // erreur à gérer
                } else if (user.getNickname().isEmpty() || user.getNickname() == null) {
                    // erreur à gérer
                } else if (user.getPassword().isEmpty() || user.getPassword() == null) {
                    // erreur à gérer
                } else {
                    User userInDb = this.userRepository.findByNickname(user.getNickname());
                    if (userInDb != null && userInDb.getId() != user.getId()) {
                        // erreur à gérer
                    } else {
                        userAUpdate.setFirstname(user.getFirstname());
                        userAUpdate.setLastname(user.getLastname());
                        userAUpdate.setNickname(user.getNickname());
                        userAUpdate.setPassword(user.getPassword());
                        this.userRepository.save(userAUpdate);
                    }
                }
            } else {
                //erreur à gérer
            }
        } else {
            //erreur à gérer
        }
    }

    public void deleteUser (int id){
        if(this.userRepository.existsById(id)){
            this.userRepository.deleteById(id);
        } else {
            //erreur à gérer
        }
    }
}
