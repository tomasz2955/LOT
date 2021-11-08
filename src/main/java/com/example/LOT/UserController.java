package com.example.LOT;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping
public class UserController {

    private final UserService userService;

    //@Autowired adnotacja nie potrzebna, jak wstrzykujesz przez konstruktor to spring to sam ogarnia
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<CreateUserDto> getUsers() {
        return userService.getUserList();
    }


    @PostMapping("/create/{user}") // musimy pogadać o koncepcji DTO https://www.baeldung.com/java-dto-pattern
    User createUser(@RequestBody CreateUserDto createUserDto) {
        return userService.createUser(createUserDto);
    }

}
