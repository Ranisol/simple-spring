package simple.spring.context.scope;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Slf4j
public class ExSingletonBean implements DisposableBean {
    @PostConstruct
    public void init() {
        log.info("ExSingletonBean init method");
    }

    @PreDestroy
    public void des () {
        log.info("PreDestroy: ExSingletonBean destroy method");
    }

    @Override
    public void destroy() {
        log.info("DisposableBean: ExSingletonBean destroy method");
    }
}