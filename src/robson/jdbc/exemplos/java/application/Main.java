package robson.jdbc.exemplos.java.application;

import robson.jdbc.exemplos.java.model.dao.DaoFactory;
import robson.jdbc.exemplos.java.model.dao.SellerDao;
import robson.jdbc.exemplos.java.model.entities.Department;
import robson.jdbc.exemplos.java.model.entities.Seller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) throws ParseException {

        SellerDao sellerDao = DaoFactory.createSellerDao();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Department department = new Department(1,null);

        System.out.println("===== TEST 1: seller insert =====");
        Seller newSeller;
        newSeller = new Seller(null, "Pedro", "pedro@gmail.com", sdf.parse("03/03/1990"), 2500.0, department);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! New id = " + newSeller.getId());

        System.out.println("=======================================================");

        System.out.println("=== TEST 2: seller findById =====");
        Seller seller = sellerDao.findById(1);
        System.out.println(seller);
    }
}