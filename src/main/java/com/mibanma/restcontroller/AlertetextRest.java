package com.mibanma.restcontroller;

import com.mibanma.entities.Alertetext;
import com.mibanma.impl.AlertetextImpl;
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
@RequestMapping("alertetext/")
public class AlertetextRest {

    @Autowired
    AlertetextImpl impl;

    @GetMapping("")
    public ResponseEntity<String> welcomme() {
        return new ResponseEntity<>("Bienvenue dans l'espace des alertes des textes!!",
                HttpStatus.OK);
    }

    /**
     * liste tous les alertes des expressions se trouvant dans la base de
     * données
     *
     * @return
     */
    @GetMapping("all")
    public ResponseEntity<List<Alertetext>> getAll() {
        List<Alertetext> lesAlertetext = new ArrayList<>();
        try {
            lesAlertetext = impl.findAll();
            return new ResponseEntity<>(lesAlertetext, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * obtenir un objet alerte en fonction de son identifiant(id)
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<Optional<Alertetext>> getById(
            @PathVariable("id") Integer id) {
        Optional<Alertetext> alertetext;
        try {
            alertetext = impl.find(id);
            return new ResponseEntity<>(alertetext, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    /**
     * crée un objet de type alerte dans la base de données
     *
     * @param alertetext
     * @return
     */
    @PostMapping("create")
    private ResponseEntity<String> create(
            @RequestBody @Valid Alertetext alertetext) {
        Alertetext tmp = new Alertetext();
        String msg = "";
        try {
            tmp = impl.create(alertetext);
            msg = "Enregistrement de l'alerte du text : " + tmp.getMotexp() + " avec succès.";
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    /**
     * modifier l'objet alerte dont l'identifiant est passé en paramettre
     *
     * @param id
     * @param alertetext
     * @return
     */
    @PutMapping("update/{id}")
    public ResponseEntity<Alertetext> updateAlertetext(
            @PathVariable("id") Integer id,
            @RequestBody Alertetext alertetext) {
        try {
            impl.update(alertetext);
            return new ResponseEntity<>(alertetext, HttpStatus.OK);
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
