package rs.ac.uns.ftn.weplayserver.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import rs.ac.uns.ftn.weplayserver.dto.UserDTO;
import rs.ac.uns.ftn.weplayserver.model.User;
import rs.ac.uns.ftn.weplayserver.security.auth.JwtAuthenticationRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.GeneralSecurityException;


public interface LoginService extends UserDetailsService {

    User checkCredentials(JwtAuthenticationRequest request);
    UserDTO register(UserDTO userDTO);
    UserDTO login(JwtAuthenticationRequest request);
    void changePassword(String oldPassword, String newPassword, String username) throws Exception;
    UserDTO refreshAuthenticationToken(HttpServletRequest request);


}