package org.example.domain.dto.response;

import lombok.*;
import org.example.domain.FileStorage;
import org.example.domain.dto.request.FileRequestDTO;
import org.example.domain.enumuration.Role;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseForOrder {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private FileStorage userImage;
}
