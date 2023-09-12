package robson.jdbc.exemplos.java.model.dao;

import robson.jdbc.exemplos.java.db.DB;
import robson.jdbc.exemplos.java.model.dao.implementations.SellerDaoJDBC;

public class DaoFactory {
    public static SellerDao createSellerDao(){
        return new SellerDaoJDBC(DB.getConnection());
    }
}
