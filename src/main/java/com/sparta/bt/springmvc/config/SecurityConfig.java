package com.sparta.bt.springmvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public SecurityConfig(DataSource dataSource, BCryptPasswordEncoder encoder) {
        this.dataSource = dataSource;
        this.encoder = encoder;
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //IN MEMORY AUTHENTICATION IS SEEN BELOW
//        auth.inMemoryAuthentication().withUser("admin").password("{noop}password").authorities("ADMIN");
//        auth.inMemoryAuthentication().withUser("user").password("{noop}password").authorities("USER");
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select user_name,password,user_enabled from user_entity where user_name=?")
                .authoritiesByUsernameQuery("select user_name,role from user_entity where user_name=?")
                .passwordEncoder(encoder);


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/search-customer.html").hasAnyAuthority("ADMIN")
                .antMatchers("/css/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/",true)
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied")
                .and().logout();
    }
}
