package com.identify.identify_service.config;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.identify.identify_service.entity.User;
import com.identify.identify_service.enums.Role;
import com.identify.identify_service.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationinitConfig {


    
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository){
        return args -> {
            if(userRepository.findByUsername("admin").isEmpty()){
                var roles =  new HashSet<String>();
                roles.add(Role.ADMIN.name());
                User user = User.builder()
                            .username("admin")
                            .password(passwordEncoder.encode("admin"))
                            .roles(roles)
                            .build();

                userRepository.save(user);
                log.warn("admin user has been created with username: admin and password: admin");
            }
        };
    }
}
