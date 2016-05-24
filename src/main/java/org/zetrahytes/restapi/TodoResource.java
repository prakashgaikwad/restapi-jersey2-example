package org.zetrahytes.restapi;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zetrahytes.restapi.entity.Todo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import static java.util.Objects.isNull;

@Path("todos")
@Api(value = "/todos")
public class TodoResource {

    private static final Logger LOG = LoggerFactory.getLogger(TodoResource.class);
    private static Map<Long, Todo> todos;

    static {
        todos = new ConcurrentHashMap<>(); // thread-safe
        Date today = new Date();
        todos.put(1L, new Todo(1, "add a working jersey2 example", true, today));
        todos.put(2L, new Todo(2, "add checkstyle", false, today));
        LOG.info("Populated some todos");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Return all todos")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "returns a list of todos in json format") })
    public Response getAllTodos() {
        Collection<Todo> todoCollection = todos.values();
        return Response.ok(todoCollection).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Add a new todo")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "todo added sucessfully") })
    public Response addTodo(@ApiParam(value = "new todo", required = true) Todo todo) {
        todos.put(todo.getId(), todo);
        return Response.noContent().build(); // 204 - Todo added
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Return a todo by id")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "a todo, if present, in json format"),
            @ApiResponse(code = 404, message = "todo not found") })
    public Response getTodoById(@ApiParam(value = "id of the todo", required = true) @PathParam("id") long id) {
        Todo todo = todos.get(id);
        if (isNull(todo)) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(todo).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Update an existing todo")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "todo updated sucessfully"),
            @ApiResponse(code = 404, message = "todo not found") })
    public Response updateTodo(@ApiParam(value = "new todo", required = true) Todo todo) {
        if (isNull(todos.get(todo.getId()))) {
            return Response.status(Status.NOT_FOUND).build();
        }
        todos.put(todo.getId(), todo);
        return Response.noContent().build(); // 204 - Todo updated
    }

    @DELETE
    @Path("{id}")
    @ApiOperation(value = "Return a todo by id")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "a todo, if present, is removed"),
            @ApiResponse(code = 404, message = "todo not found") })
    public Response removeTodo(@ApiParam(value = "id of the todo", required = true) @PathParam("id") long id) {
        Todo removedTodo = todos.remove(id);
        if (isNull(removedTodo)) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.noContent().build(); // 204 - Todo removed
    }
}
