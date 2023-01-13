package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	@OneToOne
	@JoinColumn(name = "cv_id",foreignKey = @ForeignKey(name = "cv_id"), referencedColumnName = "id")
	private Cv cv;
}
