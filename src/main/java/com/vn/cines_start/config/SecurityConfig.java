package com.vn.cines_start.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.vn.cines_start.Model.User;
import com.vn.cines_start.Service.Iplm.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //permit all url
                .authorizeRequests()
                .antMatchers("/","/home", "/currentMovie","/upcomingMovie","/schedule","/movieDetail", "/forgotpassword", "/register", "/login").permitAll()
                .antMatchers("/bookingTicket","/billInfor","/billUser","/admin/**").hasRole("ADMIN")
                .antMatchers("/bookingTicket","/billInfor","/billUser").hasRole("USER")
                .anyRequest()
                .authenticated()


                //check login
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error= true")

                //when you logout
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies( "JSESSIONID")

                //declare exeption
                .and()
                .exceptionHandling().accessDeniedPage("/403")

                //thymeleaf already has token, so disable csrf
                .and()
                .csrf()
                .disable();
        http.headers().frameOptions().disable();
    }//config authenication & authorization

    @Bean
    public PasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }//ma hoa password

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(bCryptPasswordEncoder());
    }//chon class quan li thong tin account

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**",
                "/static/**",
                "/images/**",
                "/img/**",
                "/css/**",
                "/js/**");
    }//bo qua authen cac package nay
    
    public static User getPrincipal() {
        return (User) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
    }
    
}