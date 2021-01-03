package model.dao;

import java.util.List;

import model.entidades.Departmentd;

public interface DepartmentDao {
	 
	void insert(Departmentd obj);
	void update(Departmentd obj);
	void deleteById(Integer id);
	Departmentd findbyid(Integer id);
	List<Departmentd> findAll();
	
	
	
	
}
