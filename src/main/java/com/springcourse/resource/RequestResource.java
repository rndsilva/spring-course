package com.springcourse.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.service.RequestService;
import com.springcourse.service.RequestStageService;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value = "requests")
public class RequestResource {
	@Autowired private RequestService requestService;
	@Autowired private RequestStageService stageService; 
	
	
	//save
	@PostMapping
	public ResponseEntity<Request> save(@RequestBody Request request){
		Request createdRequest = requestService.save(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
	}
	
	
	//update
	@PutMapping("/{id}")
	public ResponseEntity<Request> update(@PathVariable(name  = "id") Long id, @RequestBody Request request){
		
		request.setId(id);
		
		Request updatedRequest = requestService.update(request);
		return ResponseEntity.ok(updatedRequest);
	}
	
	//getById
	@GetMapping("/{id}")
	public ResponseEntity<Request> getById(@PathVariable(name  = "id") Long id){
		Request request = requestService.getById(id);
		return ResponseEntity.ok(request);
	}
	
	//list all
	/*@GetMapping
	public ResponseEntity<List<Request>> listAll(){
		List<Request> requests = requestService.listAll();
		return ResponseEntity.ok(requests);
	}*/
	
	@GetMapping
	public ResponseEntity<PageModel<Request>> listAll(
			@RequestParam(value = "page") int page,
			@RequestParam(value = "size") int size){
		
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<Request> pm = requestService.listAllOnLazyMode(pr);
		
		return ResponseEntity.ok(pm);
		
	}
	
	//http://localhost:8080/requests/1/request-stage
	/*@GetMapping("/{id}/request-stages")
	public ResponseEntity<List<RequestStage>> listAllStagesById(@PathVariable(name  = "id") Long id){
		List<RequestStage> stages = stageService.listAllByRequestId(id);
		return ResponseEntity.ok(stages);
	}*/
	
	@GetMapping("/{id}/request-stages")
	public ResponseEntity<PageModel<RequestStage>> listAllStagesById(
			@PathVariable(name  = "id") Long id,
			@RequestParam(value = "page") int page,
			@RequestParam(value = "size") int size
			){
		PageRequestModel pr =new PageRequestModel(page, size);
		
		PageModel<RequestStage> pm = stageService.findAllByRequestIdOnLazyModel(id, pr);
		
		return ResponseEntity.ok(pm);
	}
	

}
