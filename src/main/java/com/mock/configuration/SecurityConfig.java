package com.mock.configuration;


import com.mock.Auth.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    private static final String[] ENABLE_PERMIT = {
            "/login", "/", "/logout",  "assets/**", "/home", "/index",
            "/css/**", "/js/**", "/image/**", "/fonts/**"
    };

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    public void config(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         /** Phân quyền và xác thực*/
        http.authorizeHttpRequests(auth -> {
                    auth
                            /** Các đường dẫn không yêu cầu xác thực */
                            .requestMatchers(ENABLE_PERMIT).permitAll()

                            /** Chỉ cho phép rank 1 truy cập /admin/** */
                            .requestMatchers("/admin/**").hasAuthority("ROLE_RANK_1")
                            .requestMatchers("/claim/admin/**").hasAuthority("ROLE_RANK_1")
                            .requestMatchers("/claim/**").hasAnyAuthority("ROLE_RANK_3", "ROLE_RANK_2")
                            .requestMatchers("/approve/**").hasAuthority("ROLE_RANK_2")
                            .requestMatchers("/finance/**").hasAuthority("ROLE_RANK_4");
                });

        /** Cấu hình form login */
        http.formLogin(form ->
                form
                .loginPage("/login") // Trang login tùy chỉnh
                .loginProcessingUrl("/customLogin") // URL xử lý đăng nhập (action của form)
                .usernameParameter("username") // Tham số username trong form login
                .passwordParameter("password") // Tham số password trong form login
                .successHandler(customAuthenticationSuccessHandler) // Sử dụng SuccessHandler tùy chỉnh
        );



        http.rememberMe(rememberMe ->
                rememberMe
                        .key("uniqueAndSecret")  // Khóa bảo mật cho remember-me
                        .tokenValiditySeconds(3 * 24 * 60 * 60)  // Thời gian hiệu lực của cookie (1 ngày)
                        .rememberMeParameter("remember-me")  // Tên của tham số remember-me trong form
        );

        /** Cấu hình logout*/
        http.logout(
                logout -> logout.logoutUrl("/logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("remember-me") // Xóa cookie remember-me khi logout
                        .logoutSuccessUrl("/home")
        );

        return http.build();
    }

}
