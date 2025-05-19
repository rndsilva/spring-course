package com.springcourse.repository;

import java.util.Date; 
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.enums.RequestState;
import com.springcourse.enums.Role;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class RequestRepositoryTests {
	
	@Autowired private RequestRepository requestRepository;
	
	@Test
	@Order(1)
	public void saveTest() {
		
		User owner = new User();
		owner.setId(1L);
		Date creationDate = new Date(); // <-- adicionando a data atual
		Request request = new Request(null, "Novo LapTop HP", "Pretendo obter um laptop HP, de RAM 16GB", creationDate, RequestState.OPEN, owner, null);
		Request updatedRequest = requestRepository.save(request);
		assertThat(updatedRequest.getDescription()).isEqualTo("Pretendo obter um laptop HP, de RAM 16GB");
	}
	

	@Test
	@Order(2)
	public void getByIdTest() { 
		Optional<Request> result = requestRepository.findById(1L);
		Request request = result.get();
		
		assertThat(request.getSubject()).isEqualTo("Novo LapTop HP");
	}
	
	@Test
	@Order(3)
	public void listTest() {
		List<Request> requests = requestRepository.findAll();
		assertThat(requests.size()).isEqualTo(1);
	}
		

	
	@Test
	@Order(4)
	public void listByIdOwnerdTest() {
		
		List<Request> requests = requestRepository.findAllByOwnerId(1L);
		assertThat(requests.size()).isEqualTo(1);
		
	}
	
	@Test
	@Order(5)
	public void updateStatusTest() {
		int affectedRows = requestRepository.updateStatus(1L, RequestState.IN_PROGRESS);
		
		assertThat(affectedRows).isEqualTo(1);
				
				
		Optional<Request> result =  requestRepository.findById(1L);
		
		Request request = result.get();
		assertThat(request.getSubject()).isEqualTo("Novo LapTop HP");
		
		
	}	

	
	

}
