package kodlamaio.hrms.entities.concretes;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jobAdvertisements")
public class JobAdvertisement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "description")
	private String description;

	@Column(name = "min_salary")
	@Nullable
	private int minSalary;

	@Column(name = "max_salary")
	@Nullable
	private int maxSalary;

	@Column(name = "vacant_position_count")
	@NotEmpty
	private int vacantPositionCount;

	@Column(name = "deadline")
	private Date deadline;
	
	@Column(name = "release_date")
	private LocalDate releaseDate = LocalDate.now();

	@Column(name = "is_active", columnDefinition = "boolean default true")
	private boolean isActive = true;

	@ManyToOne
	@JoinColumn(name = "employer_id", foreignKey = @ForeignKey(name = "employer_id"))
	@JsonManagedReference
	private Employer employer;

	@ManyToOne
	@JoinColumn(name = "position_id", foreignKey = @ForeignKey(name = "position_id"))
	@JsonManagedReference
	private Position position;

	@ManyToOne
	@JoinColumn(name = "city_id", foreignKey = @ForeignKey(name = "city_id"))
	@JsonManagedReference
	private City city;

		
}
