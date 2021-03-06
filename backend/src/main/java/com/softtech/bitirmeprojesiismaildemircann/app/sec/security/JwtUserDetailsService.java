package com.softtech.bitirmeprojesiismaildemircann.app.sec.security;

import com.softtech.bitirmeprojesiismaildemircann.app.usr.entity.UsrUser;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.service.entityservice.UsrUserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UsrUserEntityService usrUserEntityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UsrUser usrUser = usrUserEntityService.findByUsername(username);

        return JwtUserDetails.create(usrUser);
    }

    public UserDetails loadUserByUserId(Long id) {

        UsrUser usrUser = usrUserEntityService.getByIdWithControl(id);

        return JwtUserDetails.create(usrUser);
    }
}
