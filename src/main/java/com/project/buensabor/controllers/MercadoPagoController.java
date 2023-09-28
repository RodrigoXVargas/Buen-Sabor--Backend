package com.project.buensabor.controllers;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import com.project.buensabor.exceptions.CustomException;
import com.project.buensabor.services.MercadoPagoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/mp")
@Slf4j
public class MercadoPagoController {

    @Autowired
    private MercadoPagoService mercadoPagoService;

    @PreAuthorize("hasAnyAuthority('mp:createPreference','_superAdmin')")
    @PostMapping(value = "/create-preference/{idOrder}")
    public ResponseEntity<?> createPreference(@PathVariable Long idOrder) {
        try {
            log.info("CREATE PREFERENCE");
            Preference preference = mercadoPagoService.createPreference(idOrder);
            return ResponseEntity.ok().body(preference);
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.internalServerError().body("Error al crear preferencia: " + e.getMessage());
        }
    }

}
