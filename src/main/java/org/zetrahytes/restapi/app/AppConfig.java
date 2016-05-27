package org.zetrahytes.restapi.app;

import java.util.Map;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.zetrahytes.restapi.entity.Todo;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import io.swagger.jaxrs.config.BeanConfig;

@ApplicationPath("/myapp/*")
public class AppConfig extends ResourceConfig {
    public AppConfig() {
        packages("io.swagger.jaxrs.listing", "org.zetrahytes.restapi", "org.zetrahytes.restapi.jersey");
        initializeHazelcast();
        initializeSwaggerBeanConfig();
    }

    public void initializeHazelcast() {
        Config cfg = new Config();
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
        Map<String, Todo> todos = instance.getMap("todos");
        property("cache", todos);
    }

    public void initializeSwaggerBeanConfig() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle("Todo API");
        beanConfig.setDescription("Manage todos with Todo API");
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[] { "http" });
        beanConfig.setHost("localhost:8000");
        beanConfig.setBasePath("/myapp");
        beanConfig.setResourcePackage("org.zetrahytes.restapi");
        beanConfig.setScan(true);
        beanConfig.setPrettyPrint(true);
    }
}
