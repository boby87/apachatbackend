package iconsoft.ftg.ApAchat.Configuration.gestionSecurity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)//ici nous disons a spring que l'authentification sera basée sur UserDetailsService
                .passwordEncoder(bCryptPasswordEncoder);// on hache le mot de passe de l'utilisateur avec cette fonction et tu vas le comparer avec le mot de passe en bd

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.formLogin();
        http.authorizeHttpRequests().antMatchers("/login/**","/utilisateur/**","/v2/api-docs","/configuration/ui","/configuration/security", "/swagger-ui.html/**","/swagger-resources/**").permitAll();
       http.addFilter(new JwtAuthentificationFilter(authenticationManager()));
       http.addFilterBefore(new JWTauthorizationFilter(),  UsernamePasswordAuthenticationFilter.class);

        // http.authorizeHttpRequests().anyRequest().authenticated();

    }



}
