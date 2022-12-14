package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.Plane;

import java.util.Optional;

@Repository
public interface PlaneRepository extends JpaRepository<Plane,Long> {

    Optional<Plane> findPlaneByRegisterNumber(String registerNumber);
}
