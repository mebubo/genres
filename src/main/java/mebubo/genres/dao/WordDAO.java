package mebubo.genres.dao;

import io.dropwizard.hibernate.AbstractDAO;
import mebubo.genres.core.LearnedWord;
import mebubo.genres.core.Word;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;

import java.util.List;

public class WordDAO extends AbstractDAO<Word> {

    public WordDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Word> list() {
        return list(criteria());
    }

}
