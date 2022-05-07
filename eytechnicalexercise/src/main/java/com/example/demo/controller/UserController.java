package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Usuario;
import com.example.demo.exceptions.InvalidPasswordException;
import com.example.demo.exceptions.MailDomainInvalidException;
import com.example.demo.exceptions.MailDuplicatedException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repository.iUserRepo;
import com.example.demo.security.Constants;

@RestController
public class UserController {
	
	private static final Pattern emailPattern = Pattern.compile(Constants.PATTERN_EMAIL);
	private static final Pattern passPattern = Pattern.compile(Constants.PATTERN_PASSWORD);

	private iUserRepo userRepo;

	@Autowired
	UserController(iUserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@PostMapping("/users")
	public ResponseEntity<Usuario> save(@Valid @RequestBody Usuario user) throws InvalidPasswordException {			
		Matcher emailMatch = emailPattern.matcher(user.getEmail());
		Matcher passMatch = passPattern.matcher(user.getPassword());
				
		if(!emailMatch.matches())
			throw new MailDomainInvalidException(Constants.VALIDATION_DOMAIN_EMAIL);
		if(!passMatch.matches())
			throw new InvalidPasswordException(Constants.VALIDATION_PASSWORD);
		try {
			return new ResponseEntity<>(userRepo.save(user), HttpStatus.CREATED);
		} catch (Exception e) {
			throw new MailDuplicatedException(Constants.VALIDATION_DUPLICATED_EMAIL);
		}			
	}

	@GetMapping("/users")
	public ResponseEntity<List<Usuario>> getAllUsers() {
		try {
			List<Usuario> list = userRepo.findAll();
			if (list.isEmpty() || list.size() == 0)
				return new ResponseEntity<List<Usuario>>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
		} catch (Exception e) {		
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<Usuario> getUser(@PathVariable Long id) {
		Optional<Usuario> user = userRepo.findById(id);

		if (user.isPresent())
			return new ResponseEntity<Usuario>(user.get(), HttpStatus.OK);
		
		throw new UserNotFoundException("id-" + id);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<Usuario> updateUser(@RequestBody Usuario user) {
		try {
			return new ResponseEntity<Usuario>(userRepo.save(user), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
		try {
			Optional<Usuario> user = userRepo.findById(id);
			if (user.isPresent())
				userRepo.delete(user.get());

			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
