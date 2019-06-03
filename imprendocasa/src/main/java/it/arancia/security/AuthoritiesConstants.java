package it.arancia.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {
    public static final String ANONYMOUS = "ROLE_ANONYMOUS";
    //public static final String USER = "ROLE_USER"; 				// OBBLIGATORIO PER TUTTI I RUOLI OPERATIVI 
    
    public static final String ADMIN = "ROLE_ADMIN";
    public static final String OPERATOR = "ROLE_OPERATOR";
	public static final String AGENT = "ROLE_AGENT";
	public static final String AGENT_PLUS = "ROLE_AGENT_PLUS";
	
	public static final String SUPER_ADMIN = "ROLE_SUPER_ADMIN"; // USO INTERNO

	private AuthoritiesConstants() {
    }
}
