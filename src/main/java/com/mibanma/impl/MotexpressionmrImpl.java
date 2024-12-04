package com.mibanma.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mibanma.services.MotexpressionmrService;
import com.mibanma.entities.Motexpressionmr;
import com.mibanma.repositories.MotexpressionmrRepos;

@Service
public class MotexpressionmrImpl implements MotexpressionmrService{
	@Autowired
    MotexpressionmrRepos repos;

    @Override
    public Motexpressionmr create(Motexpressionmr motexpressionmr) {
        return repos.save(motexpressionmr);
    }

    @Override
    public Motexpressionmr update(Motexpressionmr motexpressionfr) {
        return repos.save(motexpressionfr);
    }

    @Override
    public Optional<Motexpressionmr> find(Integer idmotexpressionmr) {
        return repos.findById(idmotexpressionmr);
    }

    @Override
    public void deleted(Integer idmotexpressionmr) {
        repos.deleteById(idmotexpressionmr);
    }

    @Override
    public List<Motexpressionmr> findAll() {
        return repos.findAll();
    }

    public String findByMotexpressionfr(String motexpressionmr){
        return repos.findByMotexpressionfr(motexpressionmr.toLowerCase()).getFormecritmr();
    }
    
    public String findByUrlaudio(String motexpressionmr) {
    	return repos.findByUrlaudio(motexpressionmr).getUrlaudio();
    }
    public Integer findIdmemr(String motexpressionmr) {
    	return repos.findIdmemr(motexpressionmr).getIdmemr();
    }
    public void updateUrlaudio(Integer idmemr, String urlaudio)
    {
    	repos.updateUrlaudio(idmemr, urlaudio);
    }
    public Motexpressionmr findByAudio(String urlaudio)
    {
    	return repos.findByAudio(urlaudio);
    }
}
