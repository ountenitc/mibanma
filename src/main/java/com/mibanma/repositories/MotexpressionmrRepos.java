package com.mibanma.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mibanma.entities.Motexpressionmr;

public interface MotexpressionmrRepos extends JpaRepository<Motexpressionmr, Integer> {
	@Query(value = "SELECT m FROM Motexpressionmr m WHERE LOWER(m.idmefr.formeecrite)=LOWER(?1)")
    Motexpressionmr findByMotexpressionfr(String formeecrite);
	@Query(value = "SELECT m FROM Motexpressionmr m WHERE LOWER(m.idmefr.formeecrite)=LOWER(?1)")	
	Motexpressionmr findByUrlaudio(String formeecrite);
	@Query(value = "SELECT m FROM Motexpressionmr m WHERE LOWER(m.idmefr.formeecrite)=LOWER(?1)")	
	Motexpressionmr findIdmemr(String formeecrite);
	@Modifying
	@Query("update Motexpressionmr m set m.urlaudio = ?2 where m.idmemr=?1")
	void updateUrlaudio(Integer idmemr, String urlaudio);
	@Query(value = "SELECT m FROM Motexpressionmr m  WHERE m.urlaudio = :urlaudio")
	Motexpressionmr findByAudio(String urlaudio);
}
