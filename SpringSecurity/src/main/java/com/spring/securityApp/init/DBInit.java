package com.spring.securityApp.init;

import com.spring.securityApp.model.RoleEntity;
import com.spring.securityApp.model.UserEntity;
import com.spring.securityApp.model.enums.RoleName;
import com.spring.securityApp.repository.RoleRepository;
import com.spring.securityApp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBInit implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DBInit(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        RoleEntity adminRole = roleRepository.save(new RoleEntity().setRole(RoleName.ADMIN));
        RoleEntity userRole = roleRepository.save(new RoleEntity().setRole(RoleName.USER));


        UserEntity admin = new UserEntity();
        admin
                .setUsername("pesho")
                .setPassword(passwordEncoder.encode("1234"))
                .setRoles(List.of(adminRole,userRole));

        UserEntity user = new UserEntity();
        user
                .setUsername("gosho")
                .setPassword(passwordEncoder.encode("1234"))
                .setRoles(List.of(userRole));

        userRepository.save(admin);
        System.out.println("SAVE ADMIN");
        userRepository.save(user);
        System.out.println("SAVE USER");


    }
}
