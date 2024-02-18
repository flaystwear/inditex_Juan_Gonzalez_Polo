package com.juangp.inditex.application.contrroller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PricesController {
    @PostMapping("/prices")
    String prueba(@RequestBody String newEmployee) {
        return "Hola " + newEmployee;
    }
}
