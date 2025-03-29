package com.dharam.knowledgebase.service;

import com.dharam.knowledgebase.model.User;
import com.dharam.knowledgebase.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> allUsers() {

        return new ArrayList<>(userRepository.findAll());
    }
}
