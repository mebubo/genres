package mebubo.genres;

import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import mebubo.genres.core.LearnedWord;
import mebubo.genres.core.Word;
import mebubo.genres.dao.LearnedWordDAO;
import mebubo.genres.dao.WordDAO;
import mebubo.genres.resources.WordResource;

public class GenresApplication extends Application<GenresConfiguration> {

    private final HibernateBundle<GenresConfiguration> hibernate = new HibernateBundle<GenresConfiguration>(
            Word.class, LearnedWord.class
    ) {

        @Override
        public DataSourceFactory getDataSourceFactory(GenresConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public static void main(String[] args) throws Exception {
        new GenresApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<GenresConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets", "/"));
        bootstrap.addBundle(hibernate);
        bootstrap.getObjectMapper().registerModules(new JSR310Module());
    }

    @Override
    public void run(GenresConfiguration genresConfiguration, Environment environment) throws Exception {
        environment.jersey().setUrlPattern("/api/*");
        WordDAO wordDAO = new WordDAO(hibernate.getSessionFactory());
        LearnedWordDAO learnedWordDAO = new LearnedWordDAO(hibernate.getSessionFactory());
        environment.jersey().register(new WordResource(wordDAO, learnedWordDAO));

    }
}
