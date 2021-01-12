package ir.pb.online_examination_system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
// SecurityConfig class decides whether to allow or not different requests to have access to the app. and decides to
// stick which view to which role.
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomAuthenticationProvider authenticationProvider;

    @Autowired
    private MyLoginSuccessHandler successHandler;
    // configure method sets the customized authenticationProvider to the AuthenticationProviderBuilder.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }
    // passwordEncoder method returns the PasswordEncoder to be used in the app.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // configure method defines that which role can access to which view or views.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
                .authorizeRequests()
                .antMatchers("../static/css/**").permitAll()
                .mvcMatchers("/admin/**").hasRole("ADMIN")
                .mvcMatchers("/sign-up-form").permitAll()
                .mvcMatchers("/user/not-registered").hasAnyRole("MASTER", "STUDENT")
                .mvcMatchers("/master/**").hasAnyRole("MASTER", "ADMIN")
                .mvcMatchers("/student/**").hasAnyRole("STUDENT", "ADMIN")
                .mvcMatchers("/sign-in").permitAll()
                .and().formLogin()
                .loginPage("/login").permitAll().successHandler(successHandler)
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
    }

}
