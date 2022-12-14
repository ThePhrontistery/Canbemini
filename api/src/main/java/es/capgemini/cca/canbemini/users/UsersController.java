package es.capgemini.cca.canbemini.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.capgemini.cca.canbemini.mapppers.UsersMapper;

@RequestMapping(value = "/api/users")
@RestController
@CrossOrigin(origins = "*")
public class UsersController {

    @Autowired
    UsersService usersService;

    @Autowired
    UsersMapper usersMapper;

    public UsersController() {

    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public UsersDto findUsers(@PathVariable("id") Long id) {
        return usersMapper.UsersToUsersDto(usersService.findUsers(id));
    }

    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody UsersDto usersDto) {
        usersService.saveUsers(id, usersDto);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        usersService.deleteUsers(id);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public UsersDto findByEmail(@RequestParam(value = "email", required = true) String email) {

        return usersMapper.UsersToUsersDto(usersService.findByEmail(email));
    }

}