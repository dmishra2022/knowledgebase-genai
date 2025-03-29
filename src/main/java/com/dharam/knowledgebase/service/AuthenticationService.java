package com.dharam.knowledgebase.service;

import com.dharam.knowledgebase.dto.LoginUserDto;
import com.dharam.knowledgebase.dto.RegisterUserDto;
import com.dharam.knowledgebase.model.Role;
import com.dharam.knowledgebase.model.Roles;
import com.dharam.knowledgebase.model.User;
import com.dharam.knowledgebase.repository.RoleRepository;
import com.dharam.knowledgebase.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public User signup(RegisterUserDto input) {
        Set<String> strRoles = input.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(Roles.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(Roles.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(Roles.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(Roles.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        var user = User.builder()
                .username(input.getUserName())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .roles(roles)
                .build();

        return userRepository.save(user);
    }

    public Authentication authenticate(LoginUserDto input) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUserName(),
                        input.getPassword()
                )
        );

    }

    public List<User> allUsers() {

        return new ArrayList<>(userRepository.findAll());
    }
}
