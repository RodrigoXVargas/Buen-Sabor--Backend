package com.project.buensabor.controllers.SecurityController;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class SecurityContr {

    @GetMapping(value = "/public")
    public ResponseEntity<?> publicEndPoint() {
        return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"All good. You DO NOT need tobe authenticated to call this endpoint.\"}");
    }

    @GetMapping(value = "/private")
    public ResponseEntity<?> privateEndPoint() {
        return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"All good. You can see this because you are Authenticated.\"}");
    }

    @GetMapping(value = "/admin-only")
    @PreAuthorize("hasAuthority('adminPermission')")
    public ResponseEntity<?> adminOnlyEndPoint() {
        return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"All good. You can see this because you are Admin.\"}");
    }


}
