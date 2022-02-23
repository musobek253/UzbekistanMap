package uz.map.uzbekistanmap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.map.uzbekistanmap.dto.ApiResponse;
import uz.map.uzbekistanmap.dto.UserDTO;
import uz.map.uzbekistanmap.model.UserS;
import uz.map.uzbekistanmap.repositary.UserSRepositary;
import uz.map.uzbekistanmap.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserCantroller {

    private final UserSRepositary userSRepositary;
    private final UserService userService;
    @Autowired
    public UserCantroller(UserSRepositary userSRepositary, UserService userService) {
        this.userSRepositary = userSRepositary;
        this.userService = userService;
    }
    @PostMapping("/add")
    public ApiResponse addUsers(@RequestBody UserDTO userDTO){
        return userService.addUsers(userDTO);
    }
    @GetMapping("/all")
    public List<UserS> getUsers(){
        return userSRepositary.findAll();
    }
}
