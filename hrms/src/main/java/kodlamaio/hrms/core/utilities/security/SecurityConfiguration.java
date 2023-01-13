package kodlamaio.hrms.core.utilities.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import kodlamaio.hrms.core.utilities.security.jwt.JwtAuthenticatoinFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
	private final JwtAuthenticatoinFilter jwtAuthenticatoinFilter;
	private final AuthenticationProvider authenticationProvider;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.csrf()
		.disable()

		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilterBefore(jwtAuthenticatoinFilter, UsernamePasswordAuthenticationFilter.class)
		.authenticationProvider(authenticationProvider)
		.authorizeHttpRequests()
		.antMatchers("/api/auth/**").permitAll()
		.antMatchers("/api/employee/**").hasRole("EMPLOYEE")
		.anyRequest().authenticated();
		
		return httpSecurity.build();
	}
	
	
}
