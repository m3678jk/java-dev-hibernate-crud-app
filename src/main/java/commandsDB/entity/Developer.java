package commandsDB.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@ToString
@Table(name = "developers")
@Entity
public class Developer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    private int age;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private int salary;

    public enum Sex {
        male,
        female,
        unknown
    }

//    @ManyToOne(optional = false, cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_developer")
//    private Skills skills;

    //@Getter

//    @ManyToMany
//    @JoinTable(name = "project_developer",
//            joinColumns = @JoinColumn(name = "id_developer"),
//            inverseJoinColumns = @JoinColumn(name = "id_project"))
//    private Set<Project> projectsSet = new HashSet<>();


}