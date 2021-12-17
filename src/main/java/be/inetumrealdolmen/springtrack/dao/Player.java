package be.inetumrealdolmen.springtrack.dao;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Player {

    @Id
    private String id;
    private String email;
    private String fullName;
    private String password;
}
