package mebubo.genres.core.converters;

import javax.persistence.AttributeConverter;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class DurationPersistenceConverter implements AttributeConverter<Duration, Long> {
    @Override
    public Long convertToDatabaseColumn(Duration duration) {
        return duration.toMinutes();
    }

    @Override
    public Duration convertToEntityAttribute(Long duration) {
        return Duration.of(duration, ChronoUnit.MINUTES);
    }
}
