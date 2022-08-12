package expertostech.tutorial.rest.api.controller;

import expertostech.tutorial.rest.api.model.BuysModel;
import expertostech.tutorial.rest.api.repository.BuyRepository;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BuyController {
    @Autowired
    private BuyRepository buyRepository;

    @GetMapping(path = "/api/buy/{id}")
    public ResponseEntity getById(@PathVariable("id") Integer id) {
        return buyRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/api/buy/transactions")
    public ResponseEntity getAll() {
        return ResponseEntity.ok().body(buyRepository.findAll());
    }

    @GetMapping(path = "/api/buy/sells")
    public ResponseEntity getAllSells() {
        ArrayList<BuysModel> sells = new ArrayList<BuysModel>();
        buyRepository.findAll().forEach(arg -> {
            if(arg.isSell())
                sells.add(arg);
        });
        return ResponseEntity.ok().body(sells);
    }

    @GetMapping(path = "/api/buy/buys")
    public ResponseEntity getAllBuys() {
        ArrayList<BuysModel> buys = new ArrayList<BuysModel>();
        buyRepository.findAll().forEach(arg -> {
            if(arg.isBuy())
                buys.add(arg);
        });
        return ResponseEntity.ok().body(buys);
    }
}
