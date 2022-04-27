package com.java.project.springsecurity;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.java.project.service.Impl.UserDetailsImpl;

@Configuration
@EnableWebSecurity
public class MySecurityConfiguation extends WebSecurityConfigurerAdapter{
	
	@Bean
	public UserDetailsService userDetailService() {
		return new UserDetailsImpl();
	} 
	
	@Bean
	public BCryptPasswordEncoder passwordEnCoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authoProvider = new DaoAuthenticationProvider();
		authoProvider.setUserDetailsService(userDetailService());
		authoProvider.setPasswordEncoder(passwordEnCoder());
		return authoProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/swagger-ui").hasAuthority("ADMIN")
			.anyRequest().authenticated()
			.and()
			.formLogin().permitAll()
			.and()
			.logout().permitAll();
	}
	
	
}
