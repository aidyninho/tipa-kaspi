package kz.aidyninho.tipakaspi.api;

import kz.aidyninho.tipakaspi.dto.UserDto;
import kz.aidyninho.tipakaspi.model.User;
import kz.aidyninho.tipakaspi.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public UserDto save(@RequestBody User user) {
        return userService.save(user);
    }
}
