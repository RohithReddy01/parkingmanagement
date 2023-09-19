package com.example.parkingmanagement.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.parkingmanagement.model.Slot;
import com.example.parkingmanagement.model.User;
import com.example.parkingmanagement.repository.ISlotRepository;
import com.example.parkingmanagement.repository.IUserRepository;
import com.example.parkingmanagement.service.impl.SlotService;
import com.example.parkingmanagement.service.impl.UserService;

@SpringBootTest
public class UserServiceTest {
	
	@MockBean
	private IUserRepository userrepo;
	

	
	@Autowired
	private UserService uservice;
	

	
	@Test
	public void findAllTest() {
		User u=new User();
		u.setId(1L);
		u.setName("John");
		u.setEmail("John21@gmail.com");
		u.setPassword("John@21");
		when(userrepo.findAll()).thenReturn(Stream
				.of(u).collect(Collectors.toList()));
		assertEquals(1,uservice.findAll().size());
	}
	
	@Test
	public void saveUserTest() {
		User u=new User();
		u.setId(1L);
		u.setName("John");
		u.setEmail("John21@gmail.com");
		u.setPassword("John@21");
		when(userrepo.save(u)).thenReturn(u);
		assertEquals(u,uservice.saveUser(u));
	}
	
	@Test
	public void getByIdTest() {
		User u=new User();
		u.setId(1L);
		u.setName("John");
		u.setEmail("John21@gmail.com");
		u.setPassword("John@21");
		Long id=u.getId();
		when(userrepo.findById(id)).thenReturn(Optional.of(u));
		assertEquals(Optional.of(u),uservice.getById(id));
	}
	
	@Test
	public void deleteUserTest() {
		User u=new User();
		u.setId(1L);
		u.setName("John");
		u.setEmail("John21@gmail.com");
		u.setPassword("John@21");
		Long id=u.getId();
		uservice.deleteUser(id);
		verify(userrepo,times(1)).deleteById(id);
	}

}
