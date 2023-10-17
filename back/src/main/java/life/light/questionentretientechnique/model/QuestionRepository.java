package life.light.questionentretientechnique.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByThemesIn(Set<Theme> themes);
}
