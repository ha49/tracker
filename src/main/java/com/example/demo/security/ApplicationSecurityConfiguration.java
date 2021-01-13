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
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;





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
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
         provider.setAuthoritiesMapper(authoritiesMapper());
        return provider;

    }

    @Bean
    public GrantedAuthoritiesMapper authoritiesMapper(){
        SimpleAuthorityMapper authorityMapper=new SimpleAuthorityMapper();
        authorityMapper.setConvertToLowerCase(true);
        authorityMapper.setDefaultAuthority("USER");
        return authorityMapper;
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

//                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/coach/**").hasRole("coach")
                .antMatchers("/client/**").hasRole("client")

                .antMatchers("/","static/css", "static/js").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll();
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
