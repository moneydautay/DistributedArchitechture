package org.rest;

import org.rest.service.SampleService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by greenlucky on 1/6/17.
 */
@ApplicationPath("")
public class App extends Application {

    private Set<Object> singletons = new HashSet<Object>();

    public App() {
        singletons.add(new SampleService());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
