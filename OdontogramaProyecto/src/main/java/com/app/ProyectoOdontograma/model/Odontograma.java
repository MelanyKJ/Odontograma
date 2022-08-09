package com.app.ProyectoOdontograma.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Odontograma")
@NamedQueries(value = {
    @NamedQuery(name = "Odontograma.buscarPorId",
            query = "select d FROM Odontograma d "
            + " WHERE d.id = :odontogramaId"            
            + " ORDER BY d.id")    
    })
public class Odontograma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String observacion;
    @OneToMany(mappedBy = "odontograma", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Diente> dientes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public List<Diente> getDientes() {
        return dientes;
    }

    public void setDientes(List<Diente> dientes) {
        for (Diente d : dientes) {
            d.setOdontograma(this);
        }
        this.dientes = dientes;
    }

    public void agregarDiente(Diente d) {
        if (!this.dientes.contains(d)) {
            d.setOdontograma(this);
            this.dientes.add(d);
        }
    }

    public boolean isPersistent() {
        return getId() != null;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Odontograma)) {
            return false;
        }
        Odontograma other = (Odontograma) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "edu.sgssalud.model.odontologia.Odontograma[ id=" + id + " ]";
    }
}