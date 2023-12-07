package tn.esprit.SkiStationProject;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {
    @GetMapping
    @PreAuthorize("hasRole('client_user')")
    public String Hello() {
        return "Hello from Spring boot & keycloak";
    }


    @GetMapping("/hello-2")
    @PreAuthorize("hasRole('client_admin')")
    public String Hello2() {
        return "Hello from Spring boot & keycloak-Admin";
    }
}
