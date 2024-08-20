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
                        .requestMatchers("/css/**", "/js/**", "/image/**").permitAll()
                        .requestMatchers("/pv/**").authenticated()
                        .requestMatchers("/admin").hasAnyAuthority("ADMIN").anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login").defaultSuccessUrl("/pv/index",true)
                                .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/public/principal") // Redirige a la página de inicio tras cerrar sesión
                        .permitAll())
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
