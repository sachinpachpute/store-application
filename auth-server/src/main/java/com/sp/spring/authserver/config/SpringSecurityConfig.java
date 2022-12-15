package com.sp.spring.authserver.config;

import com.netflix.discovery.converters.Auto;
import com.sp.spring.authserver.security.filter.JwtAuthenticationFilter;
import com.sp.spring.authserver.security.utility.JwtUtils;
import com.sp.spring.authserver.service.AppUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SpringSecurityConfig {

    private final CORSCustomizer corsCustomizer;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final AppUserDetailsService appUserDetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        corsCustomizer.corsCustomizer(http);
        return http.cors().and().csrf().disable()
                .exceptionHandling().and()
                .formLogin().and()
                .authorizeRequests()
                .antMatchers("/authenticate","/token", "/testEndpoint").permitAll()
                .anyRequest().authenticated().and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
    public AuthenticationManager authManager(AppUserDetailsService appUserDetailsService){
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(appUserDetailsService);
        return new ProviderManager(authProvider);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

  /*  @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }*/

    /* Instead I've implemented UserDetailsService with my custom AppUserDetailsService getting Users from DB
    @Bean
    public UserDetailsService userDetailsService() {
        //var u1 = User.withUsername("bill").password("12345").authorities("read").build();
        var u1 = User.withUsername("bill").password("12345").roles("USER").build();

        var uds = new InMemoryUserDetailsManager();
        uds.createUser(u1);
        return uds;
    }*/

    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // only for demonstrations
    }*/


}
