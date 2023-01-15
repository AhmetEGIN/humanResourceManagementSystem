package kodlamaio.hrms.entities.concretes;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cvs")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "Lazy"})
public class Cv {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "employee_id", foreignKey = @ForeignKey(name = "employee_id"), referencedColumnName = "id")
	private Employee employee;
	
	@OneToOne(mappedBy = "cv",cascade = CascadeType.REMOVE)
	private CoverLetter coverLetter;
	
	@OneToOne(mappedBy = "cv",cascade = CascadeType.REMOVE)
	private GithubAccount githubAccount; 
	
	@OneToOne(mappedBy = "cv",cascade = CascadeType.REMOVE)
	private LinkedinAccount linkedinAccount;
	
	@OneToMany(mappedBy = "cv", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<School> schools;
	
	@OneToMany(mappedBy = "cv", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<JobExperience> jobExperiences;
	
	@OneToMany(mappedBy = "cv", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Language> languages;
	
	@OneToMany(mappedBy = "cv", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Technology> technologies;
}
