package commandsDB.entity;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@ToString
@Setter
@Table(name = "skills")
public class Skills {
    @Id()
    @Column(name = "id_skills")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id_developer")
    private long idDev;

    @Enumerated(EnumType.STRING)
    private Technology technology;

    private String levelOfPosition;

    public enum Technology {
        Java,
        JS,
        C_PLUS_PLUS,
        C_SHARP
    }

//    @OneToMany (mappedBy="skills", fetch=FetchType.EAGER)
//    private List<Developer> developersSkills = new ArrayList<>();
}
