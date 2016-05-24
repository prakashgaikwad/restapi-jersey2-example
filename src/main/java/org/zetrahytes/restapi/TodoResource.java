package org.zetrahytes.restapi;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.zetrahytes.restapi.entity.Todo;

import static java.util.Objects.isNull;

@Path("todos")
public class TodoResource {

	private static Map<Long, Todo> todos;

	static {
		todos = new ConcurrentHashMap<>(); // thread-safe
		Date today = new Date();
		todos.put(1L, new Todo(1, "add a working jersey2 example", true, today));
		todos.put(2L, new Todo(2, "add checkstyle", false, today));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Todo> list() {
		return todos.values();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response todoById(@PathParam("id") long id) {
		Todo todo = todos.get(id);
		if (isNull(todo)) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(todo).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addTodo(Todo todo) {
		todos.put(todo.getId(), todo);
		return Response.noContent().build(); //204 - Todo added
	}
	
	@DELETE
	@Path("{id}")
	public Response remove(@PathParam("id") long id) {
		Todo removedTodo = todos.remove(id);
		if (isNull(removedTodo)) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.noContent().build(); //204 - Todo removed
	}
}
