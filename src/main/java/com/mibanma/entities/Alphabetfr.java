package com.mibanma.entities;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@XmlRootElement
@Data
@Table(name = "alphabetfr", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"idalph"})})
@NamedQueries({
    @NamedQuery(name = "Alphabetfr.findAll", query = "SELECT a FROM Alphabetfr a"),
    @NamedQuery(name = "Alphabetfr.findByIdalph", query = "SELECT a FROM Alphabetfr a WHERE a.idalph = :idalph"),
    @NamedQuery(name = "Alphabetfr.findByLettre", query = "SELECT a FROM Alphabetfr a WHERE a.lettre = :lettre"),
    @NamedQuery(name = "Alphabetfr.findByPhonetalph", query = "SELECT a FROM Alphabetfr a WHERE a.phonetalph = :phonetalph")})
public class Alphabetfr {
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idalph", nullable = false)
    private Integer idalph;
    @Column(name = "lettre")
    private Character lettre;
    @Column(name = "phonetalph", length = 254)
    private String phonetalph;
     @JsonIgnore
    @OneToMany(mappedBy = "idalph")
    private Collection<Motexpressionfr> motexpressionfrCollection;
}
