package org.zetrahytes.restapi.entity;

import java.util.Date;

public class Todo {

	private long id;
	private String name;
	private boolean done;
	private Date created;

	public Todo() {
	}

	public Todo(long id, String name, boolean done, Date created) {
		this.id = id;
		this.name = name;
		this.done = done;
		this.created = created;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return String.format("Todo [id=%s, name=%s, done=%s, created=%s]", id, name, done, created);
	}

}
