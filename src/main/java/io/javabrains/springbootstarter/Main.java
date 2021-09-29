package io.javabrains.springbootstarter;

import io.javabrains.springbootstarter.scopes.SingletonBean;
import io.javabrains.springbootstarter.scopes.SingletonBean2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

// Spring Boot auto configures a class because of presence of spring-boot-autoconfigure.jar
// eg. DataSourceAutoConfiguration if the @ConditionalOnClass is fulfilled
@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        // example to inject a prototype bean into a singleton bean
        SingletonBean singletonBean = applicationContext.getBean(SingletonBean.class);
        System.out.println(singletonBean.getPrototypeBean());
        System.out.println(singletonBean.getPrototypeBean());

        //2nd way
        SingletonBean2 singletonBean2 = applicationContext.getBean(SingletonBean2.class);
        System.out.println(singletonBean2.getPrototypeBean());
        System.out.println(singletonBean2.getPrototypeBean());

    }
}
