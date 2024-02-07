package org.example.domain.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.domain.dto.request.FileRequestDTO;
import org.example.domain.enumuration.Role;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String address;
    private Role role;
    private FileRequestDTO userImage;

}
