package it.arancia.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.arancia.repository.IncaricoRepository;
import it.arancia.service.DashboardService;
import it.arancia.service.dto.IdashboardDTO;

/**
 * Service Implementation for managing Dashboard.
 */
@Service
@Transactional
public class DashboardServiceImpl implements DashboardService {
	private final Logger log = LoggerFactory.getLogger(DashboardServiceImpl.class);
	
	@Autowired private IncaricoRepository incaricoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<IdashboardDTO> findByCriteria(String criteria) {
		log.debug("REST request to count DashboardData by criteria: {}", criteria);
		List<IdashboardDTO> result = null;
		if(criteria!=null && "incarico".equals(criteria.toLowerCase())) {
			result = incaricoRepository.findIncaricoDashboard();
		} else {
			result = incaricoRepository.findIncaricoDashboard();
		}
		return result;
	}

	
}
