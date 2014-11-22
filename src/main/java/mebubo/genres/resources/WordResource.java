package mebubo.genres.resources;

import io.dropwizard.hibernate.UnitOfWork;
import mebubo.genres.core.Genre;
import mebubo.genres.core.LearnedWord;
import mebubo.genres.core.Word;
import mebubo.genres.dao.LearnedWordDAO;
import mebubo.genres.dao.WordDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/word")
@Produces(MediaType.APPLICATION_JSON)
public class WordResource {

    private final WordDAO wordDAO;
    private LearnedWordDAO learnedWordDAO;

    public WordResource(WordDAO wordDAO, LearnedWordDAO learnedWordDAO) {
        this.wordDAO = wordDAO;
        this.learnedWordDAO = learnedWordDAO;
    }

    @GET
    @UnitOfWork
    public LearnedWord getWord() {
        Word word = wordDAO.list().get(0);
        return learnedWordDAO.createLearnedWord(word);
    }

}
