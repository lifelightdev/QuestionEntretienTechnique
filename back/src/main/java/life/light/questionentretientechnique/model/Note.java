package life.light.questionentretientechnique.model;

import jakarta.persistence.*;

@Entity
@Table(name = "note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private Integer nbBonneReponse;
    private Integer nbQuestion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNbBonneReponse() {
        return nbBonneReponse;
    }

    public void setNbBonneReponse(Integer nbBonneReponse) {
        this.nbBonneReponse = nbBonneReponse;
    }

    public Integer getNbQuestion() {
        return nbQuestion;
    }

    public void setNbQuestion(Integer nbQuestion) {
        this.nbQuestion = nbQuestion;
    }
}
