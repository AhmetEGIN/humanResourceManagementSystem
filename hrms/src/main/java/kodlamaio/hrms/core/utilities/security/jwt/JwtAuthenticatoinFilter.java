package kodlamaio.hrms.core.utilities.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticatoinFilter extends OncePerRequestFilter {
	  private final JwtService jwtService;
	  private final UserDetailsService userDetailsService;

	  // bir tane  filtre ekleyeceğiz. İçeriye gelen request leri kontrol edecek.
	  // bunun için OncePerRequestFilter class ını extend ederiz. Bu class ın içerisindeli doFilterInternal 
	  // metodunu override ederiz.
	  // Bu class ın Component olması gerekir
	  
	  @Override
	  protected void doFilterInternal(
	      @NonNull HttpServletRequest request,
	      @NonNull HttpServletResponse response,
	      @NonNull FilterChain filterChain
	  ) throws ServletException, IOException {
	    final String authHeader = request.getHeader("Authorization");
	    // öncelikle request in üzerinden getHeader kısmından Authorization kısmını alırız.
	    // Bu değişkenin içerisinde mesajlar şöyle durur : "Bearer <token>"
	    // biz bu authHeader ın token kısmı ile ilgileniyoruz
	    
	    final String jwt;
	    final String userEmail;
	    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
	      filterChain.doFilter(request, response);
	      return;
	    }
	    //eğer authHeader null değilse ve authHeader Bearer ile başlıyorsa token işlemleri başlar
	    
	    // authHeader'ı 7. karakterden itibaren alırsak token ı elde etmiş olacağız.
	    jwt = authHeader.substring(7);
	    
	    // daha sonra bu token ı kontrol edeceğiz. Bunun için gerekli metotları JwtService isimli class'ta topladık
	    userEmail = jwtService.extractUsername(jwt);
	    // SecurityContextHolder -- bunun yardımıyla bu kullanıcının daha önce sisteme login oluğ olmadığını tespit eder.
	    if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	      UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
	      // Önce token ın valid olup olmadığını kontrol ediyoruz
	      if (jwtService.isTokenValid(jwt, userDetails)) {
	    	  // 
	        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
	            userDetails,
	            null,
	            userDetails.getAuthorities()
	        );
	        authToken.setDetails(
	            new WebAuthenticationDetailsSource().buildDetails(request)
	        );
	        SecurityContextHolder.getContext().setAuthentication(authToken);
	      }
	    }
	    filterChain.doFilter(request, response);
	  }
	
}
