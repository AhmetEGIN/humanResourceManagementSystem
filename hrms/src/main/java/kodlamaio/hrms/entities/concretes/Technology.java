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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "technologies")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "Lazy"})
public class Technology {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "technology_name")
	@NotNull
	@NotBlank
	private String technologyName;
	
	@Column(name = "created_date")
	private LocalDate createdDate = LocalDate.now();
	
	@ManyToOne
	@JoinColumn(name = "cv_id", foreignKey = @ForeignKey(name = "cv_id"), referencedColumnName = "id")
	private Cv cv; 
	
}
