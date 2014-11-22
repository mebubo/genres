package mebubo.genres.dao;

import io.dropwizard.hibernate.AbstractDAO;
import mebubo.genres.core.LearnedWord;
import mebubo.genres.core.Word;
import org.hibernate.SessionFactory;

public class LearnedWordDAO extends AbstractDAO<LearnedWord> {

    public LearnedWordDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public LearnedWord createLearnedWord(Word word) {
        LearnedWord learnedWord = new LearnedWord(word);
        return persist(learnedWord);
    }
}
