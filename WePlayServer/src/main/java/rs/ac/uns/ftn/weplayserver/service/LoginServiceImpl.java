package rs.ac.uns.ftn.weplayserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import rs.ac.uns.ftn.weplayserver.dto.UserDTO;
import rs.ac.uns.ftn.weplayserver.model.Authority;
import rs.ac.uns.ftn.weplayserver.model.User;
import rs.ac.uns.ftn.weplayserver.repository.AuthorityRepository;
import rs.ac.uns.ftn.weplayserver.repository.UserRepository;
import rs.ac.uns.ftn.weplayserver.security.TokenUtils;
import rs.ac.uns.ftn.weplayserver.security.auth.JwtAuthenticationRequest;
import rs.ac.uns.ftn.weplayserver.utils.ObjectMapperUtils;

import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoginServiceImpl implements  LoginService{

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RestTemplate restTemplate;

    Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);


    @Override
    public User checkCredentials(JwtAuthenticationRequest request) {
        User user=userRepository.findByEmail(request.getUsername());
        if(user!=null){
            if(passwordEncoder.matches(request.getPassword(),user.getPassword())){
                return user;
            }
        }
        return null;
    }



    public UserDTO register(UserDTO userDTO) {
        User user = new User();

        if(userRepository.findByEmail(userDTO.getEmail()) != null){
            userDTO.setId(null);
            return userDTO;
        }

        user.setLastName(userDTO.getLastName());
        user.setEnabled(true);
        user.setFirstName(userDTO.getFirstName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        Authority authority = authorityRepository.findOneByName("ROLE_USER");
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authority);
        user.setAuthorities(authorities);
        user.setGamingSkill(userDTO.getGamingSkill());

        userRepository.save(user);
        userDTO.setId(user.getId());

        return userDTO;
    }

    @Override
    public UserDTO login(JwtAuthenticationRequest request) {
        User user=userRepository.findByEmail(request.getUsername());
        if(user!=null){
            if(passwordEncoder.matches(request.getPassword(),user.getPassword())){
                String jwt = tokenUtils.generateToken(request.getUsername());
                int expiresIn = tokenUtils.getExpiredIn();
                UserDTO userDTO= ObjectMapperUtils.map(user,UserDTO.class);
                userDTO.setExpiresIn(expiresIn);
                userDTO.setToken(jwt);
                // Vrati user-a sa tokenom kao odgovor na uspesnu autentifikaciju
                return userDTO;
            }
        }
        return null;
    }

    @Override
    public void changePassword(String oldPassword, String newPassword, String username) throws Exception{
        User user= userRepository.findByEmail(username);
        if(passwordEncoder.matches(oldPassword,user.getPassword())){
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        }else{
            throw new Exception();
        }
    }

    @Override
    public UserDTO refreshAuthenticationToken(HttpServletRequest request){
        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getEmailFromToken(token);
        User user = (User) userRepository.findByEmail(username);
        if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = tokenUtils.refreshToken(token);
            int expiresIn = tokenUtils.getExpiredIn();
            UserDTO userDTO= ObjectMapperUtils.map(user,UserDTO.class);
            userDTO.setExpiresIn(expiresIn);
            userDTO.setToken(refreshedToken);
            return userDTO;
        } else {
            return null;
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return user;
        }
    }




}