package com.stackroute.userservice.Controller;


    import java.util.HashMap;
            import java.util.Map;

            import com.stackroute.userservice.Repository.UserRepository;
            import com.stackroute.userservice.model.User;
            import com.stackroute.userservice.model.UserRole;
            import com.stackroute.userservice.service.UserServiceImpl;
            import org.springframework.beans.factory.annotation.Autowired;
            import org.springframework.http.HttpStatus;

            import org.springframework.http.ResponseEntity;
            import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
    import org.springframework.web.bind.annotation.CrossOrigin;
    import org.springframework.web.bind.annotation.GetMapping;

            import org.springframework.web.bind.annotation.RestController;


@RestController
public class googleController {

    @Autowired
    UserServiceImpl userserviceimpl;

    @Autowired
    private UserRepository repository;

    @GetMapping("/googleAuth")
    public ResponseEntity<User> saveUser(OAuth2AuthenticationToken token) {

        Map<String,Object> m = new HashMap<>();
        m=token.getPrincipal().getAttributes();

        String email=(String) m.get("email");

        User userByEmail = repository.findByEmailId(email);
        if(userByEmail!=null)
            return new ResponseEntity<User>(userByEmail, HttpStatus.OK);


        User u = new User();
        u.setEmailId(email);
        u.setUserRole(UserRole.BUYER);
        u.setPassword("null");

        User n = userserviceimpl.saveUser(u);
        return new ResponseEntity<User>(n, HttpStatus.OK);

    }
}

