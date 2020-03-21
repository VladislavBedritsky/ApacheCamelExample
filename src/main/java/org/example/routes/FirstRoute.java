package org.example.routes;

import org.apache.camel.builder.RouteBuilder;

public class FirstRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        System.out.println("it's first route");
    }
}
