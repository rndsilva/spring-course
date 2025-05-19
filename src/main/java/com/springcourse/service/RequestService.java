package com.springcourse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date; 
import java.util.List;
import java.util.Optional;

import com.springcourse.domain.Request;
import com.springcourse.enums.RequestState;
import com.springcourse.repository.RequestRepository;

@Service
public class RequestService {
	
	@Autowired private RequestRepository requestRepository;
	
	//save
	public Request save(Request request) {
		request.setState(RequestState.OPEN);
		request.setCreationDate(new Date());
		
		Request createdRequest = requestRepository.save(request);
		return createdRequest;
	}
	//update
	public Request update(Request request) {
		
		Request updatedRequest = requestRepository.save(request);
		return updatedRequest;
	}
	//get
	public Request getById(Long id) {
		Optional<Request> result = requestRepository.findById(id);
		return result.get();
	}
	
	//list
	public List<Request> listAll(){
		List<Request> requests = requestRepository.findAll();
		return requests;
	}
	
	
	//list By Owner
	public List<Request> listAllByOwnerId(Long ownerId){
		List<Request> requests = requestRepository.findAllByOwnerId(ownerId);
		return requests;
	}

}
