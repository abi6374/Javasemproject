import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VisitorDAO {

    // CREATE
    public void addVisitor(Visitor v) {
        String sql = "INSERT INTO visitors (name, purpose, contact) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, v.getName());
            stmt.setString(2, v.getPurpose());
            stmt.setString(3, v.getContact());
            stmt.executeUpdate();
            System.out.println("Visitor added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // READ - all visitors
    public List<Visitor> getAllVisitors() {
        List<Visitor> list = new ArrayList<>();
        String sql = "SELECT * FROM visitors ORDER BY checkin DESC";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Visitor(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("purpose"),
                        rs.getString("contact"),
                        rs.getString("checkin")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

//reading by ID
    public Visitor getVisitorById(int id) {
        String sql = "SELECT * FROM visitors WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Visitor(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("purpose"),
                            rs.getString("contact"),
                            rs.getString("checkin")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
//    Updating the visitor
    public void updateVisitor(int id, String name, String purpose, String contact) {
        String sql = "UPDATE visitors SET name = ?, purpose = ?, contact = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, purpose);
            stmt.setString(3, contact);
            stmt.setInt(4, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("Visitor updated successfully!");
            else System.out.println("No visitor found with ID " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteVisitor(int id) {
        String sql = "DELETE FROM visitors WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("Visitor deleted successfully!");
            else System.out.println("️ No visitor found with ID " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Search by name or purpose
    public List<Visitor> searchVisitors(String keyword) {
        List<Visitor> list = new ArrayList<>();
        String sql = "SELECT * FROM visitors WHERE name LIKE ? OR purpose LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new Visitor(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("purpose"),
                            rs.getString("contact"),
                            rs.getString("checkin")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

