package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.CoverLetter;

public interface CoverLetterRepository extends JpaRepository<CoverLetter, Integer> {
	boolean existsCoverLetterByIdEquals(int id);
}
