package com.mibanma.services;

import java.util.List;
import java.util.Optional;

import com.mibanma.entities.Motexpressionmr;

public interface MotexpressionmrService {
	public Motexpressionmr create(Motexpressionmr motexpressionmr);

    public Motexpressionmr update(Motexpressionmr motexpressionfr);

    public Optional<Motexpressionmr> find(Integer idmotexpressionmr);

    public void deleted(Integer idmotexpressionmr);

    public List<Motexpressionmr> findAll();
}
