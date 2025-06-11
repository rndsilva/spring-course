package com.springcourse.dto;

import com.springcourse.enums.Role;

import jakarta.validation.constraints.NotNull;

public class UserUpdateRoledto {
	
	@NotNull(message="Role Required")
	private Role role;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
