/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mibanma.impl;

import com.mibanma.entities.Motexpressionfr;
import com.mibanma.repositories.MotexpressionfrRepos;
import com.mibanma.services.MotexpressionfrService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public class MotexpressionfrImplold implements MotexpressionfrService {

    @Autowired
    MotexpressionfrRepos repos;

    @Override
    public Motexpressionfr create(Motexpressionfr motexpressionfr) {
        return repos.save(motexpressionfr);
    }

    @Override
    public Motexpressionfr update(Motexpressionfr motexpressionfr) {
        return repos.save(motexpressionfr);
    }

    @Override
    public Optional<Motexpressionfr> find(Integer idmotexpressionfr) {
        return repos.findById(idmotexpressionfr);
    }

    @Override
    public void deleted(Integer idmotexpressionfr) {
        repos.deleteById(idmotexpressionfr);
    }

    @Override
    public List<Motexpressionfr> findAll() {
        return repos.findAll();
    }

}
