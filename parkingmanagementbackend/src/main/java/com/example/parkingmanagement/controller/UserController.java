package com.example.parkingmanagement.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.parkingmanagement.dto.LoginDto;
import com.example.parkingmanagement.model.User;
import com.example.parkingmanagement.repository.IUserRepository;
import com.example.parkingmanagement.service.IUserService;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IUserRepository userRepository;
	
	@PostMapping("/")
	private ResponseEntity<?> checkIfUserAlreadyRegistered(@RequestBody User user) {
		HashMap<String, String> res = new HashMap<>();
		
		try {
			User u = userRepository.findByEmail(user.getEmail());
//			System.out.println("user:" + user.toString());
			if (u != null) {
				
				res.put("error", "fail");
				System.out.println("user exists, shall not be registered");
				System.out.println(user.getEmail() + "," + user.getPassword());
				return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				System.out.println("I'm here!");
				res.put("message", "success");
				System.out.println("User doesnot exist, hence registering");
				userRepository.save(new User(user.getName(),user.getEmail(),user.getPassword()));
				return new ResponseEntity<>("Successful", HttpStatus.OK);
			}
		}
		catch (Exception e) {
			System.out.println("Failed!");
			System.out.println(e.toString());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/ hi")
	public String hello() {
		return "Hello";
	}

	// ENDPOINT FOR ADDING THE USER

//    public User adduser(@RequestBody User user) {
//    	System.out.println(user);
//    	System.out.println("?");
//    	
//    	return userService.saveUser(user);
//    }
	
	// @dev need an endpoint "/" to check if user exists!

	
	
	@PostMapping("/user")
	private ResponseEntity<?> addUser(@RequestBody User user) {
//        System.out.println(user);

//        return new ResponseEntity<>("Successful", HttpStatus.OK);
		try {
			System.out.println(user);

			userService.saveUser(user);
			return new ResponseEntity<>("Successful", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// ENDPOINT FOR GETTING ALL THE USER
	@GetMapping("/users")
	private ResponseEntity<?> getUsers() {
		try {
			return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// ENDPOINT FOR GETTING ONE USER BY ID
	@GetMapping("/user/{id}")
	private ResponseEntity<?> getUserById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(userService.getById(id), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// ENDPOINT FOR UPDATING THE USER BY ID
	@PutMapping("/user/{id}")
	private ResponseEntity<?> updateById(@RequestBody User user) {
		try {
			return new ResponseEntity<>(userService.updateUser(user), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// ENDPOINT FOR DELETING THE USER BY ID
	@DeleteMapping("/user/{id}")
	private ResponseEntity<?> deleteById(@PathVariable Long id) {
		try {
			userService.deleteUser(id);

			return new ResponseEntity<>("deleted successfully", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// ENDPOINT FOR ADMIN LOGIN
	@PostMapping("/adminlogin")
	private ResponseEntity<?> adminLogin(@RequestBody LoginDto l) {
		HashMap<String, String> res = new HashMap<>();
		try {
			if (l.getEmail().equals("admin@gmail.com") && l.getPassword().equals("admin")) {
				res.put("message", "Admin");
				return new ResponseEntity<>(res, HttpStatus.OK);
			}
			return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// ENDPOINT FOR USER LOGIN
	@PostMapping("/userlogin")
	private ResponseEntity<?> findByEmailAndPassword(@RequestBody User user) {
		HashMap<String, String> res = new HashMap<>();
		try {
			User u = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
			System.out.println(u);
			if (u != null) {
				res.put("message", "success");
				res.put("userId",u.getId()+"");
//				System.out.println("user exists, logging in");
				return new ResponseEntity<>(res, HttpStatus.OK);
			} else {
				res.put("error", "fail");
				System.out.println("Failed!!!!");
				return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	


	// ENDPOINT FOR GETTING USER BY EMAIL
	@PostMapping("getbyemail")
	private ResponseEntity<?> getByEMAIL(@RequestBody HashMap<String, String> body) {
		String email = body.get("email");
		HashMap<String, String> res = new HashMap<>();

		try {
			if (userService.findByEmail(email) != null) {
				res.put("message", "email");
				return new ResponseEntity<>(res, HttpStatus.OK);
			} else {
				res.put("error", "invalid");
				return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
