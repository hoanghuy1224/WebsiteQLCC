package System.example.Apartment_Management.Service;

import System.example.Apartment_Management.DTO.UserDto;
import System.example.Apartment_Management.Model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserDto userDto);

    User saveAdmin(UserDto userDto);
}
