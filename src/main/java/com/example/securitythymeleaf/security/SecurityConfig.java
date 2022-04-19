package com.example.securitythymeleaf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource;

    private static String USUARIO_POR_USERNAME = "select username, password, status from Usuario where username=? ";
    private static String AUTHORITIES_POR_USERNAME = "select u.username, p.perfil from usuario_perfil up inner join Usuario u on u.id = up.Usuario_id inner join Perfil p on p.id = up.Perfil_id " + "where u.username = ?";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(USUARIO_POR_USERNAME)
                .authoritiesByUsernameQuery(AUTHORITIES_POR_USERNAME);
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new FalhaAutenticacaoHandler();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // configs do banco h2
        http.csrf().disable();
        http.headers().frameOptions().disable();
        // final configs do banco h2

        http.authorizeRequests()
                // recursos estáticos todos permitidos
                .antMatchers("/bootstrap/**").permitAll()

                // views públicas todas permitidas
                .antMatchers("/home/publica", "/h2-console", "/h2-console/**").permitAll()

                // rotas personalizadas
                .antMatchers("/").hasAnyAuthority("ADM")
                  .antMatchers("/home").authenticated()
                // as demais precisam de autenticação
                .anyRequest().authenticated()
                // liberando o formulário de login
                .and().formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/home")
                    .failureHandler(authenticationFailureHandler())
                    .permitAll();


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
