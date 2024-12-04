package com.mibanma.entities;

import com.mibanma.entities.Langue;
import com.mibanma.entities.Motexpressionfr;
import com.mibanma.entities.Motexpressionmr;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Data
@Table(name = "motexpressionmr", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"idmemr"})})
@NamedQueries({
    @NamedQuery(name = "Motexpressionmr.findAll", query = "SELECT m FROM Motexpressionmr m"),
    @NamedQuery(name = "Motexpressionmr.findByIdmemr", query = "SELECT m FROM Motexpressionmr m WHERE m.idmemr = :idmemr"),
    @NamedQuery(name = "Motexpressionmr.findByFormecritmr", query = "SELECT m FROM Motexpressionmr m WHERE m.formecritmr = :formecritmr"),
    @NamedQuery(name = "Motexpressionmr.findByUrlAudio", query = "SELECT m FROM Motexpressionmr m WHERE m.urlaudio = :urlaudio"),
    @NamedQuery(name = "Motexpressionmr.findByFormprononcemr", query = "SELECT m FROM Motexpressionmr m WHERE m.formprononcemr = :formprononcemr")})
public class Motexpressionmr {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmemr", nullable = false)
    private Integer idmemr;
    @Column(name = "formecritmr", length = 1024)
    private String formecritmr;
    @Column(name = "formprononcemr", length = 1024)
    private String formprononcemr;
    @Column(name = "urlaudio", length = 100)    
    private String urlaudio;
    @Column(name = "audiocontent")
    private byte[] audiocontent; 
    @JoinColumn(name = "idlangue_", referencedColumnName = "idlangue_")
    @ManyToOne
    private Langue idlangue;
    @JoinColumn(name = "idmefr", referencedColumnName = "idmefr")
    @ManyToOne
    private Motexpressionfr idmefr;
    
    public Langue getIdlangue() {
        return idlangue;
    }

    public void setIdlangue(com.mibanma.entities.Langue idlangue) {
        this.idlangue = idlangue;
    }

    public Motexpressionfr getIdmefr() {
        return idmefr;
    }

    public void setIdmefr(Motexpressionfr idmefr) {
        this.idmefr = idmefr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmemr != null ? idmemr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Motexpressionmr)) {
            return false;
        }
        Motexpressionmr other = (Motexpressionmr) object;
        if ((this.idmemr == null && other.idmemr != null) || (this.idmemr != null && !this.idmemr.equals(other.idmemr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.banma.entities.Motexpressionmr[ idmemr=" + idmemr + " ]";
    }
}
