package ru.innopolis.my.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.innopolis.my.entity.Role;
import ru.innopolis.my.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class    UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository uR;

    private static Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ru.innopolis.my.entity.User user = null;
        user = uR.findByLogin(username.trim());
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь с таким логином не найден");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        String fio = user.getSurname() + " " + user.getName();
        User securityUser = new User(fio.trim(), user.getPassword(), true, true, true, true, authorities);

        return securityUser;
    }
}