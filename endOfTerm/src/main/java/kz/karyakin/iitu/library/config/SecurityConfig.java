package kz.karyakin.iitu.library.config;

import kz.karyakin.iitu.library.config.jwt.JwtTokenAuthenticationFilter;
import kz.karyakin.iitu.library.config.jwt.JwtTokenGeneratorFilter;
import kz.karyakin.iitu.library.serivce.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/**", "/users/register", "/broadcasters/api/**").permitAll()
                .antMatchers("/users/api/**").hasAuthority("ADMIN")
                .antMatchers("/released/all").hasAuthority("ADMIN")
                .antMatchers("/users/released", "/users/return").hasAnyAuthority("MEMBER")
                .antMatchers(HttpMethod.GET, "/weather").permitAll()
                .antMatchers(HttpMethod.POST, "/weather").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/weather/**").hasAuthority("ADMIN")
                .antMatchers("/weather/**", "/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs", "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtTokenGeneratorFilter(authenticationManager()))
                .addFilterAfter(new JwtTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoderInConfig() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoderInConfig());
    }




}
