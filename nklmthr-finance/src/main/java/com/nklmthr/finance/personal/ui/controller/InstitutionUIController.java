package com.nklmthr.finance.personal.ui.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nklmthr.finance.personal.dao.Institution;
import com.nklmthr.finance.personal.service.InstitutionService;

@Controller
@RequestMapping("/")
public class InstitutionUIController {
	Logger logger = Logger.getLogger(InstitutionUIController.class);

	@Autowired
	private InstitutionService institutionService;

	@GetMapping("/Institutions")
	public String getInstitutions(Model m) {
		List<Institution> institutions = institutionService.getAllInstitutions();
		m.addAttribute("institutionList", institutions);
		logger.info("getInstitutions size:" + institutions.size());
		return "institutions/Institutions";
	}

	@GetMapping("/addnewInstitution")
	public String addnewInstitution(Model m) {
		logger.info("addnewInstitution()");
		List<Institution> institutions = institutionService.getAllInstitutions();
		m.addAttribute("institutionList", institutions);
		Institution institution = new Institution();
		m.addAttribute("institution", institution);
		return "institutions/addnewInstitution";
	}

	@PostMapping("/saveInstitution")
	public String addnewInstitution(@ModelAttribute("institution") Institution institution) {
		institutionService.save(institution);
		logger.info("saveInstitution " + institution.getName());
		return "redirect:/Institutions";
	}

	@GetMapping("/showFormForInstitutionUpdate/{id}")
	public String showFormForInstitutionUpdate(@PathVariable(value = "id") String id, Model model) {
		Institution i = institutionService.findInstitutionById(id);
		model.addAttribute(i);
		logger.info("showFormForInstitutionUpdate " + i.getName());
		return "institutions/UpdateInstitution";
	}

	@GetMapping("/deleteInstitution/{id}")
	public String deleteInstitution(@PathVariable(value = "id") String id, Model model) {
		institutionService.deleteInstitutionById(id);
		logger.info("deleteInstitution " + id);
		return "redirect:/Institutions";
	}
}
