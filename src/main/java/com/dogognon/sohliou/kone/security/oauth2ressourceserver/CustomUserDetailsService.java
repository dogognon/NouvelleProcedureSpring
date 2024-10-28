package com.dogognon.sohliou.kone.security.oauth2ressourceserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dogognon.sohliou.kone.security.data.User;
import com.dogognon.sohliou.kone.security.data.UserRepository;
import com.dogognon.sohliou.kone.security.exception.ResourceNotFoundException;

/**
 * 
 * @author m17405
 * 
 * @apiNote Cet service peremt d'injecter les autres informations sur l'utilisateur connecté
 *
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {
        // Let people login with either phone or email
    	System.err.println(login);
        User user = userRepository.findByPhoneOrEmail(login, login).orElseThrow(() -> new UsernameNotFoundException("User not found with Phone or email : " + login)
        );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(String id) throws ResourceNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        return UserPrincipal.create(user);
    }
}