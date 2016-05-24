package org.zetrahytes.restapi.app;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.zetrahytes.restapi.TodoResource;

@ApplicationPath("/myapp/*")
public class App extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(TodoResource.class);

        return classes;
    }
}
