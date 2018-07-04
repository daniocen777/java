/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DANIEL
 */
@Entity
@Table(name = "frases")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Frases.findAll", query = "SELECT f FROM Frases f")
    , @NamedQuery(name = "Frases.findByIdfrase", query = "SELECT f FROM Frases f WHERE f.idfrase = :idfrase")})
public class Frases implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfrase")
    private Integer idfrase;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "frase")
    private String frase;
    @JoinColumn(name = "idautor", referencedColumnName = "idautor")
    @ManyToOne(optional = false)
    private Autores idautor;

    public Frases() {
    }

    public Frases(Integer idfrase) {
        this.idfrase = idfrase;
    }

    public Frases(Integer idfrase, String frase) {
        this.idfrase = idfrase;
        this.frase = frase;
    }

    public Integer getIdfrase() {
        return idfrase;
    }

    public void setIdfrase(Integer idfrase) {
        this.idfrase = idfrase;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public Autores getIdautor() {
        return idautor;
    }

    public void setIdautor(Autores idautor) {
        this.idautor = idautor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfrase != null ? idfrase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Frases)) {
            return false;
        }
        Frases other = (Frases) object;
        if ((this.idfrase == null && other.idfrase != null) || (this.idfrase != null && !this.idfrase.equals(other.idfrase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Frases[ idfrase=" + idfrase + " ]";
    }
    
}
