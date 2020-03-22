package org.example.one;

import org.apache.camel.builder.RouteBuilder;

public class FirstRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        System.out.println("it's first route");
    }
}
