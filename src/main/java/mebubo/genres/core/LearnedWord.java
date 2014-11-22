package mebubo.genres.core;

import mebubo.genres.core.converters.LocalDateTimePersistenceConverter;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "learned_words")
public class LearnedWord extends Word {

    public LearnedWord() {
    }

    public LearnedWord(Word word) {
        this.setFrequencyBooks(word.getFrequencyBooks());
        this.setFrequencyFilms(word.getFrequencyFilms());
        this.setGenre(word.getGenre());
        this.setLemme(word.getLemme());
        this.setRank(word.getRank());
    }

    @Convert(converter = LocalDateTimePersistenceConverter.class)
    LocalDateTime due = LocalDateTime.now();

    public LocalDateTime getDue() {
        return due;
    }

    public void setDue(LocalDateTime due) {
        this.due = due;
    }

    //Duration period;
}
