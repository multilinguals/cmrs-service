package org.multilinguals.enterprise.cmrs.infrastructure.security;

import org.multilinguals.enterprise.cmrs.infrastructure.i18n.I18Translator;
import org.multilinguals.enterprise.cmrs.query.user.UserDetailsViewRepository;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import javax.annotation.Resource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private UserDetailsViewRepository userDetailsViewRepository;

    @Resource
    private I18Translator i18Translator;

    private static final String[] AUTH_WHITELIST = {
            "/sign-up-username",
            "/sign-in-with-password"
    };

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.headers().cacheControl().disable() //禁用缓存
                .and()
                .csrf().disable() // 基于token，不需要csrf
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 基于token，所以不需要session
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST)
                .permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() // 允许所有的OPTIONS通过
                .anyRequest().authenticated()
                .and()
                .addFilter(new RequestValidationFilter(authenticationManager(), this.userDetailsViewRepository))
                .exceptionHandling()
                .authenticationEntryPoint(new AuthenticationExceptionEntryPoint(this.i18Translator))
                .accessDeniedHandler(new CmrsAccessDeniedHandler(this.i18Translator));
    }
}
