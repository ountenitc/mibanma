package com.mibanma.entities;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mibanma.entities.Motexpressionmr;

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
@Data
@Table(name = "langue", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"idlangue_"})})
@NamedQueries({
    @NamedQuery(name = "Langue.findAll", query = "SELECT l FROM Langue l"),
    @NamedQuery(name = "Langue.findByIdlangue", query = "SELECT l FROM Langue l WHERE l.idlangue = :idlangue"),
    @NamedQuery(name = "Langue.findByLiblangue", query = "SELECT l FROM Langue l WHERE l.liblangue = :liblangue"),
    @NamedQuery(name = "Langue.findByAppelationethnie", query = "SELECT l FROM Langue l WHERE l.appelationethnie = :appelationethnie"),
    @NamedQuery(name = "Langue.findByPayslangue", query = "SELECT l FROM Langue l WHERE l.payslangue = :payslangue"),
    @NamedQuery(name = "Langue.findByRegionlangue", query = "SELECT l FROM Langue l WHERE l.regionlangue = :regionlangue"),
    @NamedQuery(name = "Langue.findByNombrelocuteur", query = "SELECT l FROM Langue l WHERE l.nombrelocuteur = :nombrelocuteur")})
public class Langue {
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlangue_", nullable = false)
    private Integer idlangue;
    @Column(name = "liblangue", length = 254)
    private String liblangue;
    @Column(name = "appelationethnie", length = 254)
    private String appelationethnie;
    @Column(name = "payslangue", length = 254)
    private String payslangue;
    @Column(name = "regionlangue", length = 254)
    private String regionlangue;
    @Column(name = "nombrelocuteur", length = 254)
    private String nombrelocuteur;
     @JsonIgnore
    @OneToMany(mappedBy = "idlangue")
    private Collection<Motexpressionmr> motexpressionmrCollection;
}
