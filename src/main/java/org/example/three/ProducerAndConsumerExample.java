package org.example.three;

import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class ProducerAndConsumerExample {

    public static void main(String[] args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();

        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start")
                        .process(new Processor() {
                            @Override
                            public void process(Exchange exchange) throws Exception {
                                String message = exchange.getIn().getBody(String.class);
                                message = message + " - boom";

                                exchange.getOut().setBody(message);
                            }
                        })
                        .to("seda:end");
            }
        });

        camelContext.start();

        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        producerTemplate.sendBody("direct:start", "Hello world");

        ConsumerTemplate consumerTemplate = camelContext.createConsumerTemplate();
        String message = consumerTemplate.receiveBody("seda:end", String.class);
        System.out.println(message);
    }
}
