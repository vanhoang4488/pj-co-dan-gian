package pj.hoangnv.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.authorizeRequests()
                .antMatchers("/", "/home", "/sys/user/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/sys/user/login").permitAll()
                .usernameParameter("usernameOrGmail")
                .defaultSuccessUrl("/home?login=success")
                .failureUrl("/login?login=failed")
                .loginProcessingUrl("j_spring_security_check")
                .and()
                .logout()
                .logoutSuccessUrl("/sys/user/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService);
    }
}
