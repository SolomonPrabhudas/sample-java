package com.example.SpringBootTesting.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/students/**").permitAll()  // Allow public access to /students/** URLs
                .anyRequest().authenticated()  // Require authentication for other URLs
        );
        http.httpBasic(AbstractHttpConfigurer::disable);  // Use HTTP Basic authentication
        http.csrf().disable();  // Disable CSRF (for API simplicity)

        return http.build();
    }

    private Customizer<HttpBasicConfigurer<HttpSecurity>> withDefaults() {
		// TODO Auto-generated method stub
		return null;
	}

	@Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);  // Use the defined userDetailsService
        return new ProviderManager(authenticationProvider);  // Return a new ProviderManager with our provider
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user1")
                .password("{noop}secret1")
                .authorities("read")
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin1")
                .password("{noop}secret1")
                .authorities("read")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);  // Define in-memory users
    }
}
