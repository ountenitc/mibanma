package com.mibanma.services;

import com.mibanma.entities.Alertetext;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author HP
 */
public interface AlertetextService {

    public Alertetext create(Alertetext alertetext);

    public Alertetext update(Alertetext alertetext);

    public Optional<Alertetext> find(Integer idalertetext);

    public void deleted(Integer idalertetext);
    
    public List<Alertetext> findAll();

}
