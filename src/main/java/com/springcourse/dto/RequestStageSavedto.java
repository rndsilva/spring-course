package com.springcourse.dto;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import com.springcourse.enums.RequestState;

import jakarta.validation.constraints.NotNull;

public class RequestStageSavedto {
	
	
	private String description;
	
	@NotNull(message="State required")
	private RequestState state;
	
	@NotNull(message="Request required")
	private Request request;
	
	@NotNull(message="Owner required")
	private User owner;
	
	public RequestStage transformeToRequestStage() {
		RequestStage stage = new RequestStage(null, description, null, state, request, owner);
		return stage;
	}
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public RequestState getState() {
		return state;
	}
	public void setState(RequestState state) {
		this.state = state;
	}
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public RequestStageSavedto(String description, RequestState state, Request request, User owner) {
		super();
		this.description = description;
		this.state = state;
		this.request = request;
		this.owner = owner;
	}
	
	public RequestStageSavedto() {
		
	}
	

}
