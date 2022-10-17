package solution2.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Company {

	@Id
	private long id;
	private String name;

	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Employee> employees;

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}

	public Company(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

}
