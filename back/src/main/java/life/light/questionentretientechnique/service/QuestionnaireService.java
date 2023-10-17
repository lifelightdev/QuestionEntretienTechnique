package life.light.questionentretientechnique.service;

import life.light.questionentretientechnique.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class QuestionnaireService {

    @Autowired
    private QuestionRepository questionRepository;

    public Questionnaire generer(int nbQuestion, Set<Theme> themes) {
        Questionnaire questionnaire = new Questionnaire();
        List<Question> toutesLesQuestions;
        if (null != themes){
            toutesLesQuestions = questionRepository.findByThemesIn(themes);
        } else {
            toutesLesQuestions = questionRepository.findAll();
        }
        Collections.shuffle(toutesLesQuestions);
        List<Question> questions = new ArrayList<>();
        int indexDesQuestion = 0;
        for (Question question : toutesLesQuestions){
            if (indexDesQuestion < nbQuestion){
                questions.add(question);
                indexDesQuestion++;
            } else {
                break;
            }
        }
        questionnaire.setQuestions(questions);
        return questionnaire;
    }

    public Boolean verifier(Question question){
        if (null != question.getReponsesJustes() && null != question.getReponsesChoisies()) {
            return question.getReponsesJustes().equals(question.getReponsesChoisies());
        }
        return false;
    }

    public Note calculeNote(Questionnaire questionniare) {
        Note note = new Note();
        Integer nbBonneReponse = 0;
        for (Question question : questionniare.getQuestions()){
            if (verifier(question)){
                nbBonneReponse++;
            }
        }
        note.setNbBonneReponse(nbBonneReponse);
        note.setNbQuestion(questionniare.getQuestions().size());
        return note;
    }
}
