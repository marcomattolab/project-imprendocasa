package it.arancia.service.util;

import org.springframework.stereotype.Component;

import it.arancia.security.AuthoritiesConstants;
import it.arancia.security.SecurityUtils;
import it.arancia.service.dto.EditableDTO;

@Component
public class EntityProfilerUtils {

	
	public <T extends EditableDTO> void completeInfoEditableDTO(Iterable<T> dtos) {

		boolean isAdmin = SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)
				|| SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.SUPER_ADMIN);
		boolean isAgent = SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.AGENT);
		boolean isAgentPlus = SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.AGENT_PLUS);
		// boolean isOperator =
		// SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.OPERATOR);

		for (T dto: dtos) {

			// L’operatore gestisce tutte le funzioni dell’amministratore ma solo in
			// inserimento e non in modifica/cancellazione .
			boolean editAvaiable = false;
			boolean deleteAvaiable = false;

			if (isAdmin) {
				// L’utente Amministratore ha piena visibilità sull’intera base dati e può
				// modificare qualsiasi dato.
				editAvaiable = true;
				deleteAvaiable = true;
			} else if ((isAgent || isAgentPlus)) {
				// Ogni utente di tipo Agente, per default, può visualizzare, modificare ed
				// eliminare solamente i clienti, gli incarichi e gli immobili creati da lui.
				// Ad ogni Agente può essere attribuita la funzionalità “visualizzazione
				// estesa”, che consente di visualizzare (ma non modificare) tutti gli incarichi
				// e tutti i clienti presenti nella base dati.
				String agente = SecurityUtils.getCurrentUserLogin().get();
				if (dto.getAgenteCreatedBy().equals(agente)) {
					editAvaiable = true;
					deleteAvaiable = true;
				}
			}
			dto.setDeleteAvaiable(deleteAvaiable);
			dto.setEditAvaiable(editAvaiable);
		}
	}
	
}
