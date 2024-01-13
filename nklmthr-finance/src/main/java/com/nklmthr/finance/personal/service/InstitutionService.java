package com.nklmthr.finance.personal.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nklmthr.finance.personal.dao.Institution;
import com.nklmthr.finance.personal.dao.InstitutionRepository;

@Service
public class InstitutionService {

	private static Logger logger = Logger.getLogger(InstitutionService.class);

	@Autowired
	private InstitutionRepository institutionRepository;

	public List<Institution> getAllInstitutions() {
		return institutionRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "name"));
	}

	public boolean save(Institution institution) {
		institutionRepository.save(institution);
		return true;
	}

	public Institution findInstitutionById(String id) {
		Optional<Institution> inst = institutionRepository.findById(id);
		if (inst.isPresent()) {
			return inst.get();
		}
		return null;
	}

	public boolean deleteInstitutionById(String id) {
		Optional<Institution> inst = institutionRepository.findById(id);
		if (inst.isPresent()) {
			institutionRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
