package mebubo.genres.resources;

import com.google.common.base.Optional;
import io.dropwizard.hibernate.UnitOfWork;
import mebubo.genres.core.Genre;
import mebubo.genres.core.LearnedWord;
import mebubo.genres.dao.WordDAO;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/word")
@Produces(MediaType.APPLICATION_JSON)
public class WordResource {

    private final WordDAO wordDAO;

    public WordResource(WordDAO wordDAO) {
        this.wordDAO = wordDAO;
    }

    @Path("{id}/{genre}")
    @PUT
    @UnitOfWork
    public LearnedWord update(@PathParam("id") int id, @PathParam("genre") Genre genre) {
        return wordDAO.learn(id, genre);
    }

    @GET
    @UnitOfWork
    public Optional<LearnedWord> next() {
        return wordDAO.wordForNow();
    }

}
