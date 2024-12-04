/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mibanma.impl;

import com.mibanma.entities.Langue;
import com.mibanma.repositories.LangueRepos;
import com.mibanma.services.LangueService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public class LangueImpl implements LangueService {

    @Autowired
    LangueRepos repos;

    @Override
    public Langue create(Langue langue) {
        return repos.save(langue);
    }

    @Override
    public Langue update(Langue langue) {
        return repos.save(langue);
    }

    @Override
    public Optional<Langue> find(Integer idlangue) {
        return repos.findById(idlangue);
    }

    @Override
    public void deleted(Integer idlangue) {
        repos.deleteById(idlangue);
    }

    @Override
    public List<Langue> findAll() {
        return repos.findAll();
    }

}
