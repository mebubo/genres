package mebubo.genres.core;

import mebubo.genres.core.converters.GenrePersistenceConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "lexique")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Word {
    @Id
    @GeneratedValue
    private int id;

    private String lemme;

    @Convert(converter = GenrePersistenceConverter.class)
    private Genre genre;

    @Column(name = "freqlemfilms2")
    private float frequencyFilms;
    @Column(name = "freqlemlivres")
    private float frequencyBooks;
    private int rank;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getFrequencyBooks() {
        return frequencyBooks;
    }

    public void setFrequencyBooks(float frequencyBooks) {
        this.frequencyBooks = frequencyBooks;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public float getFrequencyFilms() {
        return frequencyFilms;
    }

    public void setFrequencyFilms(float frequencyFilms) {
        this.frequencyFilms = frequencyFilms;
    }

    public String getLemme() {
        return lemme;
    }

    public void setLemme(String lemme) {
        this.lemme = lemme;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
