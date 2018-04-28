package com.example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
		 .authorizeRequests()
		 .antMatchers("/").permitAll()
		 .antMatchers("/student/viewall").hasRole("ADMIN")
		 .antMatchers("/student/view/**").hasRole("USER")
		 .antMatchers("/css/**").permitAll()
		 .antMatchers("/js/**").permitAll()
		 .anyRequest().authenticated()
		 .and()
		 .formLogin()
		 .loginPage("/login")
		 .permitAll()
		 .and()
		 .logout()
		 .permitAll();
	}
	
	@Autowired
	public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception
	{
	 auth.inMemoryAuthentication()
	 .withUser("admin").password("admin")
	 .roles("ADMIN");
	 auth.inMemoryAuthentication()
	 .withUser("user").password("user")
	 .roles("USER");
	}
}