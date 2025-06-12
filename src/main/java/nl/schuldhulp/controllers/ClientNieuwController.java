package nl.schuldhulp.controllers;

import nl.schuldhulp.model.classes.Client;
import nl.schuldhulp.model.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/client")
public class ClientNieuwController {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/nieuw")
    public ResponseEntity<?> nieuweClient(@RequestBody Client client) {
        Client saved = clientRepository.save(client);
        return ResponseEntity.ok(Map.of(
                "message", "Vastleggen geslaagd",
                "clientnummer", saved.getClientnummer()
        ));
    }
}

