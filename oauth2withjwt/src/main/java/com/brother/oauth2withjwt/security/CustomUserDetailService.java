package com.brother.oauth2withjwt.security;

import com.brother.oauth2withjwt.entity.RoleEntity;
import com.brother.oauth2withjwt.entity.UserEntity;
import com.brother.oauth2withjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    HttpServletRequest request;

    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByUserName(userName)
                .orElseThrow(() -> new BadCredentialsException("Invalid Credentials"));

        String[] roles = userEntity.getRoles().stream().map(RoleEntity::getName).toArray(String[]::new);
        return User.withUsername(userEntity.getUserName())
                .password(userEntity.getPassword())
                .authorities(roles).build();
    }
}
