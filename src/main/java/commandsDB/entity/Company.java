package commandsDB.entity;


import jakarta.persistence.*;

import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
@Table(name = "company")
public class Company {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_company")
    private long idCompany;
    @Setter
    @Column(name = "name_of_company")
    private String nameOfCompany;
    @Setter
    @Column
    private String address;


}
