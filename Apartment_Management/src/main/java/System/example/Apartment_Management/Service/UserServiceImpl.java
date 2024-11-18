package System.example.Apartment_Management.Service;

import System.example.Apartment_Management.DTO.UserDto;
import System.example.Apartment_Management.Model.Role;
import System.example.Apartment_Management.Model.User;
import System.example.Apartment_Management.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    @Lazy
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserDto userDto) {
        // Mặc định người dùng mới sẽ có vai trò ROLE_USER
        return saveUserWithRole(userDto, "ROLE_USER");
    }

    public User saveAdmin(UserDto userDto) {
        // Người dùng mới với vai trò ADMIN
        return saveUserWithRole(userDto, "ROLE_ADMIN");
    }

    private User saveUserWithRole(UserDto userDto, String role) {
        User user = new User(userDto.getUsername(),
                userDto.getEmail(),
                passwordEncoder.encode(userDto.getPassword()), // Mã hóa mật khẩu
                List.of(new Role(role))); // Đảm bảo Role được định nghĩa chính xác
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Tên người dùng hoặc mật khẩu không hợp lệ.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
