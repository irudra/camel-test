package org.camel.mypackage;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpComponent;
import org.apache.camel.component.jetty.JettyHttpComponent;
import org.apache.camel.support.jsse.KeyManagersParameters;
import org.apache.camel.support.jsse.KeyStoreParameters;
import org.apache.camel.support.jsse.SSLContextParameters;
import org.apache.camel.support.jsse.TrustManagersParameters;

public class MyRouteBuilder extends RouteBuilder {
    public void configure() {
        configureJetty();
        configureHttp4();
        //This works with configuring Jetty
        from("jetty:https://0.0.0.0:8085/sample1/?matchOnUriPrefix=true")
                .to("file://./?fileName=out.csv");
        //This url does not working with the configuring the jetty with configure jetty this works.
        from("jetty:http://0.0.0.0:8084/sample2/?matchOnUriPrefix=true")
                .to("file://./?fileName=out2.csv");
    }

    private void configureJetty() {
        KeyStoreParameters ksp = new KeyStoreParameters();
        ksp.setResource("./trustStore.jks");
        ksp.setPassword("someSecretPassword");
        KeyManagersParameters kmp = new KeyManagersParameters();
        kmp.setKeyStore(ksp); kmp.setKeyPassword("someSecretPassword");
        SSLContextParameters scp = new SSLContextParameters();
        scp.setKeyManagers(kmp);
        JettyHttpComponent jettyComponent = getContext().getComponent("jetty", JettyHttpComponent.class);
        jettyComponent.setSslContextParameters(scp);
    }
    private void configureHttp4() {
        KeyStoreParameters ksp = new KeyStoreParameters();
        ksp.setResource("./dela.jks");
        ksp.setPassword("password");
        TrustManagersParameters tmp = new TrustManagersParameters();
        tmp.setKeyStore(ksp);
        SSLContextParameters scp = new SSLContextParameters();
        scp.setTrustManagers(tmp);
        HttpComponent httpComponent = getContext().getComponent("https4", HttpComponent.class);
        httpComponent.setSslContextParameters(scp);
    }
}
