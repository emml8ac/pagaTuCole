package com.pagatucole.PagaTuCole.configuracion;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SeguridadConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf-> csrf.disable())
                .authorizeHttpRequests(auth ->auth.requestMatchers("/public/**").permitAll()
                        .requestMatchers("/css/**", "/js/**", "/image/**","/nopass").permitAll()
                        .requestMatchers("/index").authenticated()
                        .requestMatchers("/admin").hasAnyAuthority("ADMIN").anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login").defaultSuccessUrl("/index",true)
                                .permitAll())
                .rememberMe(rememberMe -> rememberMe
                        .key("uniqueAndSecret")  // Clave secreta para la funcionalidad de "Recuérdame"
                        .tokenValiditySeconds(86400)  // Duración del token en segundos (86400 = 1 día)
                        .rememberMeParameter("remember-me")  // Nombre del parámetro en el formulario (coincide con el checkbox)
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login") // Redirige a la página de inicio tras cerrar sesión
                        .permitAll())
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
