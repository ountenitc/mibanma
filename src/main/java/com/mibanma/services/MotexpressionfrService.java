package com.mibanma.services;

import java.util.List;
import java.util.Optional;

import com.mibanma.entities.Motexpressionfr;

public interface MotexpressionfrService {
	public Motexpressionfr create(Motexpressionfr motexpressionfr);

    public Motexpressionfr update(Motexpressionfr motexpressionfr);

    public Optional<Motexpressionfr> find(Integer idmotexpressionfr);

    public void deleted(Integer idmotexpressionfr);

    public List<Motexpressionfr> findAll();
}
