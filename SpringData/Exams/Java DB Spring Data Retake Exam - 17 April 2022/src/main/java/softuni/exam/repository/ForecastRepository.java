package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.entity.DayOfWeek;
import softuni.exam.models.entity.Forecast;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface ForecastRepository extends JpaRepository<Forecast,Long> {

    Optional<List<Forecast>> findAllByDayOfWeekAndCity_PopulationLessThanOrderByMaxTemperatureDescIdAsc(DayOfWeek day, int populayion);

    Optional<Forecast> findByCity_IdAndDayOfWeek(Long city, DayOfWeek dayOfWeek);
}

