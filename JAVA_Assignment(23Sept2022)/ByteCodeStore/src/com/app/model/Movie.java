package com.app.model;

public class Movie {

	private long id;
	private String movieName;

	// Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String name) {
		this.movieName = name;
	}

	// toString()
	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + movieName + "]";
	}

	// Parameterized constructor
	public Movie(long id, String name) {
		super();
		this.id = id;
		this.movieName = name;
	}

	// Default constructor
	public Movie() {
		super();
	}

}
