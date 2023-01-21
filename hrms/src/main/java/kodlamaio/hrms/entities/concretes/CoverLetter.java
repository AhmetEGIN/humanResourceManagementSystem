package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cover_letters")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "Lazy"})
public class CoverLetter {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "description")
	@NotBlank
	@NotEmpty
	private String description;
	
	@Column(name = "created_date")
	private LocalDate createdDate = LocalDate.now();
	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "cv_id",foreignKey = @ForeignKey(name = "cv_id"), referencedColumnName = "id")
	private Cv cv;
}
