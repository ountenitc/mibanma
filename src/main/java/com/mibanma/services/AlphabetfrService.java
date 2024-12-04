package com.mibanma.services;

import java.util.List;
import java.util.Optional;

import com.mibanma.entities.Alphabetfr;

public interface AlphabetfrService {
	public Alphabetfr create(Alphabetfr alphabetfr);

    public Alphabetfr update(Alphabetfr alphabetfr);

    public Optional<Alphabetfr> find(Integer idalphabetfr);

    public void deleted(Integer idalphabetfr);
    
    public List<Alphabetfr> findAll();
}
