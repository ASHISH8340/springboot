package com.stackroute.userservice.DTO;





import com.stackroute.userservice.model.Address;
import com.stackroute.userservice.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageUserDTO {

    private String emailId;
    private String name;
    private String contactNo;
    private String password;
    private String gender ;
    private UserRole userRole;
    private List<Address> address;
}
