package web.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column
	@Pattern(regexp = "[A-Za-z]{2,15}", message = "Name should be between 2 and 15 latin characters")
	private String name;
	
	@Column
	@Pattern(regexp = "[A-Za-z]{2,15}", message = "LastName should be between 2 and 15 latin characters")
	private String lastName;
	
	@Column
	@Min(value = 0, message = "Age should be >= 0")
	@Max(value = 127, message = "Age should be < 128")
	private Byte age;
	
	public User() {
	}
	
	public User(String name, String lastName, Byte age) {
		this.name = name;
		this.lastName = lastName;
		this.age = age;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Byte getAge() {
		return age;
	}
	
	public void setAge(Byte age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return  """
                
                User  [ ID =        %s,
                        Name =      %s,
                        Last Name = %s,
                        Age =       %s ]
                """
				.formatted(id, name, lastName, age);
	}
}
