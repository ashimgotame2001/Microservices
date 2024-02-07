package org.example.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.domain.enumuration.Role;

import java.util.UUID;

@Getter
@Setter
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ms_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String address;
    private UUID userCode;
    private Role role;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userImage_id")
    private FileStorage fileStorage;
}
