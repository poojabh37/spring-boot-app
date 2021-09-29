package io.javabrains.springbootstarter.scopes;

import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Provider;

@Component
public class SingletonBean2 {

    @Inject
    private Provider<PrototypeBean> prototypeBean;

    public PrototypeBean getPrototypeBean() {
        return prototypeBean.get();
    }


}
