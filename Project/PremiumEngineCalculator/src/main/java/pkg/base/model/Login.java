package pkg.base.model;

import java.io.Serializable;

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
public class Login implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2938350161186506539L;
	@Size(min = 4, message = "Username length should not be less than 4 characters.")
	private String username;
	@Size(min = 6, message = "Password length should not be less than 6 characters.")
	private String password;
	private String loginTime;

	@Override
	public String toString() {
		return "Login [username=" + username + ", password=" + password + ", loginTime=" + loginTime + "]";
	}
}
