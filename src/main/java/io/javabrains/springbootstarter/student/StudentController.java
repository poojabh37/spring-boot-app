package io.javabrains.springbootstarter.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@Validated
public class StudentController {

    @Autowired
    private MessageSource messageSource;

    //validate path variables
    @RequestMapping("/student/{age}")
    public ResponseEntity<String> geAgeP(@PathVariable @Min(5) @Max(100) int age) {
        return ResponseEntity.ok("Your age is " + age);
    }

    //validate query variables
    @RequestMapping("/student")
    public ResponseEntity<String> geAgeQ(@RequestParam @Min(5) @Max(100) int age) {
        return ResponseEntity.ok("Your age is " + age);
    }

    //validate RequestBody
    @PostMapping(value = "/students",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> acceptStudent(@Valid @RequestBody Student student) {
        return ResponseEntity.ok(student);
    }

    //validate Request Headers
    @GetMapping(value = "/students/header",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> acceptHeader(@RequestHeader @Min(5) int age) {
        return ResponseEntity.ok(age);
    }

    @GetMapping(value = "student/i18n", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getMessageI18n(
            //     @RequestHeader(value = "Accept-Language", required = false) Locale locale
    ) {
        return messageSource.getMessage("good.morning", new Object[]{"pooja"}, LocaleContextHolder.getLocale());
    }

    //for content negotiation, added Jackson Dataformat XML jar, use Accept header for xml
    @GetMapping("/sayHello")
    public Student sayHello() {
        return new Student("pooja", 25);
    }
}
