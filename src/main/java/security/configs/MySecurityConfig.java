package security.configs;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    //Аутентификация (1 уровень защиты)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserBuilder userBuilder = User.withDefaultPasswordEncoder();
        auth.inMemoryAuthentication().
                withUser(userBuilder.username("Michail")
                        .password("Michail")
                        .roles("EMPLOYEE"))
                .withUser(userBuilder.username("Anastasia")
                        .password("Anastasia")
                        .roles("HR"))
                .withUser(userBuilder.username("Ivan")
                        .password("Ivan")
                        .roles("MANAGER", "HR"));
    }


    //Авторизация (2 уровень защиты)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasAnyRole("EMPLOYEE", "HR", "MANAGER")
                .antMatchers("/hr_info").hasRole("HR")
                .antMatchers("/manager_info").hasRole("MANAGER")
                .and().formLogin().permitAll();
    }
}
