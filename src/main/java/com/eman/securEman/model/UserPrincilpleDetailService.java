package com.eman.securEman.model;

import com.eman.securEman.db.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * Created by Mohammad on 9/6/2020.
 */
@Service
public class UserPrincilpleDetailService implements UserDetailsService {
    private UserRepository userRepository;

    public UserPrincilpleDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user=userRepository.findUserByUsername(s);
        UserPrinciple userPrincipal=new UserPrinciple(user);
        return userPrincipal;
    }
}
