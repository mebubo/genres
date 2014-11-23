package mebubo.genres.dao;

import com.google.common.base.Optional;
import mebubo.genres.core.Genre;
import mebubo.genres.core.LearnedWord;
import mebubo.genres.core.Word;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class WordDAO {

    private final SessionFactory sessionFactory;

    public WordDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public LearnedWord learn(int id, Genre genre) {
        Query query = currentSession().createQuery("from LearnedWord where word_id = :id").setParameter("id", id);
        LearnedWord learnedWord = (LearnedWord) query.uniqueResult();
        return updateDue(learnedWord, genre);
    }

    private LearnedWord updateDue(LearnedWord learnedWord, Genre genre) {
        if (learnedWord.getWord().getGenre() == genre) {
            Period period = learnedWord.getPeriod();
            LocalDate newDue = LocalDate.now().plus(period);
            period = period.multipliedBy(2);
            learnedWord.setDue(newDue);
            learnedWord.setPeriod(period);
        } else {
            learnedWord.reset();
        }
        return learnedWord;
    }

    private Optional<LearnedWord> toReviseNow() {
        Query query = currentSession().createQuery("from LearnedWord where due <= :now").setDate("now", Date.valueOf(LocalDate.now()));
        return getFirstResult(query);
    }

    private <T> Optional<T> getFirstResult(Query query) {
        List list = query.setMaxResults(1).list();
        if (!list.isEmpty()) {
            return Optional.of((T) list.get(0));
        } else {
            return Optional.absent();
        }
    }

    public Optional<LearnedWord> toLearnNow() {
        Query query = currentSession().createQuery("from Word where id not in (select word from LearnedWord)");
        Optional<Word> word = getFirstResult(query);
        LearnedWord learnedWord = null;
        if (word.isPresent()) {
            learnedWord = new LearnedWord(word.get());
            currentSession().save(learnedWord);
            return Optional.of(learnedWord);
        } else {
            return Optional.absent();
        }
    }

    public Optional<LearnedWord> wordForNow() {
        Optional<LearnedWord> toRevise = toReviseNow();
        return toRevise.isPresent() ? toRevise : toLearnNow();
    }
}
