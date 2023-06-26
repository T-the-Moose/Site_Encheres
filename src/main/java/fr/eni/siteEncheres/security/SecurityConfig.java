package fr.eni.siteEncheres.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> {
			auth
//					//Permettre aux visiteurs d'accéder à la liste des films
//					.requestMatchers(HttpMethod.GET, "/films").permitAll()
//					//Permettre aux visiteurs d'accéder au détail d'un film
//					.requestMatchers(HttpMethod.GET, "/films/film").permitAll()	
//					//Permettre aux visiteurs d'accéder au détail d'un film
//					.requestMatchers(HttpMethod.GET, "/creer").hasRole("MEMBRE")
//					// Accès à la vue principale
//					.requestMatchers("/").permitAll()
//					// Permettre à tous d'afficher correctement les images et CSS
//					.requestMatchers("/css/*").permitAll().requestMatchers("/images/*").permitAll().requestMatchers("/js/*").permitAll()
//					// Il faut être connecté pour toutes autres URLs
//					.anyRequest().authenticated();
			
					// Tout ouvert
					.anyRequest().permitAll();
		});
		
		//formulaire de connexion par défaut
		http.formLogin(Customizer.withDefaults());

		
		// Customiser le formulaire
//			http.formLogin(form -> {
//				form.loginPage("/login").permitAll();
//				form.defaultSuccessUrl("/session").permitAll();
//			});

		// /logout --> vider la session et le contexte de sécurité
//			http.logout(logout -> 
//					logout
//					.invalidateHttpSession(true)
//					.clearAuthentication(true)
//					.deleteCookies("JSESSIONID")
//					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//					.logoutSuccessUrl("/").permitAll());

		return http.build();

	}
	
	
	@Bean
	public UserDetailsService userDetailsService() {
		var userDetailsService = new InMemoryUserDetailsManager();
		
		var user2 = User.withUsername("admin").password("admin123").roles("ADMIN").build();
		userDetailsService.createUser(user2);

		return userDetailsService;
	}
	
	

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
		// return new BCryptPasswordEncoder();
	}
}
