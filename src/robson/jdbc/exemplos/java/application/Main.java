package robson.jdbc.exemplos.java.application;

import robson.jdbc.exemplos.java.model.dao.DaoFactory;
import robson.jdbc.exemplos.java.model.dao.SellerDao;
//import robson.jdbc.exemplos.java.model.entities.Department;
import robson.jdbc.exemplos.java.model.entities.Seller;

//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();
        /*
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Department department = new Department(1,null);

        System.out.println("===== TEST 1: seller insert =====");
        Seller newSeller;
        newSeller = new Seller(null, "Carlos", "carlos@gmail.com", sdf.parse("10/04/1995"), 1900.0, department);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! New id = " + newSeller.getId());
        */

        System.out.println("=== TEST 2: seller findById =====");
        Seller seller = sellerDao.findById(1);
        System.out.println(seller);

        System.out.println("\n=== TEST 3: seller findAll =====");
        List<Seller> sellerAll = sellerDao.findAll();
        for (Seller obj : sellerAll){
            System.out.println(obj);
        }
    }
}