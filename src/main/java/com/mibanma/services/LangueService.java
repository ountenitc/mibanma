package com.mibanma.services;

import com.mibanma.entities.Langue;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author HP
 */
public interface LangueService {
    public Langue create(Langue langue);

    public Langue update(Langue langue);

    public Optional<Langue> find(Integer idlangue);

    public void deleted(Integer idlangue);

    public List<Langue> findAll();
}
