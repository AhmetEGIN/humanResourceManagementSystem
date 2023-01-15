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
import jakarta.persistence.OneToOne;
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
@Table(name = "github_accounts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "Lazy"})
public class GithubAccount {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "account_address")
	@NotNull
	@NotBlank
	private String accountAddress;
	
	@Column(name = "created_Date")
	private LocalDate createdDate = LocalDate.now();
	
	@Column(name = "is_active", columnDefinition = "BOOLEAN DEFAULT true")
	private boolean isActive = true;
	
	@OneToOne
	@JoinColumn(name = "cv_id",foreignKey = @ForeignKey(name = "cv_id"), referencedColumnName = "id")
	private Cv cv;
	
	
}
