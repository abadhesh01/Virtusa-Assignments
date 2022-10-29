package pkg.base.model;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Signup {
	@Size(min = 4, message = "Username length should not be less than 4 characters.")
	private String username;
	@Size(min = 4, message = "New password length should not be less than 6 characters.")
	private String newPassword;
	@Size(min = 4, message = "Confirm password length should not be less than 6 characters.")
	private String confirmPassword;

	@Override
	public String toString() {
		return "Signup [username=" + username + ", newPassword=" + newPassword + ", confirmPassword=" + confirmPassword
				+ "]";
	}
}
