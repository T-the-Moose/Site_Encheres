package fr.eni.siteEncheres.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	private DataSource dataSource;
	
    @Autowired
    public void configureGlobal( AuthenticationManagerBuilder auth ) throws Exception {
        auth.jdbcAuthentication()
                .dataSource( dataSource )
//                .usersByUsernameQuery( "SELECT pseudo, mot_de_passe, 1 FROM utilisateurs WHERE pseudo = ? IN ( pseudo , email )" )
                .usersByUsernameQuery( "SELECT pseudo, mot_de_passe, 1 FROM utilisateurs WHERE pseudo = ?" )
                .authoritiesByUsernameQuery( "SELECT ?, 'admin' " )
                ;
    }


	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> {
			auth
					
					//Permettre aux visiteurs d'accéder à l'accueil
					.requestMatchers(HttpMethod.GET, "/").permitAll()
					.requestMatchers(HttpMethod.GET, "/accueil").permitAll()		
					.requestMatchers(HttpMethod.GET, "/accueil/*").permitAll()					
					//Permettre aux visiteurs d'accéder a la page de connexion
					.requestMatchers(HttpMethod.GET, "/connexion").permitAll()
					//Permettre aux visiteurs d'accéder a la page inscription
					.requestMatchers(HttpMethod.GET, "/inscription").permitAll()
					//Permettre aux visiteurs d'accéder au détail d'un film
//					.requestMatchers(HttpMethod.GET, "/creer").hasRole("MEMBRE")
//					// Accès à la vue principale
//					.requestMatchers("/").permitAll()
//					// Permettre à tous d'afficher correctement les images et CSS
					.requestMatchers("/css/*").permitAll().requestMatchers("/img/*").permitAll().requestMatchers("/script/*").permitAll()
//					// Il faut être connecté pour toutes autres URLs
					.anyRequest().authenticated();
			
					// Tout ouvert
//					.anyRequest().permitAll();
		});
		
//		//formulaire de connexion par défaut
//		http.formLogin(Customizer.withDefaults());

		
		// Customiser le formulaire
			http.formLogin(form -> {
				form.loginPage("/connexion").permitAll();
				form.defaultSuccessUrl("/encheres", true).permitAll();
			});

		// /logout --> vider la session et le contexte de sécurité
			http.logout(logout -> 
					logout
					.invalidateHttpSession(true)
					.clearAuthentication(true)
					.deleteCookies("JSESSIONID")
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessUrl("/").permitAll());

		return http.build();

	}
	
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		var userDetailsService = new InMemoryUserDetailsManager();
//		
//		var user2 = User.withUsername("admin").password("admin123").roles("ADMIN").build();
//		userDetailsService.createUser(user2);
//
//		return userDetailsService;
//	}
	
	@Bean
	JdbcUserDetailsManager users(DataSource dataSource, PasswordEncoder passwordEncoder) {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		return jdbcUserDetailsManager;
	}
	

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
		//return new BCryptPasswordEncoder();
	}
}
