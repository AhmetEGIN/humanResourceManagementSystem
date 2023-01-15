package kodlamaio.hrms.business.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import kodlamaio.hrms.core.utilities.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
	private final JwtAuthenticationFilter jwtAuthenticatoinFilter;
	private final AuthenticationProvider authenticationProvider;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.csrf()
		.disable()
		.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/api/amployee/getall").hasRole("EMPLOYEE")
				.requestMatchers("/api/employee/**").permitAll()
				.requestMatchers("/api/auth/**").permitAll()
				.requestMatchers("/api/cv/**").permitAll()
				.anyRequest()
				.authenticated()
				)
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilterBefore(jwtAuthenticatoinFilter, UsernamePasswordAuthenticationFilter.class)
		.authenticationProvider(authenticationProvider);

		
		return httpSecurity.build();
	}
	
	
}