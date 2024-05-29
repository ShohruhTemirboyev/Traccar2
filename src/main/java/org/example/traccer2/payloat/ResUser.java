package org.example.traccer2.payloat;

import jakarta.persistence.ManyToMany;
import lombok.Data;
import org.example.traccer2.entity.Role;

import java.util.List;

@Data
public class ResUser {

    private Integer id;
    private String name;
    private String userName;
    private String email;
    private String surname;

    private List<Role> role;

}
