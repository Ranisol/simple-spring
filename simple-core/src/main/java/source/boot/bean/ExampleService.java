package source.boot.bean;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;


@Service
@Slf4j
public class ExampleService implements
        BeanNameAware,
        BeanClassLoaderAware,
        BeanFactoryAware,
        InitializingBean,
        EnvironmentAware,
        ServletContextAware
{
    @PostConstruct
    public void init() {
        log.info("PostConstruct: init method");
    }

    @Override
    public void setBeanName(String name) {
        log.info("My Bean name is: " + name);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        log.info("My ClassLoader name is: " + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info("My BeanFactory is: " + beanFactory);
    }

    @Override
    public void setEnvironment(Environment environment) {
        log.info("My Environment is: " + environment);
    }

    @Override
    public void afterPropertiesSet() {
        log.info("afterPropertiesSet: My Bean is being initialized");
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        log.info("My ServletContext is: " + servletContext);
    }
}
