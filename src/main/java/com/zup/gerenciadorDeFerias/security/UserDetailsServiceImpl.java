package com.zup.gerenciadorDeFerias.security;

import com.zup.gerenciadorDeFerias.model.User;
import com.zup.gerenciadorDeFerias.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User usermodel = userRepository.findByUsername(username).orElseThrow(()->
                new UsernameNotFoundException("usuario nao encontrado: "+username));
        return new User(usermodel.getUsername(), usermodel.getPassword(),
                true, true, true,true,);
    }
}
