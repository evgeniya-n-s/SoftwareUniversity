package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.Car;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

    Optional<Car> findCarByMake(String make);

    Optional<Car> findCarById(Long id);

    List<Car> findAllByOrderByPicturesDescMakeAsc();
}
