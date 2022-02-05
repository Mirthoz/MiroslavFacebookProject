package com.example.miroslavfacebookproject.service.implementation;

import com.example.miroslavfacebookproject.component.Constants;
import com.example.miroslavfacebookproject.dto.UserDTO;
import com.example.miroslavfacebookproject.entity.Avatar;
import com.example.miroslavfacebookproject.repository.AvatarRepository;
import com.example.miroslavfacebookproject.repository.UserRepository;
import com.example.miroslavfacebookproject.dto.RegisterDTO;
import com.example.miroslavfacebookproject.entity.Role;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.service.contract.RoleService;
import com.example.miroslavfacebookproject.service.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AvatarRepository avatarRepository;
    private final Constants constants;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleService roleService,
                           BCryptPasswordEncoder passwordEncoder,
                           AvatarRepository avatarRepository,
                           Constants constants) {

        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.avatarRepository = avatarRepository;
        this.constants = constants;
    }

    @Override
    public User registration(RegisterDTO registerDTO) {
        if (Integer.parseInt(registerDTO.getAge()) <= constants.MIN_AGE)
            throw new IllegalArgumentException("User must be over 14 years old");
        if (registerDTO.getPasswordRepeat() == null || !registerDTO.getPassword().equals(registerDTO.getPasswordRepeat()))
            throw new IllegalArgumentException("Password do not match");

        User user = new User();
        Avatar avatar = new Avatar();
        avatarRepository.save(avatar);
        user.setAvatar(avatar);
        user.setEmail(registerDTO.getEmail());
        user.setUsername(registerDTO.getUsername());
        user.setSurname(registerDTO.getSurname());
        user.setAge(registerDTO.getAge());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        user.setEmailPrivacy(definePrivacy(registerDTO.isEmailPrivacy()));
        user.setAgePrivacy(definePrivacy(registerDTO.isAgePrivacy()));
        user.setPostsAndImagesPrivacy(definePrivacy(registerDTO.isPostsAndImagesPrivacy()));

        Set<Role> roles = new HashSet<>();
        roles.add(roleService.takeUserRole());
        user.setRoles(roles);
        userRepository.save(user);
        return user;
    }

    private String definePrivacy(boolean privacyValue) {
        String privacy;
        if (privacyValue) {
            privacy = "FOR_ALL";
        } else {
            privacy = "JUST_ME";
        }
        return privacy;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findFirstByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found; with email: " + email));
        return user;
    }

    @Override
    public User takeUserByUserName(String username) throws UsernameNotFoundException {
        return userRepository.findFirstByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found; with username: " + username));
    }

    public void changeUserInformation(UserDTO userDTO, UserDetails userDetails) {
        com.example.miroslavfacebookproject.entity.User user = takeUserByUserName(userDetails.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setAge(userDTO.getAge());
        user.setUsername(userDTO.getUsername());
        user.setSurname(userDTO.getSurname());

        user.setEmailPrivacy(definePrivacy(userDTO.isEmailPrivacy()));
        user.setAgePrivacy(definePrivacy(userDTO.isAgePrivacy()));
        user.setPostsAndImagesPrivacy(definePrivacy(userDTO.isPostsAndImagesPrivacy()));
        userRepository.save(user);
    }

    public void resetUserPassword(String email, String password, String passwordRepeat) {
        if (password.equals(passwordRepeat) && userRepository.findByEmail(email) != null) {
            User user = userRepository.findByEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
            RegisterDTO registerDTO = new RegisterDTO();
            registerDTO.setEmail(user.getEmail());
            registerDTO.setPassword(user.getPassword());
        }
    }

    public List<UserDTO> findByName(String name) {
        List<User> users = userRepository.findByUsernameContainingIgnoreCase(name.toLowerCase(Locale.ROOT));
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            userDTOS.add(mapUserToUserDTO(user));
        }
        return userDTOS;
    }

    private UserDTO mapUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setSurname(user.getSurname());
        return userDTO;
    }
}
