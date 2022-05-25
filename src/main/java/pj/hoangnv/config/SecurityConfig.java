package pj.hoangnv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception{

        http.authorizeRequests().antMatchers("/home").permitAll();

        http.authorizeRequests().anyRequest().authenticated();

        http.authorizeRequests().and()
                .csrf().ignoringAntMatchers("/**");

        http.authorizeRequests()
                .and()
                .formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/sys/user/login")
                .defaultSuccessUrl("/home")
                .failureForwardUrl("/sys/user/login")
                .permitAll();
    }

    @Override
    public void configure(AuthenticationManagerBuilder manager) throws Exception{

        manager.inMemoryAuthentication()
                .withUser("hoangnv").password(passwordEncoder().encode("12345")).authorities("ROLE_USER")
                .and()
                .withUser("vanhoang").password(passwordEncoder().encode("12345")).authorities("ROLE_ADMIN");
    }
}
