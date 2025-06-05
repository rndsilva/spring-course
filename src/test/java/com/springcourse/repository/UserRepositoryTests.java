package com.springcourse.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.enums.RequestState;
import com.springcourse.enums.Role;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UserRepositoryTests {
	
	@Autowired private UserRepository userRepository;
	
	@Disabled
	@Test
	@Order(1)
	public void saveTest() {
		User user = new User(null, "Kevin", "kevin.wingi@gmail.com", "123", Role.ADMINISTRATOR, null, null);
		User createdUSer = userRepository.save(user);
		
		assertThat(createdUSer.getId()).isEqualTo(1L);
	}
	

	
	@Disabled
	@Test
	@Order(2)
	public void updateTest() {
		User user = new User(1L, "Kevin Wingi", "kevin.wingi@gmail.com", "123", Role.ADMINISTRATOR, null, null);
		User updateUSer = userRepository.save(user);
		
		assertThat(updateUSer.getName()).isEqualTo("Kevin Wingi");
	}
	
	@Disabled
	@Test
	@Order(3)
	public void getByIdTest() {
		Optional<User> resultt = userRepository.findById(1L);
		User user = resultt.get();
		
		assertThat(user.getPassword()).isEqualTo("123");

	}
	
	@Disabled
	@Test
	@Order(4)
	public void listTest() {
	
		List<User> users = userRepository.findAll(); 
		assertThat(users.size()).isEqualTo(1);
	}
	
	@Disabled
	@Test
	@Order(5)
	public void loginTest() {
		Optional<User> result = userRepository.login("kevin.wingi@gmail.com", "123");
		User loggedUser = result.get();
		
		assertThat(loggedUser.getId()).isEqualTo(1L);
		
	}
	
	
	@Test
	@Order(6)
	public void updateRoleTest() {
		int affectedRows = userRepository.updateRole(4L, Role.ADMINISTRATOR);
		assertThat(affectedRows).isEqualTo(1);
	}

}
