package cr.ac.una.andrey.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Andrey Vasquez Cespedes
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetails() throws Exception {
        List<UserDetails> users = List.of(
            User.withUsername("user1")
                .password(passwordEncoder().encode("user1"))
                .roles("INVITADO")
                .build(),
            User.withUsername("user2")
                .password(passwordEncoder().encode("user2"))
                .roles("OPERADOR")
                .build(),
            User.withUsername("user3")
                .password(passwordEncoder().encode("user3"))
                .roles("ADMIN")
                .build(),
            User.withUsername("user4")
                .password(passwordEncoder().encode("user4"))
                .roles("ADMIN", "OPERADOR")
                .build()
        );

        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(cr -> cr.disable())
            .authorizeHttpRequests(authz -> authz
                .requestMatchers(HttpMethod.POST, "/cursos/").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/cursos/").hasAnyRole("ADMIN", "OPERADOR")
                .requestMatchers(HttpMethod.DELETE, "/cursos/").hasAnyRole("ADMIN", "OPERADOR")
                .requestMatchers("/cursos/**").authenticated()
                .anyRequest().permitAll()
            )
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}