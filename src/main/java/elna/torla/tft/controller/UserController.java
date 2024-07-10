package elna.torla.tft.controller;

import elna.torla.tft.entities.User;
import elna.torla.tft.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path="user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody User user){
        this.userService.createUser(user);
    }

    @PutMapping(path="{id}", consumes = APPLICATION_JSON_VALUE )
    public void updateUser(@PathVariable int id,@RequestBody User user){
        this.userService.updateUser(user,id);
    }

    @GetMapping
    public List<User> getUsers(@RequestParam(required = false) String nickname){
        return this.userService.getUsers(nickname);
    }

    @GetMapping(path="{id}")
    public User getUser(@PathVariable int id){
        return this.userService.getUser(id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable int id){
        this.userService.deleteUser(id);
    }

}
