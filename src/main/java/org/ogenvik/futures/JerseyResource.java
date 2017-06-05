package org.ogenvik.futures;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;

/**
 * Copyright © 2017 Erik Ogenvik.
 */
@Component
@Path("/jersey")
public class JerseyResource {


    @GET
    @Path("sync")
    public Response sync() throws InterruptedException {
        Thread.sleep(1000);
        return Response.ok("jersey sync").build();
    }

    @GET
    @Path("async")
    public void async(@Suspended final AsyncResponse ar) {

        FuturesApplication.completeLater()
                .thenAccept(s -> ar.resume("spring " + s));
    }
}
