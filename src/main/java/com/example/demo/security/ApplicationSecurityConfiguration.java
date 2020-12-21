package com.example.demo.security;

import com.example.demo.auth.UserFlxDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfiguration
        extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserFlxDetailsService userFlxDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userFlxDetailsService);
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//        provider.setAuthoritiesMapper(authoritiesMapper());
        return provider;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index", "/css/*", "/js/*")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }


//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        List<UserDetails> users =new ArrayList<>();
//        users.add(User.withDefaultPasswordEncoder().username("john").password("me").roles("USER", "ADMIN").build());
//        users.add(User.withDefaultPasswordEncoder().username("mike").password("you").roles("USER").build());
//        return  new InMemoryUserDetailsManager(users);
//    }
}