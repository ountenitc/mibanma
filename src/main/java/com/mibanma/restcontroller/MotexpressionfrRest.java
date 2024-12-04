package com.mibanma.restcontroller;

import com.mibanma.entities.Motexpressionfr;
import com.mibanma.impl.MotexpressionfrImplold;
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
@RequestMapping("motexpressionfr/")
public class MotexpressionfrRest {
    
 @Autowired
   MotexpressionfrImplold impl;

    @GetMapping("")
    public ResponseEntity<String> welcomme() {
        return new ResponseEntity<>("Bienvenue dans l'espace des document !!",
                HttpStatus.OK);
    }

    /**
     * liste tous les alertes des expressions se trouvant dans la base de données 
     * @return 
     */
    @GetMapping("all")
    public ResponseEntity<List<Motexpressionfr>> getAll() {
        List<Motexpressionfr> lesMotexpressionfr =  new ArrayList<>();
        try {
            lesMotexpressionfr = impl.findAll();
            return new ResponseEntity<>(lesMotexpressionfr, HttpStatus.OK);
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
    public ResponseEntity<Optional<Motexpressionfr>> getById(
            @PathVariable("id") Integer id) {
        Optional<Motexpressionfr> motexpressionfr;
        try {
            motexpressionfr = impl.find(id);
            return new ResponseEntity<>(motexpressionfr, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    /**
     * crée un objet de type alerte dans la base de données 
     * @param motexpressionfr
     * @return 
     */
    @PostMapping("create")
    private ResponseEntity<Motexpressionfr> create(
            @RequestBody @Valid Motexpressionfr motexpressionfr) {
        Motexpressionfr tmp = new Motexpressionfr();
        String msg = "";
        try {
            tmp = impl.create(motexpressionfr);
            msg = "Enregistrement du texte avec succès.";
            return new ResponseEntity<>(tmp, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
    /**
     * modifier l'objet alerte dont l'identifiant est passé en paramettre 
     * @param id
     * @param motexpressionfr
     * @return 
     */
    @PutMapping("update/{id}")
    public ResponseEntity<Motexpressionfr> updateMotexpressionfr(
            @PathVariable("id") Integer id,
            @RequestBody Motexpressionfr motexpressionfr) {
        try {
            impl.update(motexpressionfr);
            return new ResponseEntity<>(motexpressionfr, HttpStatus.OK);
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