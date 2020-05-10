package com.javaee.project.auth;

import com.javaee.project.project3.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String identificator) throws UsernameNotFoundException {
        System.out.println(identificator);
        com.javaee.project.project3.model.User user=userRepo.findByUsernameOrEmail(identificator,identificator)
                .orElseThrow(()->new UsernameNotFoundException("no user with this username "+identificator));
        System.out.println(user.getUsername() + " Hello");
        return new UserDetailsImpl(user);
    }
}
