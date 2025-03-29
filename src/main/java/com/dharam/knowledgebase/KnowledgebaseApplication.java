package com.dharam.knowledgebase;

import com.dharam.knowledgebase.model.Role;
import com.dharam.knowledgebase.model.Roles;
import com.dharam.knowledgebase.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class KnowledgebaseApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KnowledgebaseApplication.class, args);
	}


	@Autowired
	RoleRepository roleRepository;
	@Override
	public void run(String... args) {
		if (roleRepository.findAll().isEmpty()) {
			roleRepository.saveAll(List.of(
					Role.builder().name(Roles.ROLE_USER).build(),
					Role.builder().name(Roles.ROLE_ADMIN).build(),
					Role.builder().name(Roles.ROLE_MODERATOR).build()
			));
		}
	}
}
