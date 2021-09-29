package io.javabrains.springbootstarter.student;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ValidatedStudentController {

    // customize Errors
    @RequestMapping(value = "/students/validate",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> validateStudent(@RequestBody @Valid Student student, Errors errors) {
        if (errors.hasErrors()) {
            return customizeErrors(errors);
        }
        return ResponseEntity.ok("Your student has been accepted");
    }

    private ResponseEntity<Object> customizeErrors(Errors errors) {
        if (errors.hasFieldErrors()) {
            StringBuilder error = getErrorMessage(errors.getFieldErrors());
            System.out.println(error);
            return ResponseEntity
                    .badRequest()
                    .body(error);
        }
        return ResponseEntity.badRequest().body(errors.getAllErrors().toString());
    }

    private StringBuilder getErrorMessage(List<FieldError> fieldErrors) {
        StringBuilder errorBuilder = new StringBuilder("Errors count : " + fieldErrors.size() + "\n");
        fieldErrors.forEach(fieldError ->
                errorBuilder.append(fieldError.getField()).append(" : ")
                        .append(fieldError.getDefaultMessage()).append("\n"));
        return errorBuilder;
    }
}
