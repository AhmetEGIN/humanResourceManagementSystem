package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.Position;

public interface PositionRepository extends JpaRepository<Position, Integer> {
	boolean existsPositionByName(String name);
}
