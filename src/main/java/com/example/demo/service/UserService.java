package com.example.demo.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UserRepository;
import com.example.demo.user.User;

@Service
public class UserService implements UserDetailsService
{
private final UserRepository userRepository;

private final BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();


public UserService(UserRepository userRepository)
{
	this.userRepository=userRepository;
}



public User registerUser(User user)
{
	user.setPassword(passwordEncoder.encode(user.getPassword()));
	
	return userRepository.save(user);
}

public User loginUser(User user)
{
    System.out.println("LOGIN EMAIL RECEIVED: [" + user.getEmail() + "]");

    Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

    System.out.println("USER FOUND: " + existingUser.isPresent());

    if(existingUser.isPresent())
    {
        User foundUser=existingUser.get();

        System.out.println("Email Found: " + foundUser.getEmail());

        System.out.println("Password Match: ");

        if(passwordEncoder.matches(
                user.getPassword(),
                foundUser.getPassword()))
        {
            return foundUser;
        }
    }

    throw new RuntimeException("Invalid email or password");
}

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
{
	Optional<User> existingUser=userRepository.findByEmail(username);
	
	if(existingUser.isEmpty())
	{
		throw new UsernameNotFoundException("User not found");
		
	}
	
	User user=existingUser.get();
	
	return org.springframework.security.core.userdetails.User
			.withUsername(user.getEmail())
			.password(user.getPassword())
			.roles(user.getRole())
			.build();
}

}
