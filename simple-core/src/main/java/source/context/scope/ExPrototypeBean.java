package source.context.scope;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Slf4j
public class ExPrototypeBean implements DisposableBean {
    @PostConstruct
    public void init() {
        log.info("ExPrototypeBean init method");
    }

    @PreDestroy
    public void des () {
        log.info("PreDestroy: ExPrototypeBean destroy method");
    }

    @Override
    public void destroy() {
        log.info("DisposableBean: ExPrototypeBean destroy method");
    }
}
