package rs.ac.uns.ftn.weplayserver.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.weplayserver.dto.UserDTO;
import rs.ac.uns.ftn.weplayserver.security.TokenUtils;
import rs.ac.uns.ftn.weplayserver.security.auth.JwtAuthenticationRequest;
import rs.ac.uns.ftn.weplayserver.service.LoginServiceImpl;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    LoginServiceImpl loginService;


    @RequestMapping(value = "/login/{fcmid}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, @PathVariable String fcmid) throws AuthenticationException, IOException {
        UserDTO user=loginService.login(authenticationRequest,fcmid);
        if(user!=null){
            // Vrati user-a sa tokenom kao odgovor na uspesno autentifikaciju
            return ResponseEntity.ok(user);
        }else{
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/register/{fcmid}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO, @PathVariable String fcmid) {
        UserDTO u = loginService.register(userDTO, fcmid);
        return new ResponseEntity<>(u, HttpStatus.CREATED);
    }


}