package com.site.MDL.controller;
import java.util.List;

import com.site.MDL.model.Fourniseur;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class FournisseurController {
    @Autowired
    ProducerTemplate producerTemplate;

    @RequestMapping(value = "/fournisseur", method = RequestMethod.GET)
    public List<Fourniseur> getAllFourniseur() {
        List<Fourniseur> fourniseur = producerTemplate.requestBody("direct:select1", null, List.class);
        return fourniseur;

    }

    @RequestMapping(value = "/fournisseur", consumes = "application/json", method = RequestMethod.POST)
    public boolean insertFournisseur(@RequestBody Fourniseur fourniseur) {
        producerTemplate.requestBody("direct:insert1", fourniseur, List.class);
        return true;
    }
}
