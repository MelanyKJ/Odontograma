package com.app.ProyectoOdontograma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.ProyectoOdontograma.model.Paciente;
import com.app.ProyectoOdontograma.repository.IPaciente;


@Service
public class PacienteService {
	@Autowired
	private IPaciente repo;
	
	public List<Paciente> listAll(String keyword) {
		if (keyword != null) {
			return repo.search(keyword);
		}
		return repo.findAll();
	}
	
	public void save(Paciente paciente) {
		repo.save(paciente);
	}
	
	public Paciente get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
