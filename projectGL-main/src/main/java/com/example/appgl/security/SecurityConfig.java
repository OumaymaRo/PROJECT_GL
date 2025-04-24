package com.example.appgl.security;

import com.example.appgl.services.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/register", "/css/**", "/js/**","/responsable/**").permitAll()
                .requestMatchers("/fournisseur/**").hasAuthority("ROLE_FOURNISSEUR")
                .requestMatchers("/enseignant/**").hasAuthority("ROLE_ENSEIGNANT")
                .requestMatchers("/technicien/**").hasAuthority("ROLE_TECHNICIEN")
                .requestMatchers("/responsable/**").hasAuthority("ROLE_RESPONSABLE")
                .requestMatchers("/chef-departement/**").hasAuthority("ROLE_CHEF_DEPARTEMENT")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(authenticationSuccessHandler())
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll()
            )
            .authenticationProvider(authenticationProvider())
            .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            logger.info("Tentative de redirection après authentification");
            logger.info("Nom d'utilisateur: " + authentication.getName());
            logger.info("Rôles de l'utilisateur: " + authentication.getAuthorities());
            
            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_FOURNISSEUR"))) {
                logger.info("Redirection vers le tableau de bord du fournisseur");
                response.sendRedirect("/fournisseur/dashboard");
            } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ENSEIGNANT"))) {
                logger.info("Redirection vers le tableau de bord de l'enseignant");
                response.sendRedirect("/enseignant/dashboard");
            } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_TECHNICIEN"))) {
                logger.info("Redirection vers le tableau de bord du technicien");
                response.sendRedirect("/technicien/dashboard");
            } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_RESPONSABLE"))) {
                logger.info("Redirection vers le tableau de bord du responsable");
                response.sendRedirect("/responsable/dashboard");
            } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CHEF_DEPARTEMENT"))) {
                logger.info("Redirection vers le tableau de bord du chef de département");
                response.sendRedirect("/chef-departement/dashboard");
            } else {
                logger.warn("Aucun rôle trouvé pour l'utilisateur: " + authentication.getName());
                response.sendRedirect("/login?error=no_role");
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
} 