package org.ogenvik.futures;

import org.glassfish.jersey.server.ResourceConfig;

//Uncomment the line below to enable Jersey (if Jersey is enabled, Spring Web won't work).
//@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(JerseyResource.class);
    }
}