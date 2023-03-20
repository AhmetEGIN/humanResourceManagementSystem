package kodlamaio.hrms.business.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
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
@EnableAspectJAutoProxy(proxyTargetClass = true)
@RequiredArgsConstructor
public class SecurityConfiguration {
	private final JwtAuthenticationFilter jwtAuthenticatoinFilter;
	private final AuthenticationProvider authenticationProvider;

	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.csrf()
		.disable()
		.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/api/employee/getall").hasRole("EMPLOYEE")
				.requestMatchers(
						"/api/employee/**",
						"/api/employer/**",
						"/api/city/**",
						"/api/coverletter/**",
						"/api/cv/**",
						"/api/githubAccount/**",
						"/api/image/**",
						"/api/jobadvertisement/**",
						"/api/jobExperience/**",
						"/api/language/**",
						"/api/linkedinAccount/**",
						"/api/position/**",
						"/api/school/**",
						"/api/technology/**",
						"/v3/api-docs",
						"/swagger-resources/**",
						"/swagger-ui.html",
						"/swagger-ui/**",
						"/swagger-ui/index.html",
						"/webjars/**",
						"/swagger.json",
						"/configuration/ui",
						"/configuration/security"
						).permitAll()
//				.requestMatchers("/**").permitAll()
				.requestMatchers("/api/auth/**").permitAll()
				.anyRequest()
				.authenticated()
				)
		
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.formLogin()
		.and()
		.addFilterBefore(jwtAuthenticatoinFilter, UsernamePasswordAuthenticationFilter.class)
		.authenticationProvider(authenticationProvider);


		
		return httpSecurity.build();
	}
	

	
	
}
