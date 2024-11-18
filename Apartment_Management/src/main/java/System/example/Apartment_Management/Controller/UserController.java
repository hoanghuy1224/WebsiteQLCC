package System.example.Apartment_Management.Controller;

import System.example.Apartment_Management.DTO.UserDto;
import System.example.Apartment_Management.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@Controller
@RequestMapping("/registration")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserDto userDto() {
        return new UserDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserDto userDto) {
        if (userDto.getRoles() != null && userDto.getRoles().contains("ROLE_ADMIN")) {
            userService.saveAdmin(userDto); // Lưu với ROLE_ADMIN
        } else {
            userDto.setRoles(Collections.singletonList("ROLE_USER")); // Mặc định là ROLE_USER
            userService.save(userDto); // Lưu với ROLE_USER
        }
        return "redirect:/registration?success";
    }
}
