package it.arancia.service;

/**
 * Service Interface for managing Scheduler .
 */
public interface SchedulerService {
	
	/**
	 * Test Current Time
	 * Note: Task is scheduled every 1 minute
	 */
	//public void testCurrentTime();

	/**
	 * Update Workflow about Immobile from BOZZA to ATTIVO after N minutes from Immobile creationTime
	 * Note: Task is scheduled every 1 minute 
	 */
	public void updateWorkflow();
	
}
