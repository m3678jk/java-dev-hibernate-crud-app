package commandsDB.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
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

    @Getter
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "developers")
    private Set<Project> projects = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Developer developer = (Developer) o;
        return id == developer.id && age == developer.age && Objects.equals(firstName, developer.firstName) && Objects.equals(secondName, developer.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, secondName, age);
    }
}