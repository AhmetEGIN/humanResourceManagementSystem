package kodlamaio.hrms.core.utilities.security;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenHelper {
	String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);
	String generateToken(UserDetails userDetails);
	String extractUsername(String token);
}
