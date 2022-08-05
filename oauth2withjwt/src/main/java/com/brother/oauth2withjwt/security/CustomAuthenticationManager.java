package com.brother.oauth2withjwt.security;

import com.brother.oauth2withjwt.entity.UserEntity;
import com.brother.oauth2withjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class CustomAuthenticationManager implements AuthenticationManager {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();

        UserEntity userEntity = userRepository.findByUserName(username)
                .orElseThrow(() -> new BadCredentialsException("Invalid Credentials"));

        if (userEntity == null || !passwordEncoder.matches(password, userEntity.getPassword())) {
            throw new BadCredentialsException("Invalid Credentials");
        }

        List<GrantedAuthority> grantedAuths = new ArrayList<>();

        userEntity.getRoles().forEach(roleEntity -> {
            grantedAuths.add(new SimpleGrantedAuthority(roleEntity.getName()));
        });

        return new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
    }
}
