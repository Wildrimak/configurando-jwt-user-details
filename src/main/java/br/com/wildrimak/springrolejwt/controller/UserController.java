package br.com.wildrimak.springrolejwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.wildrimak.springrolejwt.config.AuthToken;
import br.com.wildrimak.springrolejwt.config.LoginUser;
import br.com.wildrimak.springrolejwt.config.TokenProvider;
import br.com.wildrimak.springrolejwt.dto.UserDto;
import br.com.wildrimak.springrolejwt.model.User;
import br.com.wildrimak.springrolejwt.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException {

	final Authentication authentication = authenticationManager.authenticate(
		new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
	SecurityContextHolder.getContext().setAuthentication(authentication);
	final String token = jwtTokenUtil.generateToken(authentication);
	return ResponseEntity.ok(new AuthToken(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User saveUser(@RequestBody UserDto user) {
	return userService.save(user);
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/userping", method = RequestMethod.GET)
    public String userPing() {
	return "Any User Can Read This";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/adminping", method = RequestMethod.GET)
    public String adminPing() {
	return "Any Admin Can Read This";
    }

}
