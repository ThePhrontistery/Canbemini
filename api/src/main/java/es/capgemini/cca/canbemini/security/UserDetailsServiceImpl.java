package es.capgemini.cca.canbemini.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.capgemini.cca.canbemini.users.Users;
import es.capgemini.cca.canbemini.users.UsersRepository;

//implementación de la interfaz UserDetailsService
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    @Transactional //significa que cualquier operación realizada se realizará en una transacción.
    /*consulta a la base de datos para recuperar un usuario con un email determinado. Si esta consulta
    tiene éxito, se completa la transacción y se devuelve el usuario. Si la consulta falla,
    se deshacen todas las operaciones realizadas y se lanza una excepción.
     */
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

        return UserDetailsImpl.build(user);
    }

}