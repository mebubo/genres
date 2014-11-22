package mebubo.genres.core;

import javax.persistence.*;

@Entity
@Table(name = "lexique")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Word {
    @Id
    @GeneratedValue
    private int id;

    private String lemme;
    private String genre;
    @Column(name = "freqlemfilms2")
    private float frequencyFilms;
    @Column(name = "freqlemlivres")
    private float frequencyBooks;
    private int rank;

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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
