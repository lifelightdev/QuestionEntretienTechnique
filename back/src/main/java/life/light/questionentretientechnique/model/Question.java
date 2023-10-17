package life.light.questionentretientechnique.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "question_id"))
    private List<Reponse> reponses;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "question_id"))
    private Set<Theme> themes;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "question_id"))
    private List<Reponse> reponsesJustes;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "question_id"))
    private List<Reponse> reponsesChoisies;

    public List<Reponse> getReponsesChoisies() {
        return reponsesChoisies;
    }

    public void setReponsesChoisies(List<Reponse> reponsesChoisies) {
        this.reponsesChoisies = reponsesChoisies;
    }

    public List<Reponse> getReponsesJustes() {
        return reponsesJustes;
    }

    public void setReponsesJustes(List<Reponse> reponsesJustes) {
        this.reponsesJustes = reponsesJustes;
    }

    public Set<Theme> getThemes() {
        return themes;
    }

    public void setThemes(Set<Theme> themes) {
        this.themes = themes;
    }

    public List<Reponse> getReponses() {
        return reponses;
    }

    public void setReponses(List<Reponse> reponses) {
        this.reponses = reponses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
