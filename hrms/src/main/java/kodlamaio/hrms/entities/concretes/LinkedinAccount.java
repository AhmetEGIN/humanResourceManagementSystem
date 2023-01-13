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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "linkedin_accounts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "Lazy"})
public class LinkedinAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "account_address")
	@NotNull
	@NotBlank
	private String accountAddress;
	
	@Column(name = "created_date")
	private LocalDate createdDate = LocalDate.now();
	
	@Column(name = "is_active", columnDefinition = "boolean default true")
	private boolean isActive = true;
	
	@OneToOne
	@JoinColumn(name = "cv_id",foreignKey = @ForeignKey(name = "cv_id"), referencedColumnName = "id")
	private Cv cv;
	
}
