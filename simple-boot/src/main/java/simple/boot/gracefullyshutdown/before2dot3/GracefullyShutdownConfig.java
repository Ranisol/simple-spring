package simple.boot.gracefullyshutdown.before2dot3;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GracefullyShutdownConfig {
    @Bean
    public GracefullyShutdown shutdown() {
        return new GracefullyShutdown();
    }
    @Bean
    public ConfigurableServletWebServerFactory webServerFactory(final GracefullyShutdown shutdown) {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers(shutdown);
        return factory;
    }
}
