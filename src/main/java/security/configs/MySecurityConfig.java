package security.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

import javax.sql.DataSource;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

//    Аутентификация (1 уровень защиты) через in memory data
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        UserBuilder userBuilder = User.withDefaultPasswordEncoder();
//        auth.inMemoryAuthentication().
//                withUser(userBuilder.username("Michail")
//                        .password("Michail")
//                        .roles("EMPLOYEE"))
//                .withUser(userBuilder.username("Anastasia")
//                        .password("Anastasia")
//                        .roles("HR"))
//                .withUser(userBuilder.username("Ivan")
//                        .password("Ivan")
//                        .roles("MANAGER", "HR"));
//    }


//  Аутентификация (1 уровень защиты) через БД
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.a
//    }

    //Авторизация (2 уровень защиты) через in memory data
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasAnyRole("EMPLOYEE", "HR", "MANAGER")
                .antMatchers("/hr_info").hasRole("HR")
                .antMatchers("/manager_info").hasRole("MANAGER")
                .and().formLogin().permitAll();
    }
}
