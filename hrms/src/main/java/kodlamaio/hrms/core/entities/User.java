package kodlamaio.hrms.core.entities;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
	//Bir entity' nin inherit edilebilmesi için bu class'a Inheritance anotasyonunu eklemeliyiz
	// Inheritance yapılan entity'lerde farklı tablo stratejileri vardır. Single-Table, Joined, Table-Per-Class
	// Joined en çok kullanılan yöntemdir. Veriler farklı tablolarda tutulur. 
	// Ata ve kalıtılan sınıflar birbirlerine foreign key ile bağlanır. Yani veri normalizasyona tabi tutulur.
	// Superclasslardaki fieldlar subclass ların tablosunda olmaz.
	// Subclass ta da PrimaryKeyJoinColumn anotasyonunu tanımlamamız gerekir.
	
	//SingleTable -- Eğer bir strategy belirlemezsek default olarak bu seçilidir.
	// Veriler aynı tabloda saklanır. superclass ve bu class'ı extend eden classların fieldları bir tablo içerisinde olur.
	// Ata sınıfta @DiscriminatorColumn ile tipleri ayırt etmek için kullanılacak sütun ismi belirtilir. 
    // Kalıtan sınıftaysa @DiscriminatorValue ile tipi ayırt etmek için kullanılacak değer belirtilir.
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "email")
	@Email
	@NotEmpty
	@NotNull
	private String email;

	@Column(name = "password")
	@NotNull
	@NotBlank
	@NotEmpty
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
