package com.softtech.bitirmeprojesiismaildemircann.app.sec.service;

import com.softtech.bitirmeprojesiismaildemircann.app.sec.dto.SecLoginRequestDto;
import com.softtech.bitirmeprojesiismaildemircann.app.sec.enums.EnumJwtConstant;
import com.softtech.bitirmeprojesiismaildemircann.app.sec.security.JwtTokenGenerator;
import com.softtech.bitirmeprojesiismaildemircann.app.sec.security.JwtUserDetails;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.dto.request.UsrUserSaveRequestDto;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.dto.response.UsrUserResponseDto;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.service.UsrUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsrUserService usrUserService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenGenerator jwtTokenGenerator;

    /**
     * @param usrUserSaveRequestDto {username, password, name, surname}
     * @return This method returns the registered user information.
     */
    public UsrUserResponseDto register(UsrUserSaveRequestDto usrUserSaveRequestDto) {

        return usrUserService.saveUser(usrUserSaveRequestDto);
    }

    /**
     * @param secLoginRequestDto {username, password}
     * @return This method returns a token(JWT) for the logged in user.
     */
    public String login(SecLoginRequestDto secLoginRequestDto) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(secLoginRequestDto.getUsername(), secLoginRequestDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenGenerator.generateJwtToken(authentication);

        String bearer = EnumJwtConstant.BEARER.getConstant();

        return bearer + token;
    }

    /**
     * @return This method returns current user's id.
     */
    public Long getCurrentUserId(){

        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();

        Long jwtUserDetailsId = null;
        if (jwtUserDetails != null){
            jwtUserDetailsId = jwtUserDetails.getId();
        }

        return jwtUserDetailsId;
    }

    private JwtUserDetails getCurrentJwtUserDetails() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        JwtUserDetails jwtUserDetails = null;
        if (authentication != null && authentication.getPrincipal() instanceof JwtUserDetails){
            jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        }
        return jwtUserDetails;
    }
}
