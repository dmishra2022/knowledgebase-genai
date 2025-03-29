package com.dharam.knowledgebase.controller;

import com.dharam.knowledgebase.dto.LoginUserDto;
import com.dharam.knowledgebase.dto.RegisterUserDto;
import com.dharam.knowledgebase.repository.UserRepository;
import com.dharam.knowledgebase.response.LoginResponse;
import com.dharam.knowledgebase.response.MessageResponse;
import com.dharam.knowledgebase.service.AuthenticationService;
import com.dharam.knowledgebase.service.JwtService;
import com.dharam.knowledgebase.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody RegisterUserDto signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUserName())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        return ResponseEntity.ok()
                .body(authenticationService.signup(signUpRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        Authentication authenticatedUser = authenticationService.authenticate(loginUserDto);
        UserDetailsImpl userDetails = (UserDetailsImpl) authenticatedUser.getPrincipal();

        String jwtToken = jwtService.generateToken(userDetails);

        LoginResponse loginResponse = LoginResponse.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();

        return ResponseEntity.ok(loginResponse);
    }
}
