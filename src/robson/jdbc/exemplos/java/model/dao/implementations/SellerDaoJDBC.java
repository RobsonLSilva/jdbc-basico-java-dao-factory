package robson.jdbc.exemplos.java.model.dao.implementations;

import robson.jdbc.exemplos.java.db.DB;
import robson.jdbc.exemplos.java.model.dao.SellerDao;
import robson.jdbc.exemplos.java.model.entities.Department;
import robson.jdbc.exemplos.java.model.entities.Seller;

import java.sql.*;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {

    private final Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException { // throws = propaga a exceção
        Seller obj = new Seller();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setBirthDate(rs.getDate("BirthDate"));
        obj.setDepartment(dep);
        return obj;
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    @Override
    public void insert(Seller obj) {
        String sql = "INSERT INTO seller (name, email, birthDate, baseSalary, departmentId) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setDate(3,new java.sql.Date(obj.getBirthDate().getTime()));
            st.setDouble(4, obj.getBaseSalary());
            st.setInt(5, obj.getDepartment().getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new RuntimeException("No rows affected!");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Seller obj) {
        String sql = "UPDATE seller SET name=?, email=?, birthDate=?, baseSalary=?, departmentId=? WHERE id=?";
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            st.setDouble(4, obj.getBaseSalary());
            st.setInt(5, obj.getDepartment().getId());
            st.setInt(6, obj.getId());

            st.executeUpdate();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "Delete from seller where id=?";
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement(sql);
            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Seller findById(Integer id) {
        String sql = "SELECT seller.*, department.Name as DepName "
                     +"FROM seller "
                     +"INNER JOIN department ON seller.DepartmentId = department.Id "
                     +"WHERE seller.Id = ?";
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {    // se veio alguma coisa na minha consulta
                Department dep = instantiateDepartment(rs);
                return instantiateSeller(rs, dep);
            }
            return null;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
        return null;
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        return null;
    }
}
