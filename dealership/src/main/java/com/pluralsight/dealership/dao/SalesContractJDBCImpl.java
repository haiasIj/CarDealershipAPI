package com.pluralsight.dealership.dao;

import com.pluralsight.dealership.models.SalesContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class SalesContractJDBCImpl {

    private final DataSource dataSource;

    @Autowired
    public SalesContractJDBCImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public SalesContract getById(int id) {
        String sql = "SELECT * FROM sales_contracts WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new SalesContract(
                        rs.getInt("id"),
                        rs.getString("customerName"),
                        rs.getString("customerEmail"),
                        rs.getInt("vehicleId"),
                        rs.getDate("contractDate").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addSalesContract(SalesContract contract) {
        String sql = "INSERT INTO sales_contracts (customerName, customerEmail, vehicleId, contractDate) VALUES (?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, contract.getCustomerName());
            stmt.setString(2, contract.getCustomerEmail());
            stmt.setInt(3, contract.getVehicleId());
            stmt.setDate(4, Date.valueOf(contract.getContractDate()));
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSalesContract(SalesContract contract) {
        String sql = "UPDATE sales_contracts SET customerName = ?, customerEmail = ?, vehicleId = ?, contractDate = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, contract.getCustomerName());
            stmt.setString(2, contract.getCustomerEmail());
            stmt.setInt(3, contract.getVehicleId());
            stmt.setDate(4, Date.valueOf(contract.getContractDate()));
            stmt.setInt(5, contract.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSalesContract(int id) {
        String sql = "DELETE FROM sales_contracts WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
