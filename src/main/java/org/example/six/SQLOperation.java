package org.example.six;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

public class SQLOperation {
    public static void main(String[] args) throws Exception {

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/camel_example");
        dataSource.setUser("root1");
        dataSource.setPassword("123");

        SimpleRegistry simpleRegistry = new SimpleRegistry();
        simpleRegistry.put("myDataSource",dataSource);

        CamelContext camelContext = new DefaultCamelContext(simpleRegistry);
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start")
                        .to("jdbc:myDataSource")
                        .bean(new ResultHandler(), "printResult");
            }
        });

        camelContext.start();

        String sql = "select * from customer";

        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        producerTemplate.sendBody("direct:start", sql);


    }
}
