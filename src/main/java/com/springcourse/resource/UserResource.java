package com.springcourse.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.dto.UserLogindto;
import com.springcourse.dto.UserSavedto;
import com.springcourse.dto.UserUpdateRoledto;
import com.springcourse.dto.UserUpdatedto;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.service.RequestService;
import com.springcourse.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "users")
public class UserResource {
	@Autowired private UserService userService;
	@Autowired private RequestService requestsService;
	
	//save
	@PostMapping
	public ResponseEntity<User> save(@RequestBody @Valid UserSavedto userdto){
		User userToSave = userdto.transformToUser();
		User createdUser = userService.save(userToSave);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
	
	//update	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable(name = "id") Long id, @RequestBody @Valid UserUpdatedto userdto){

		User user = userdto.transformToUser();
		
		user.setId(id);
		User updatedUser = userService.update(user);
		return ResponseEntity.ok(updatedUser);
	}

	
	//getById
	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable("id") Long id){
		User user = userService.getById(id);
		return ResponseEntity.ok(user);
	}
	
	//list
	/*@GetMapping
	public ResponseEntity<List<User>> listAll(){
		List<User> users = userService.listAll();
		return ResponseEntity.ok(users);
		
	}*/
	
	@GetMapping
	public ResponseEntity<PageModel<User>> listAll(
			@RequestParam(value = "page", defaultValue="0") int page,
			@RequestParam(value = "size", defaultValue="10") int size){
		
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<User> pm = userService.listAllOnLazyMode(pr);
		
		return ResponseEntity.ok(pm);
		
	}
	
	
			
	//login
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody @Valid UserLogindto user){
		User loggedUser = userService.login(user.getEmail(), user.getPassword());
		return ResponseEntity.ok(loggedUser);
		
	}
	
	//list all by owner id
	// http://localhost:8080/users/id/requests
	/*@GetMapping("/{id}/requests")
	public ResponseEntity<List<Request>> listAllRequestsById(@PathVariable(name = "id") Long id){
		List<Request> requests = requestsService.listAllByOwnerId(id);
		return ResponseEntity.ok(requests);
	}*/

	@GetMapping("/{id}/requests")
	public ResponseEntity<PageModel<Request>> listAllRequestsById(
			@PathVariable(name = "id") Long id,
			@RequestParam(value = "size", defaultValue="0") int size,
			@RequestParam(value = "page", defaultValue="10") int page
			){
		
		PageRequestModel pr = new PageRequestModel(page, size);
		
		PageModel<Request> pm = requestsService.findAllByOwnerIdOnLazyModel(id, pr);
		
		return ResponseEntity.ok(pm);
	}
	
	@PatchMapping("/role/{id}")
	public ResponseEntity<?> updateRole(@PathVariable(name = "id") Long id, @RequestBody @Valid UserUpdateRoledto userdto ){
		User user = new User();
		user.setId(id);
		user.setRole(userdto.getRole());
		
		userService.updateRole(user);
		
		return ResponseEntity.ok().build();
		
	}
	
}
