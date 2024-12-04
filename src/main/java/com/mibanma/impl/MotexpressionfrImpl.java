package com.mibanma.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mibanma.entities.Motexpressionfr;
import com.mibanma.repositories.MotexpressionfrRepos;
import com.mibanma.services.MotexpressionfrService;

@Service
public class MotexpressionfrImpl implements MotexpressionfrService{
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
