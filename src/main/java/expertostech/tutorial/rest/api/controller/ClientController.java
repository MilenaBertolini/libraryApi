package expertostech.tutorial.rest.api.controller;

import expertostech.tutorial.rest.api.model.ClientsModel;
import expertostech.tutorial.rest.api.repository.ClientsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {
    @Autowired
    private ClientsRepository clientRepository;

    @GetMapping(path = "/api/clients/{id}")
    public ResponseEntity<ClientsModel> getById(@PathVariable("id") Integer id) {
        return clientRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/api/clients")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok().body(clientRepository.findAll());
    }

}
