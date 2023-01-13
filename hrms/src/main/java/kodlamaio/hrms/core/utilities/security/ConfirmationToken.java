package kodlamaio.hrms.core.utilities.security;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import kodlamaio.hrms.core.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "confirmation_tokens")
public class ConfirmationToken {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "token")
	@NotNull
	private String token;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@Column(name = "expires_at")
	private LocalDateTime expiresAt = LocalDateTime.now();
	
	private LocalDateTime confirmedAt; 
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;

	public ConfirmationToken(@NotNull String token, LocalDateTime createdAt, LocalDateTime expiresAt,
			 User user) {
		this.token = token;
		this.createdAt = createdAt;
		this.expiresAt = expiresAt;
		this.user = user;
	}	
}
