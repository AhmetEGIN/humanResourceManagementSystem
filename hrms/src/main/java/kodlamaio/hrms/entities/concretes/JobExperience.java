package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job_experiences")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "Lazy"})
public class JobExperience {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "job_position")
	private String jobPosition;
	
	@Column(name = "start_date")
	private LocalDate startDate;
	
	@Column(name = "release_date", nullable = true)
	private LocalDate releaseDate;
	
	@ManyToOne
	@JoinColumn(name = "cv_id", foreignKey = @ForeignKey(name = "cv_id"))
	private Cv cv;
	
}
