package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;

import org.springframework.lang.Nullable;

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
@Table(name = "schools")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "Lazy"})
public class School {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "department_name")
	private String departmentName;
	
	@Column(name = "start_year")
	private LocalDate startYear;
	
	@Column(name = "graduation_year")
	@Nullable
	private LocalDate graduationYear;
	
	
	@JoinColumn(name = "cv_id", foreignKey = @ForeignKey(name = "cv_id") , referencedColumnName = "id")
	@ManyToOne
	private Cv cv;
	
}
