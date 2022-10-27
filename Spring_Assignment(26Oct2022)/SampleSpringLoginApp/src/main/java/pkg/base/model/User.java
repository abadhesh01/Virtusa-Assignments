package pkg.base.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "users")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@Size(min = 3, max = 15, message = "Username must be between 3 to 15 charcaters.")
	private String username;
	@Size(min = 6, max = 15, message = "Password must be between 6 to 15 charcaters.")
	private String password;

	@Override
	public String toString() {
		return "User [username=" + username + "]";
	}
}
