package commandsDB.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Table(name = "projects")
@Setter
@Entity
public class Project {
    @Id
    @Column(name = "id_project")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name_of_project")
    private String nameOfProject;
    @Column
    private String description;
    @Column(name = "start_date")
    private LocalDate date;

//    @Setter
//    @Getter
//    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//    @JoinTable(name= "project_developer",
//            joinColumns = @JoinColumn(name = "id_project"),
//            inverseJoinColumns = @JoinColumn(name = "id_developer")
//    )
//    private Set<Developer> developerSet = new HashSet<>();


//    public void addDeveloper(Developer developer) {
//        developerSet.add(developer);
//    }
//
//    public void removeDeveloper(Developer developer){
//      //  developerSet.forEach(it -> it.getProjects().remove(this));
//       this.developerSet.remove(developer);
////        developer.getProjects().remove(this);
//    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", nameOfProject='" + nameOfProject + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}