package rs.ac.uns.ftn.weplayserver.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import rs.ac.uns.ftn.weplayserver.dto.UserDTO;
import rs.ac.uns.ftn.weplayserver.model.User;
import rs.ac.uns.ftn.weplayserver.security.auth.JwtAuthenticationRequest;

import javax.servlet.http.HttpServletRequest;


public interface LoginService extends UserDetailsService {

    User checkCredentials(JwtAuthenticationRequest request);
    UserDTO register(UserDTO userDTO, String fcmid);
    UserDTO login(JwtAuthenticationRequest request, String fcmid);
    void changePassword(String oldPassword, String newPassword, String username) throws Exception;
    UserDTO refreshAuthenticationToken(HttpServletRequest request);


}