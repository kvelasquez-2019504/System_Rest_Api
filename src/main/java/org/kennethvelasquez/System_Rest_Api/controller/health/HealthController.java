package org.kennethvelasquez.System_Rest_Api.controller.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthController {

    @GetMapping("/health")
    public String checkApi() {
        return "<h1>The API is working ok!</h1>";
    }
}
