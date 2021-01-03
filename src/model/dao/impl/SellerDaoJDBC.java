package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DbException;
import model.dao.SellerDao;
import model.entidades.Departmentd;
import model.entidades.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		PreparedStatement st = null;
		
		
		try {
			st = conn.prepareStatement("INSERT INTO seller "
						+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
						+ "VALUES "
						+ " (?, ?, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS);
	
		st.setString(1, obj.getName());
		st.setString(2, obj.getEmail());
		st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
		st.setDouble(4, obj.getBaseSalary());
		st.setInt(5, obj.getDepartmentd().getId());
			
		int rowsAffected = st.executeUpdate();

		if(rowsAffected > 0 ) {
			ResultSet rs = st.getGeneratedKeys();
			while(rs.next()){
				int id  = rs.getInt(1);
				obj.setId(id);
				
			}
		}else {
			System.out.println("No rows Affected ");
		}
			
			
		} catch (SQLException e) {
				throw new DbException(e.getMessage());
		} 
		
		
		
		
	

	}

	@Override
	public void update(Seller obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE seller "
					+ "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
					+ "WHERE Id = ?"
					);
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getBaseSalary());
			st.setInt(5, obj.getDepartmentd().getId());
			st.setLong(6, obj.getId());
			
			st.executeUpdate();
					
		}catch(SQLException e ) {
			throw new DbException(e.getMessage());
		}

		
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
		st = conn.prepareStatement("DELETE FROM seller"
				+ "WHERE Id = ?"
				);
		st.setInt(1, id);
		
		
	}catch(SQLException e) {
		throw new DbException(e.getMessage());
	}
		
		
	}

	@Override
	public Seller findbyid(Integer id) {
			PreparedStatement st = null;
			ResultSet rs = null;
		  
			try {
				st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
				  					+ "FROM seller INNER JOIN department "
				  					+ "ON seller.DepartmentId = department.Id "
				  					+ " WHERE seller.Id = ? ");  
			
				st.setInt(1, id);
				rs = st.executeQuery();
				if(rs.next()) {
					Departmentd dep =  instantiateDepartment(rs); 	
					Seller sl = instantiateSeller(rs , dep);
				
			 		return sl;
			
				}else {
					return null;
				 }
		  
			}catch(Exception err) {
				throw new DbException(err.getMessage());	
			}
		
		
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
  					+ "FROM seller INNER JOIN department "
  					+ "ON seller.DepartmentId = department.Id "
  					+ "ORDER BY Name ");

			
			rs = st.executeQuery();		
			
			List<Seller> listSeller = new ArrayList<>();
		    Map<Integer,Departmentd> map = new HashMap<>();
		    while(rs.next()) {
				
				Departmentd dep= map.get(rs.getInt("DepartmentId"));
				
				if(dep == null){
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
					
				}
				
				Seller sl = instantiateSeller(rs , dep);
				listSeller.add(sl);
				
		 		
			}
			return listSeller;
			
		}catch(Exception err) {
			throw new DbException(err.getMessage());	
		}
		
	
	}
	
	
	private Departmentd  instantiateDepartment(ResultSet rs) throws SQLException {
		Departmentd dep = new Departmentd();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep; 
	}

	private Seller instantiateSeller(ResultSet rs, Departmentd dep) throws SQLException{
		Seller sl = new Seller();
		sl.setId(rs.getInt("Id"));
		sl.setName(rs.getString("Name"));
		sl.setEmail(rs.getString("Email"));
		sl.setBirthDate(rs.getDate("BirthDate"));
		sl.setBaseSalary(rs.getDouble("BaseSalary"));
		sl.setDepartmentd(dep);
		return sl; 
	  
	}

	@Override
	public List<Seller> findByDepartmetd(Departmentd departmentd) {
		PreparedStatement st = null;
		ResultSet rs = null;
	  
		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
			  					+ "FROM seller INNER JOIN department "
			  					+ "ON seller.DepartmentId = department.Id "
			  					+ " WHERE DepartmentId = ? "
			  					+ " ORDER BY Name ");  
		
			st.setInt(1, departmentd.getId());
			
			rs = st.executeQuery();
			List<Seller> listSeller = new ArrayList<>();
		    Map<Integer,Departmentd> map = new HashMap<>();
			while(rs.next()) {
				
				Departmentd dep= map.get(rs.getInt("DepartmentId"));
				
				if(dep == null){
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
					
				}
				
				Seller sl = instantiateSeller(rs , dep);
				listSeller.add(sl);
				
		 		
			}
			return listSeller;
			
		}catch(Exception err) {
			throw new DbException(err.getMessage());	
		}
	

	}



}
