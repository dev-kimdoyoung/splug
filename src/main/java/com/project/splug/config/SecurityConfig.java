package com.project.splug.config;

import com.project.splug.domain.enums.RoleType;
import com.project.splug.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserLoginService userLoginService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers("/", "/assets/**", "/images/**", "/h2-console/**", "/profile").permitAll()
                    .antMatchers("/api/v1/**").hasAnyRole(RoleType.ADMIN.name(), RoleType.MEMBER.name(), RoleType.GUEST.name(), RoleType.GRADUATE.name())
                    .anyRequest().authenticated()
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true)
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/api/v2/login")
                    .usernameParameter("id")
                    .passwordParameter("password")
                    .permitAll();

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userLoginService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
