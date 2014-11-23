package mebubo.genres.core.converters;

import mebubo.genres.core.Genre;

import javax.persistence.AttributeConverter;

public class GenrePersistenceConverter implements AttributeConverter<Genre, String> {
    @Override
    public String convertToDatabaseColumn(Genre attribute) {
        return attribute.name();
    }

    @Override
    public Genre convertToEntityAttribute(String dbData) {
        return Genre.valueOf(dbData);
    }
}
