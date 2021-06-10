package org.camel.mypackage;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new MyRouteBuilder());
        context.start();
        System.out.println("Camel context started. Wating 2s...");
        Thread.sleep(2000);
        System.out.println("Done Wating. Exiting.");
    }
}
