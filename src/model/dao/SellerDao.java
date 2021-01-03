package model.dao;

import java.util.List;

import model.entidades.Departmentd;
import model.entidades.Seller;

public interface SellerDao {

	void insert(Seller obj);
	void update(Seller obj);
	void deleteById(Integer id);
	Seller findbyid(Integer id);
	List<Seller> findAll();
	List<Seller> findByDepartmetd(Departmentd dep);
	
	
	
}
