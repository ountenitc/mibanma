package com.mibanma.restcontroller;

import com.mibanma.entities.Alphabetfr;
import com.mibanma.impl.AlphabetfrImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author HP
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("alphabetfr/")
public class AlphabetfrRest {  
    
    @Autowired
    AlphabetfrImpl impl;

    @GetMapping("")
    public ResponseEntity<String> welcomme() {
        return new ResponseEntity<>("Bienvenue dans l'espace des alphabet francais!!",
                HttpStatus.OK);
    }

    /**
     * liste tous les alertes des expressions se trouvant dans la base de données 
     * @return 
     */
    @GetMapping("all")
    public ResponseEntity<List<Alphabetfr>> getAll() {
        List<Alphabetfr> lesAlphabetfr =  new ArrayList<>();
        try {
            lesAlphabetfr = impl.findAll();
            return new ResponseEntity<>(lesAlphabetfr, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * obtenir un objet alerte en fonction de son identifiant(id) 
     * @param id
     * @return 
     */
    @GetMapping("{id}")
    public ResponseEntity<Optional<Alphabetfr>> getById(
            @PathVariable("id") Integer id) {
        Optional<Alphabetfr> alphabetfr;
        try {
            alphabetfr = impl.find(id);
            return new ResponseEntity<>(alphabetfr, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    /**
     * crée un objet de type alerte dans la base de données 
     * @param alphabetfr
     * @return 
     */
    @PostMapping("create")
    private ResponseEntity<String> create(
            @RequestBody @Valid Alphabetfr alphabetfr) {
        Alphabetfr tmp = new Alphabetfr();
        String msg = "";
        try {
            tmp = impl.create(alphabetfr);
            msg = "Enregistrement de l'alphabet : " + tmp.getLettre() + " avec succès.";
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
    /**
     * modifier l'objet alerte dont l'identifiant est passé en paramettre 
     * @param id
     * @param alphabetfr
     * @return 
     */
    @PutMapping("update/{id}")
    public ResponseEntity<Alphabetfr> updateAlphabetfr(
            @PathVariable("id") Integer id,
            @RequestBody Alphabetfr alphabetfr) {
        try {
            impl.update(alphabetfr);
            return new ResponseEntity<>(alphabetfr, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
    
    @DeleteMapping("delete/{id}")
    private ResponseEntity<String> delete(
           @PathVariable("id") Integer id) {
        String msg = "";
        try {
            impl.deleted(id);
            msg = "Suppression effectuée avec succès.";
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
}
