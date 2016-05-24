package org.zetrahytes.restapi.app;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.zetrahytes.restapi.TodoResource;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

@ApplicationPath("/myapp/*")
public class App extends Application {
    
    public App() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle("Todo API");
        beanConfig.setDescription("Manage todos with Todo API");
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8000");
        beanConfig.setBasePath("/myapp");
        beanConfig.setResourcePackage("org.zetrahytes.restapi");
        beanConfig.setScan(true);
    }
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(TodoResource.class);
        classes.add(ApiListingResource.class);
        classes.add(SwaggerSerializers.class);
        return classes;
    }
}
