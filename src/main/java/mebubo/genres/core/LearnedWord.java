package mebubo.genres.core;

import mebubo.genres.core.converters.LocalDatePersistenceConverter;
import mebubo.genres.core.converters.PeriodPersistenceConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "learned_words")
public class LearnedWord {

    public LearnedWord() {
    }

    public LearnedWord(Word word) {
        this.word = word;
    }

    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    private Word word;

    @Convert(converter = LocalDatePersistenceConverter.class)
    LocalDate due = LocalDate.now();

    @Convert(converter = PeriodPersistenceConverter.class)
    Period period = initialPeriod();

    private static Period initialPeriod() {
        return Period.ofDays(1);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public LocalDate getDue() {
        return due;
    }

    public void setDue(LocalDate due) {
        this.due = due;
    }

    public void reset() {
        this.period = initialPeriod();
        this.due = LocalDate.now();
    }
}
