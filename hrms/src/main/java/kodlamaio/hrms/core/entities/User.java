package kodlamaio.hrms.core.entities;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import kodlamaio.hrms.core.entities.enums.Role;
import kodlamaio.hrms.entities.concretes.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "email")
	@Email
	private String email;

	@Column(name = "password")
	@NotNull
	@NotBlank
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private Role role;

	@Column(name = "is_email_verified", columnDefinition = "BOOLEAN DEFAULT false")
	private boolean isEmailVerified;

	@Column(name = "created_date")
	private LocalDate createdDate = LocalDate.now();

	@Column(name = "is_active", columnDefinition = "boolean default false")
	private boolean isActive;

	@OneToMany(mappedBy = "user", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<Image> images;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

}
