package org.example.five;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

public class CallMethodUsingClassComponent {
    public static void main(String[] args) throws Exception {

        MyService myService = new MyService();
        SimpleRegistry registry = new SimpleRegistry();
        registry.put("myService",myService);

        CamelContext camelContext = new DefaultCamelContext(registry);
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start")
                        .to("bean:myService?method=doSomething");
            }
        });
        camelContext.start();

        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        producerTemplate.sendBody("direct:start","hello");
    }
}
