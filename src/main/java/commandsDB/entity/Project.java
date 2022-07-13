package commandsDB.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
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

    @Setter
    @Getter
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Developer> developers = new HashSet<>();

    public void addDeveloper(Developer developer) {
        developers.add(developer);
        developer.getProjects().add(this);
    }

    public void removeDeveloper(Developer developer) {
        developers.remove(developer);
        developer.getProjects().remove(this);
    }


    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", nameOfProject='" + nameOfProject + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id && Objects.equals(nameOfProject, project.nameOfProject) && Objects.equals(description, project.description) && Objects.equals(date, project.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameOfProject, description, date);
    }
}