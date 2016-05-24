package org.zetrahytes.restapi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.zetrahytes.restapi.entity.Todo;

@Path("todos")
public class TodoResource {
	
	private static Collection<Todo> todoList = new ArrayList<>();
	
	static {
		todoList.add(new Todo("add a working jersey2 example", true, new Date()));
		todoList.add(new Todo("add checkstyle", false, new Date()));
	}
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Todo> listTodos() {
        return todoList;
    }
}
