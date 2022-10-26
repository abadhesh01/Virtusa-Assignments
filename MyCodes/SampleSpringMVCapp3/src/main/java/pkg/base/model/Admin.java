package pkg.base.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
	private String username;
	@Size(min = 8, max = 15, message = "Password length must be between 8 to 15 characters.")
	private String password;
	@NotEmpty(message = "Mood status cannot be empty.")
	private String mood;

	@Override
	public String toString() {
		return "Admin [username=" + username + ", mood=" + mood + "]";
	}
}
