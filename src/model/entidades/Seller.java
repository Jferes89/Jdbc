package model.entidades;

import java.io.Serializable;
import java.util.Date;

public class Seller implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String name;
	private String Email;
	private Date birthDate;
	private Double baseSalary;

	private Departmentd departmentd;

	public Seller() {

	}

	public Seller(long id, String name, String email, Date birthDate, Double baseSalary, Departmentd departmentd) {
		this.id = id;
		this.name = name;
		Email = email;
		this.birthDate = birthDate;
		this.baseSalary = baseSalary;
		this.departmentd = departmentd;
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

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public Departmentd getDepartmentd() {
		return departmentd;
	}

	public void setDepartmentd(Departmentd departmentd) {
		this.departmentd = departmentd;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seller other = (Seller) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Seller [id=" + id + ", name=" + name + ", Email=" + Email + ", birthDate=" + birthDate + ", baseSalary="
				+ baseSalary + ", departmentd=" + departmentd + "]";
	}

}
