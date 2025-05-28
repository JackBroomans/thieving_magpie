package nl.schuldhulp.controllers;

import nl.schuldhulp.services.ClientnummerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientnummer")
public class ClientnummerController {

    @Autowired
    private ClientnummerService clientnummerService;

    @GetMapping
    public ResponseEntity<String> genereerClientnummer() {
        String nummer = clientnummerService.getNieuwClientnummer();
        return ResponseEntity.ok(nummer);
    }
}
