/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mibanma.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
//import lombok.NoArgsConstructor;

/**
 *
 * @author HP
 */
@Entity
@XmlRootElement
@Data
@AllArgsConstructor
@Table(name = "alertetext")
@NamedQueries({
    @NamedQuery(name = "Alertetext.findAll", query = "SELECT a FROM Alertetext a"),
    @NamedQuery(name = "Alertetext.findByIdalerttxt", query = "SELECT a FROM Alertetext a WHERE a.idalerttxt = :idalerttxt"),
    @NamedQuery(name = "Alertetext.findByDatetxt", query = "SELECT a FROM Alertetext a WHERE a.datetxt = :datetxt"),
    @NamedQuery(name = "Alertetext.findByMotexp", query = "SELECT a FROM Alertetext a WHERE a.motexp = :motexp")})
public class Alertetext implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idalerttxt", nullable = false)
    private Integer idalerttxt;
    @Column(name = "datetxt")
    @Temporal(TemporalType.DATE)
    private Date datetxt;
    @Column(name = "motexp", length = 254)
    private String motexp;

    public Alertetext() {
    }

    public Alertetext(Integer idalerttxt) {
        this.idalerttxt = idalerttxt;
    }

    public Integer getIdalerttxt() {
        return idalerttxt;
    }

    public void setIdalerttxt(Integer idalerttxt) {
        this.idalerttxt = idalerttxt;
    }

    public Date getDatetxt() {
        return datetxt;
    }

    public void setDatetxt(Date datetxt) {
        this.datetxt = datetxt;
    }

    public String getMotexp() {
        return motexp;
    }

    public void setMotexp(String motexp) {
        this.motexp = motexp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idalerttxt != null ? idalerttxt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alertetext)) {
            return false;
        }
        Alertetext other = (Alertetext) object;
        if ((this.idalerttxt == null && other.idalerttxt != null) || (this.idalerttxt != null && !this.idalerttxt.equals(other.idalerttxt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rigo.rigo.entities.Alertetext[ idalerttxt=" + idalerttxt + " ]";
    }
    
}
