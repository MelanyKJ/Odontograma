package com.app.ProyectoOdontograma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.ProyectoOdontograma.model.Paciente;
import com.app.ProyectoOdontograma.service.PacienteService;


@Controller
public class PacienteController {
	@Autowired
	private PacienteService service;
	
	@RequestMapping("/")
	public String viewHomePage(Model model, @Param("keyword") String keyword) {
		List<Paciente> listPacientes = service.listAll(keyword);
		model.addAttribute("listPacientes", listPacientes);
		model.addAttribute("keyword", keyword);
		
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewPacientesForm(Model model) {
		Paciente paciente = new Paciente();
		model.addAttribute("paciente", paciente);
		
		return "new_paciente";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String savePaciente(@ModelAttribute("paciente") Paciente paciente) {
		service.save(paciente);
		
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditPacientesForm(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("edit_paciente");
		
		Paciente paciente = service.get(id);
		mav.addObject("product", paciente);
		
		return mav;
	}	
	
	@RequestMapping("/delete/{id}")
	public String deletePaciente(@PathVariable(name = "id") Long id) {
		service.delete(id);
		
		return "redirect:/";
	}
}

