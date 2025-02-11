package com.berkepite.MainApplication32Bit.subscribers;

import com.berkepite.MainApplication32Bit.coordinator.ICoordinator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SubscriberLoader {
    private final ApplicationContext applicationContext;
    private static final Logger LOGGER = LogManager.getLogger(SubscriberLoader.class);

    public SubscriberLoader(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public ISubscriber load(SubscriberBindingConfig bindingConfig, ICoordinator coordinator) {
        ISubscriber subscriber = null;

        try {
            String fullClassName = bindingConfig.getClassPath() + "." + bindingConfig.getClassName();
            Class<?> clazz = Class.forName(fullClassName);

            ConfigurableApplicationContext context = (ConfigurableApplicationContext) applicationContext;
            DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();

            GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
            beanDefinition.setBeanClass(clazz);
            beanDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);
            beanDefinition.getPropertyValues().add("coordinator", coordinator);

            beanFactory.registerBeanDefinition(bindingConfig.getClassPath(), beanDefinition);

            subscriber = (ISubscriber) context.getBean(bindingConfig.getClassPath());
        } catch (Throwable e) {
            LOGGER.error("Failed to load subscriber class {}, {}", bindingConfig.getClassPath(), e);
        }

        return subscriber;

    }
}
