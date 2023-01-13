package kodlamaio.hrms.entities.concretes;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
