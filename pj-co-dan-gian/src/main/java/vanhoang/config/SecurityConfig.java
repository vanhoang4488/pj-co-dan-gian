package vanhoang.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.web.AuthenticatedPrincipalOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final BaseLdapPathContextSource contextSource;
    private final ClientRegistrationRepository clientRegistrationRepository;

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

    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager () {
        OAuth2AuthorizedClientProvider authorizedClientProvider =
                OAuth2AuthorizedClientProviderBuilder.builder()
                        .authorizationCode()
                        .refreshToken()
                        .clientCredentials()
                        .password()
                        .build();

        DefaultOAuth2AuthorizedClientManager authorizedClientManager =
                new DefaultOAuth2AuthorizedClientManager(this.clientRegistrationRepository, this.authorizedClientRepository());
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

        return authorizedClientManager;
    }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService () {
        return new InMemoryOAuth2AuthorizedClientService(this.clientRegistrationRepository);
    }

    @Bean
    public OAuth2AuthorizedClientRepository authorizedClientRepository () {
        return new AuthenticatedPrincipalOAuth2AuthorizedClientRepository(this.authorizedClientService());
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login(oauth2 -> oauth2
                        .clientRegistrationRepository(this.clientRegistrationRepository)
                        .authorizedClientService(this.authorizedClientService())
                        .authorizedClientRepository(this.authorizedClientRepository())
                        .userInfoEndpoint(userInfo -> userInfo
                            .userService(this.userService())
                            .oidcUserService(this.oidcUserService()))
                        .failureUrl("/auth/error")
                )
                .oauth2Client(client -> client
                        .clientRegistrationRepository(this.clientRegistrationRepository)
                        .authorizedClientService(this.authorizedClientService())
                        .authorizedClientRepository(this.authorizedClientRepository()))
                .formLogin()
                .failureForwardUrl("/auth/error")
                .permitAll();

        return http.build();
    }

    public RestOperations restOperations () {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(this.clientHttpRequestInterceptor());
        restTemplate.setInterceptors(interceptors);
        return  restTemplate;
    }

    public ClientHttpRequestInterceptor clientHttpRequestInterceptor () {
        return (request, body, execution) -> {
            log.info("-------> interceptor success");
            return execution.execute(request, body);
        };
    }

    public OAuth2UserService<OAuth2UserRequest, OAuth2User> userService () {
        DefaultOAuth2UserService userService = new DefaultOAuth2UserService();
        userService.setRequestEntityConverter(new Converter<OAuth2UserRequest, RequestEntity<?>>() {
            @Override
            public RequestEntity<?> convert(OAuth2UserRequest source) {
                log.info("----------------> pre handler");
                return null;
            }
        });
        userService.setRestOperations(this.restOperations());
        return userService;
    }

    public OidcUserService oidcUserService () {
        OidcUserService oidcUserService = new OidcUserService();
        oidcUserService.setOauth2UserService(this.userService());
        return  oidcUserService;
    }
}
