package be.projetblog.technofuturtic.projetblog.security;

import be.projetblog.technofuturtic.projetblog.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private UserService userService;
    @Autowired
    public WebSecurityConfigurer(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(PasswordConfig.passwordencoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**") // TODO Ajouter la sécurité par ici aussi
                .permitAll()
            .and()
                .logout()
                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login?logout").and().httpBasic();
    }

    /*@Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password(PasswordConfig.passwordencoder().encode("password"))
                .authorities(UserRole.MEMBER.getGrantedAuthorities())
                .build();

        UserDetails admin = User.builder().username("admin")
                .password(PasswordConfig.passwordencoder().encode("password"))
                .authorities(UserRole.ADMIN.getGrantedAuthorities())
                .build();

        UserDetails sadmin = User.builder().username("sadmin")
                .password(PasswordConfig.passwordencoder().encode("password"))
                .authorities(UserRole.SUPER_ADMIN.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(user, admin, sadmin);
    }*/
}