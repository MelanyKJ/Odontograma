package com.app.ProyectoOdontograma.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "Tratamiento")
@NamedQueries(value = {
    @NamedQuery(name = "Tratamiento.buscarPorOdontogramaYConsulta",
            query = "select t FROM Tratamiento t JOIN t.diente d"
            + " WHERE d.odontograma.id = :odontogramaId"           
            + " ORDER BY t.id"),
    @NamedQuery(name = "Tratamiento.buscarPorDienteYNombre",
            query = "select t FROM Tratamiento t "
            + " WHERE t.diente.id = :dienteId"
            + " AND t.nombre = :nombreTratamiento"
            + " ORDER BY t.id")})
public class Tratamiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaRealizacion;
    private String nombre;
    private String procedimiento;    
    private String nombreAux;    
    private String cua;
    

    @ManyToOne()
    @JoinColumn(name = "diente_id")
    private Diente diente;


    public Tratamiento() {
    }

    public Tratamiento(String nombre) {
        this.nombre = nombre;        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreAux() {
        return nombreAux;
    }

    public void setNombreAux(String nombreAux) {
        this.nombreAux = nombreAux;
    }    

    public String getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(String procedimiento) {
        this.procedimiento = procedimiento;
    }

    public Diente getDiente() {
        return diente;
    }

    public void setDiente(Diente diente) {
        this.diente = diente;
    }

    public String getCua() {
        return cua;
    }

    public void setCua(String cua) {
        this.cua = cua;
    }
  
    
    public boolean isCuandrante(String c){
         List<String> listaC = Arrays.asList(cua.split(","));        
        if(listaC.contains(c)){
            return true;
        }
        return false;
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

        if (!(object instanceof Tratamiento)) {
            return false;
        }
        Tratamiento other = (Tratamiento) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.app.Odontograma.model.Tratamiento[ "
                + "id=" + getId() + ","
                + "nombre=" + getNombre() + ","
                + "fecha" + getFechaRealizacion() + ","
                + " ]";
    }

}
