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
import kodlamaio.hrms.core.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "images")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "Lazy"})
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "image_path")
	private String imagePath;

	@Column(name = "created_date")
	private LocalDate createdDate = LocalDate.now();
	
	@Column(name = "image_type")
	private kodlamaio.hrms.entities.enums.ImageType imageType;
	
	@ManyToOne
	@JoinColumn(name = "employee_id", foreignKey = @ForeignKey(name = "employee_id"))
	private User user;
	
	 
}
