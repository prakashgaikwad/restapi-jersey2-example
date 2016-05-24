package org.zetrahytes.restapi.entity;

import java.util.Date;

public class Todo {

	private String name;
	private boolean done;
	private Date created;
	
	public Todo(String name, boolean done, Date created) {
		this.name = name;
		this.done = done;
		this.created = created;
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

}
