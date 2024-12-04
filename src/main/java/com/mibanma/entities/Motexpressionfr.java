package com.mibanma.entities;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mibanma.entities.Motexpressionfr;
import com.mibanma.entities.Alphabetfr;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@XmlRootElement
@Data
@Table(name = "motexpressionfr", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"idmefr"})})
@NamedQueries({
    @NamedQuery(name = "Motexpressionfr.findAll", query = "SELECT m FROM Motexpressionfr m"),
    @NamedQuery(name = "Motexpressionfr.findByIdmefr", query = "SELECT m FROM Motexpressionfr m WHERE m.idmefr = :idmefr"),
    @NamedQuery(name = "Motexpressionfr.findByFormeecrite", query = "SELECT m FROM Motexpressionfr m WHERE m.formeecrite = :formeecrite"),
    @NamedQuery(name = "Motexpressionfr.findByFormprononce", query = "SELECT m FROM Motexpressionfr m WHERE m.formprononce = :formprononce")})
public class Motexpressionfr {
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmefr", nullable = false)
    private Integer idmefr;
    @Column(name = "formeecrite", length = 254)
    private String formeecrite;
    @Column(name = "formprononce", length = 254)
    private String formprononce;
    @JoinColumn(name = "idalph", referencedColumnName = "idalph")
    @ManyToOne
    private Alphabetfr idalph;
     @JsonIgnore
    @OneToMany(mappedBy = "idmefr")
    private Collection<Motexpressionmr> motexpressionmrCollection;
     
     @XmlTransient
     public Collection<Motexpressionmr> getMotexpressionmrCollection() {
         return motexpressionmrCollection;
     }

     public void setMotexpressionmrCollection(Collection<Motexpressionmr> motexpressionmrCollection) {
         this.motexpressionmrCollection = motexpressionmrCollection;
     }

     @Override
     public int hashCode() {
         int hash = 0;
         hash += (idmefr != null ? idmefr.hashCode() : 0);
         return hash;
     }

     @Override
     public boolean equals(Object object) {
         // TODO: Warning - this method won't work in the case the id fields are not set
         if (!(object instanceof Motexpressionfr)) {
             return false;
         }
         Motexpressionfr other = (Motexpressionfr) object;
         if ((this.idmefr == null && other.idmefr != null) || (this.idmefr != null && !this.idmefr.equals(other.idmefr))) {
             return false;
         }
         return true;
     }

     @Override
     public String toString() {
         return "com.banma.entities.Motexpressionfr[ idmefr=" + idmefr + " ]";
     }

}
