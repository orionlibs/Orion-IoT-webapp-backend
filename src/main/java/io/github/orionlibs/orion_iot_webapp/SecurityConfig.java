package io.github.orionlibs.orion_iot_webapp;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter.ReferrerPolicy;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter.HeaderValue;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{
    @Bean
    public Customizer<CsrfConfigurer<HttpSecurity>> csrfCustomizer()
    {
        return csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .ignoringRequestMatchers("/**");
    }


    @Bean
    public Customizer<CorsConfigurer<HttpSecurity>> corsCustomizer()
    {
        return cors -> cors.configurationSource(request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedOrigins(Arrays.asList("http://localhost:8081"));
            config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
            config.setAllowedHeaders(Arrays.asList("*"));
            config.setAllowCredentials(true);
            config.setMaxAge(3600L);
            return config;
        });
    }


    @Bean
    public Customizer<SessionManagementConfigurer<HttpSecurity>> sessionManagementCustomizer()
    {
        return http -> http.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


    @Bean
    public Customizer<HeadersConfigurer<HttpSecurity>.CacheControlConfig> cacheControlCustomizer()
    {
        return http -> http.disable();
    }


    @Bean
    public Customizer<HeadersConfigurer<HttpSecurity>.ContentSecurityPolicyConfig> contentSecurityCustomizer()
    {
        return http -> http.policyDirectives("frame-src 'none'");
    }


    @Bean
    public Customizer<HeadersConfigurer<HttpSecurity>.HstsConfig> hstsCustomizer()
    {
        return http -> http.includeSubDomains(true).preload(true);
    }


    @Bean
    public Customizer<HeadersConfigurer<HttpSecurity>.XXssConfig> xssCustomizer()
    {
        return http -> http.headerValue(HeaderValue.ENABLED_MODE_BLOCK);
    }


    @Bean
    public Customizer<HeadersConfigurer<HttpSecurity>.ReferrerPolicyConfig> referrerPolicyCustomizer()
    {
        return http -> http.policy(ReferrerPolicy.STRICT_ORIGIN);
    }


    @Bean
    public Customizer<HeadersConfigurer<HttpSecurity>> headersCustomizer()
    {
        return http -> http.referrerPolicy(referrerPolicyCustomizer())
                        .xssProtection(xssCustomizer())
                        .httpStrictTransportSecurity(hstsCustomizer())
                        .contentSecurityPolicy(contentSecurityCustomizer())
                        .cacheControl(cacheControlCustomizer());
    }


    @Bean
    public Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> authorizeHttpRequestsCustomizer()
    {
        return http -> http.requestMatchers("/**").anonymous()
                        .anyRequest().permitAll();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.cors(corsCustomizer())
                        .csrf(csrfCustomizer())
                        .headers(headersCustomizer())
                        .sessionManagement(sessionManagementCustomizer())
                        .authorizeHttpRequests(authorizeHttpRequestsCustomizer());
        return http.build();
    }
}