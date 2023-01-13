package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
