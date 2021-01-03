package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entidades.Seller;

public class Program {

	public static void main(String[] args) {
		
		
	
		SellerDao sellerDao = DaoFactory.CreateSellerDao();
		
		List<Seller> sell = sellerDao.findAll();
		
		for(Seller obj : sell){
			System.out.println(obj);
		}
		
		
		
	}

}
