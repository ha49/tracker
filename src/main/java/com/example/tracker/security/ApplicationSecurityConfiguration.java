package com.example.tracker.security;

import com.example.tracker.auth.UserFlx;
import com.example.tracker.auth.UserFlxDetailsService;
import com.example.tracker.security.jwt.config.JwtAuthenticationEntryPoint;
import com.example.tracker.security.jwt.config.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,
        jsr250Enabled = true, prePostEnabled = true
)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserFlxDetailsService userFlxDetailsService;
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private JwtRequestFilter jwtRequestFilter;

    public ApplicationSecurityConfiguration(UserFlxDetailsService userFlxDetailsService, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                                            JwtRequestFilter jwtRequestFilter) {
        this.userFlxDetailsService = userFlxDetailsService;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userFlxDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        provider.setAuthoritiesMapper(authoritiesMapper());
        return provider;

    }


    @Bean
    public GrantedAuthoritiesMapper authoritiesMapper() {
        SimpleAuthorityMapper authorityMapper = new SimpleAuthorityMapper();
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
                /*                .csrf().disable()
                                .authorizeRequests()

                                //                .antMatchers("/admin").hasRole("ADMIN")
                                .antMatchers("/coach/**").hasRole("coach")
                                .antMatchers("/client/**").hasRole("client")
                                .antMatchers("/clientpage").hasRole("client")
                                .antMatchers("/coachpage").hasRole("coach")
                                .antMatchers("/member/new").hasRole("client")
                                .antMatchers("/member/**").hasRole("coach")

                                .antMatchers("/", "static/css", "static/js").permitAll()
                                .anyRequest().authenticated()
                                .and()
                                .formLogin()
                                .loginPage("/login")
                                .permitAll()
                                .and()
                                .logout()
                                .invalidateHttpSession(true)
                                .clearAuthentication(true)
                                .permitAll();*/


                .csrf().disable()
                .authorizeRequests()
                //
                .antMatchers("/home", "/application", "/user/createUser", "/user/createCoach",
                        "/link/**", "/member/**", "/tracking",
                        "/client/delete/**", "/authenticate").permitAll()
                .antMatchers("/admin").permitAll()
                .antMatchers("/clientpage").hasRole("client")

                .antMatchers("/adminpage").hasRole("admin")
                .antMatchers("/client/**").hasAnyRole("client", "coach")
                .antMatchers("/coachpage").hasRole("coach")

                .anyRequest().permitAll()
                .and()
//                .formLogin()
//                .loginPage("/login").permitAll()
//                .and()
//                .logout()
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .permitAll();
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
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
