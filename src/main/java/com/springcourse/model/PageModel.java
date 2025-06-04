package com.springcourse.model;

import java.io.Serializable;
import java.util.List;

public class PageModel<T> implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int totalElements;
	private int tageSize;
	private int totalPages;
	private List<T> elements;
	public int getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
	}
	public int getTageSize() {
		return tageSize;
	}
	public void setTageSize(int tageSize) {
		this.tageSize = tageSize;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public List<T> getElements() {
		return elements;
	}
	public void setElements(List<T> elements) {
		this.elements = elements;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public PageModel(int totalElements, int tageSize, int totalPages, List<T> elements) {
		super();
		this.totalElements = totalElements;
		this.tageSize = tageSize;
		this.totalPages = totalPages;
		this.elements = elements;
	}
	public PageModel() {
		
	}
	
	
	
}
