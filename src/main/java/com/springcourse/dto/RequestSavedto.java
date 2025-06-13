package com.springcourse.dto;

import java.util.ArrayList;
import java.util.List;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RequestSavedto {
	
	@NotBlank(message = "Subject required")
	private String subject;
	private String description;
	
	@NotNull(message = "Owner required")
	private User owner;
	private List<RequestStage> stages = new ArrayList<RequestStage>();
	
	public Request transformToRequest() {
		Request request  = new Request(null, this.subject, this.description, null, null, this.owner, stages);
		return request;
	}
	
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public List<RequestStage> getStages() {
		return stages;
	}
	public void setStages(List<RequestStage> stages) {
		this.stages = stages;
	}
	public RequestSavedto(String subject, String description, User owner, List<RequestStage> stages) {
		super();
		this.subject = subject;
		this.description = description;
		this.owner = owner;
		this.stages = stages;
	}
	
	public RequestSavedto()
	{
		
	}
	
	
	
}
