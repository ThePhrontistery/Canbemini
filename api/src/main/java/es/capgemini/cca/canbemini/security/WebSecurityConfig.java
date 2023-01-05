package es.capgemini.cca.canbemini.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//clase de configuración de Spring Security que se encarga de establecer la configuración de seguridad.
@Configuration
public class WebSecurityConfig {
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    // validar el token JWT en las solicitudes entrantes
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    //para controlar el acceso a las rutas protegidas por autorización
    @Bean
    public JWTAuthorizationFilter authoritationFilter() {
        return new JWTAuthorizationFilter();
    }

   /*Se utiliza para la autenticación de usuarios, se encarga de buscar al usuario en la base de datos
   y de verificar que la contraseña sea correcta.
   Si el usuario y la contraseña son válidos, DaoAuthenticationProvider devuelve una instancia de un objeto de
   tipo Authenication, que indica que el usuario se ha autenticado correctamente.
   * */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    /*proporciona una instancia de AuthenticationManager basándose en la configuración de autenticación especificada
    en authConfig. La instancia de AuthenticationManager se puede usar para autenticar solicitudes y verificar la
    autenticación de usuarios.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    //Se utiliza para codificar contraseñas de forma segura utilizando el algoritmo BCrypt.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*configura las reglas de autenticación y autorización para proteger las rutas de la aplicación.
      /auth/ son accesibles sin necesidad de autenticación y /api/ requieren autenticación.
     */
    @Bean
    @Order(1)
    public SecurityFilterChain filterChainKanban(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers("/auth/**").permitAll().antMatchers("/api/**").authenticated();

        http.headers().frameOptions().disable();
        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
