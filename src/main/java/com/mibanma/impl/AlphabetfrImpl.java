package com.mibanma.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mibanma.services.AlphabetfrService;
import com.mibanma.entities.Alphabetfr;
import com.mibanma.repositories.AlphabetfrRepos;

@Service
public class AlphabetfrImpl implements AlphabetfrService {
	@Autowired
    AlphabetfrRepos repos;

    @Override
    public Alphabetfr create(Alphabetfr alphabetfr) {
        return repos.save(alphabetfr);
    }

    @Override
    public Alphabetfr update(Alphabetfr alphabetfr) {
        return repos.save(alphabetfr);
    }

    @Override
    public Optional<Alphabetfr> find(Integer idalphabetfr) {
          return repos.findById(idalphabetfr);
  }

    @Override
    public void deleted(Integer idalphabetfr) {
        repos.deleteById(idalphabetfr);
    }

    @Override
    public List<Alphabetfr> findAll() {
         return repos.findAll();
   }
}
