package life.light.questionentretientechnique;

import life.light.questionentretientechnique.model.*;
import life.light.questionentretientechnique.service.QuestionnaireService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionEntretienTechniqueApplicationTests {

    @InjectMocks
    QuestionnaireService service;

    @Mock
    QuestionRepository questionRepository;

    List<Question> questions;
    Set<Theme> themes;
    Theme themeJava;
    Question question;
    Reponse reponseUn;
    Reponse reponseDeux;
    Reponse reponseTrois;
    Reponse reponseQuatre;

    @BeforeEach
    void setUp() {
        question = new Question();
        List<Reponse> reponses = new ArrayList<>();
        reponseUn = new Reponse();
        reponseDeux = new Reponse();
        reponseTrois = new Reponse();
        reponseQuatre = new Reponse();
        reponses.add(reponseUn);
        reponses.add(reponseDeux);
        reponses.add(reponseTrois);
        reponses.add(reponseQuatre);
        question.setReponses(reponses);
        themeJava = new Theme();
        themeJava.setNom("Java");
        themes = new HashSet<>();
        themes.add(themeJava);
        question.setThemes(themes);
        List<Reponse> reponsesJustes = new ArrayList<>();
        reponsesJustes.add(reponseDeux);
        List<Reponse> reponsesChoisies = new ArrayList<>();
        reponsesChoisies.add(reponseDeux);
        question.setReponsesJustes(reponsesJustes);
        question.setReponsesChoisies(reponsesChoisies);
        questions = new ArrayList<>();
        questions.add(question);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void genererUnQuestionnaire() {
        Questionnaire questionnaire = service.generer(1, null);
        assertThat(questionnaire).isNotNull();
    }

    @Test
    void genererUnQuestionnaireAvecUneQuestion(){
        when(questionRepository.findAll()).thenReturn(questions);
        Questionnaire questionnaire = service.generer(1, null);
        assertThat(questionnaire.getQuestions()).asList();
        assertThat(questionnaire.getQuestions().size()).isEqualTo(1);
    }

    @Test
    void genererUnQuestionnaireAvecUneQuestionEtQuatreReponses(){

        when(questionRepository.findAll()).thenReturn(questions);

        Questionnaire questionnaire = service.generer(1, null);
        assertThat(questionnaire.getQuestions().get(0).getReponses()).asList();
        assertThat(questionnaire.getQuestions().get(0).getReponses().size()).isEqualTo(4);
    }

    @Test
    void genererUnQuestionnaireAvecUneQuestionSurLeThemeJava(){

        when(questionRepository.findByThemesIn(themes)).thenReturn(questions);
        Questionnaire questionnaire = service.generer(1, themes);

        assertThat(questionnaire.getQuestions().get(0).getThemes().contains(themeJava)).isTrue();
    }

    @Test
    void verifierLaReponseJuste(){
        Boolean reponse = service.verifier(question);
        assertThat(reponse).isTrue();
    }

    @Test
    void verifierLaReponseFausse(){
        List<Reponse> reponsesJustes = new ArrayList<>();
        reponsesJustes.add(reponseUn);
        List<Reponse> reponsesChoisies = new ArrayList<>();
        reponsesChoisies.add(reponseDeux);
        question.setReponsesJustes(reponsesJustes);
        question.setReponsesChoisies(reponsesChoisies);
        Boolean reponse = service.verifier(question);
        assertThat(reponse).isFalse();
    }

    @Test
    void noteUnSurUn(){
        Questionnaire questionniare = new Questionnaire();
        questionniare.setQuestions(questions);
        Note note = service.calculeNote(questionniare);
        assertThat(note.getNbBonneReponse()).isEqualTo(1);
        assertThat(note.getNbQuestion()).isEqualTo(1);
    }

    @Test
    void noteUnSurDeux(){
        Questionnaire questionniare = new Questionnaire();
        Question questionDeux = new Question();
        questions.add(questionDeux);
        questionniare.setQuestions(questions);
        Note note = service.calculeNote(questionniare);
        assertThat(note.getNbBonneReponse()).isEqualTo(1);
        assertThat(note.getNbQuestion()).isEqualTo(2);
    }

    @Test
    void noteDeuxSurTrois(){
        Questionnaire questionniare = new Questionnaire();
        Question questionDeux = new Question();
        questions.add(questionDeux);
        Question questionTrois = new Question();
        List<Reponse> reponses = new ArrayList<>();
        reponses.add(reponseDeux);
        questionTrois.setReponsesChoisies(reponses);
        questionTrois.setReponsesJustes(reponses);
        questions.add(questionTrois);
        questionniare.setQuestions(questions);
        Note note = service.calculeNote(questionniare);
        assertThat(note.getNbBonneReponse()).isEqualTo(2);
        assertThat(note.getNbQuestion()).isEqualTo(3);
    }
}
