package it.arancia.service.impl;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import it.arancia.config.SchedulerProperties;
import it.arancia.domain.enumeration.StatoIncarico;
import it.arancia.service.IncaricoService;
import it.arancia.service.SchedulerService;
import it.arancia.service.dto.IncaricoDTO;

/**
 * Service Implementation for managing Partner.
 */
@Service
@Transactional
public class SchedulerServiceImpl implements SchedulerService {
    private final Logger log = LoggerFactory.getLogger(SchedulerServiceImpl.class);

    @Autowired 
    private SchedulerProperties schedulerProperties;

    @Autowired 
    private IncaricoService incaricoService;
    
//	@Override
//	@Scheduled(cron = "0 0/1 * * * ?")
//	public void testCurrentTime() {
//		 SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss a");
//		 log.info("## Schedule tasks using cron jobs " + dateFormat.format(new Date()));
//	}

	@Override
	@Scheduled(cron = "0 0/60 * * * ?")
	public void updateWorkflow() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss a");
		if (schedulerProperties.getEnabled()!=null && schedulerProperties.getEnabled().booleanValue()) {
			log.info("## UpdateWorkflow using cron jobs " + dateFormat.format(new Date())+" is enabled. Processing...");
			
			Instant nowInstantTH = getInstantTH();
			List<IncaricoDTO> incarichiInBozza = incaricoService.findAllDraft();
			if ( !CollectionUtils.isEmpty(incarichiInBozza)) {
				for(IncaricoDTO incaricoDTO: incarichiInBozza) {
					if ( incaricoDTO!=null && nowInstantTH.isBefore( incaricoDTO.getCreatedDate() ) ) {
						log.info("## Change status Bozza -> Attivo for IncaricoId: " + incaricoDTO.getId());
						
						//Change status to DRAFT => ACTIVE and save
						incaricoDTO.setStato(StatoIncarico.ATTIVO);
						incaricoService.save(incaricoDTO);
					} else {
						log.info("## Skipped change status Bozza -> Attivo for IncaricoId: " + incaricoDTO.getId() + " !");
					}
				}
			} else {
				log.info("## UpdateWorkflow no DRAFT has been found on DB.");
			}
		} else {
			log.info("## UpdateWorkflow using cron jobs " + dateFormat.format(new Date()) + " is NOT enabled. SKIPPED!");
		}
	}

	/**
	 * @return ==> Now - Threshould
	 */
	private Instant getInstantTH() {
		Instant nowInstant = Instant.now();
		long deltaMillisTh = schedulerProperties.getHours().longValue() * 60 * 1000;
		return nowInstant.minusMillis(deltaMillisTh);
	}
	
}
