package com.sparta.bt.springmvc;

import com.sparta.bt.springmvc.entities.UserEntity;
import com.sparta.bt.springmvc.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringMvcApplication {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner demo(UserRepository userRepository){
//        return(args -> {
//            userRepository.save(new UserEntity("admin",encoder.encode("password"),"ADMIN",1));
//            userRepository.save(new UserEntity("user",encoder.encode("password"),"USER",1));
//        });
//    }
}
