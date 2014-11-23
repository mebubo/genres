package mebubo.genres.core.converters;

import javax.persistence.AttributeConverter;
import java.time.Period;

public class PeriodPersistenceConverter implements AttributeConverter<Period, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Period period) {
        return period.getDays();
    }

    @Override
    public Period convertToEntityAttribute(Integer duration) {
        return Period.ofDays(duration);
    }
}
