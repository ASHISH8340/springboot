package com.stackroute.authenticationservice.config;


import com.stackroute.authenticationservice.Model.Role;
import com.stackroute.authenticationservice.Model.User;
import com.stackroute.authenticationservice.service.UserServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @Autowired
    private UserServiceImpl userService;

    @RabbitListener(queues="user_queue")
    public void getUserDtoFromRabbitMq(UserDTO userDto)
    {
        System.out.println(userDto.toString());
        User user=new User();
        user.setUsername(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        if(userDto.getRole().equals("SELLER")) {
            System.out.println("yes seller");
            user.setRole(Role.SELLER);
        }
        else if(userDto.getRole().equals("BUYER"))
        {
            user.setRole(Role.BUYER);
        }

        userService.saveUser(user);
    }
}
