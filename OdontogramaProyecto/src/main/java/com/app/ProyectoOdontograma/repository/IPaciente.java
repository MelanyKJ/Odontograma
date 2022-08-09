package com.app.ProyectoOdontograma.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.app.ProyectoOdontograma.model.Paciente;

public interface IPaciente extends JpaRepository<Paciente, Long> {
	
	@Query("SELECT p FROM Paciente p WHERE CONCAT(p.nombre, ' ', p.apellido) LIKE %?1%")
	public List<Paciente> search(String keyword);
}
