package io.javabrains.springbootstarter.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class HelloController {

    @Value("${hello.enabled}")
    private boolean helloEnabled;

    //default HTTP method = GET
    @RequestMapping("/hello")
    public int sayHi() {
        if (helloEnabled)
            return 5;
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "hello is not enabled");
    }
}
