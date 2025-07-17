package com.example.SpringBootTesting.entity;

import java.util.List;

import lombok.*;

@Getter
@Setter

public class Course {
	private String id;
	private String name;
	private String description;
	private List<String> steps;
	
	public Course()
	{
		
	}
	
	public Course(String id, String name, String description, List<String> steps)
	{
		super();
		this.id=id;
		this.name=name;
		this.description=description;
		this.steps=steps;
	}
}
