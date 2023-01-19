package vanhoang.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final BaseLdapPathContextSource contextSource;

    @Bean
    public BindAuthenticator bindAuthenticator () {
        BindAuthenticator authenticator = new BindAuthenticator(contextSource);
        authenticator.setUserSearch(new FilterBasedLdapUserSearch("ou=people", "uid={0}", contextSource));
        return authenticator;
    }

    @Bean
    public LdapAuthenticationProvider ldapAuthenticationProvider () {
        return new LdapAuthenticationProvider(bindAuthenticator());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/auth/register", "/").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll();
    }
}
