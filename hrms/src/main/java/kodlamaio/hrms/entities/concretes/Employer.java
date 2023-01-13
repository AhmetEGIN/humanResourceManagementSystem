package kodlamaio.hrms.entities.concretes;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonBackReference;

import kodlamaio.hrms.core.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "employers")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
@EqualsAndHashCode(callSuper = false)
public class Employer extends User {
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "web_site")
	private String webSite;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "is_verified_from_admin", columnDefinition = "boolean default false")
	private boolean isVerifiedFromAdmin = false;
	
	@JsonBackReference
	@OneToMany(mappedBy = "employer", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	List<JobAdvertisement> jobAdvertisements;
	
}
