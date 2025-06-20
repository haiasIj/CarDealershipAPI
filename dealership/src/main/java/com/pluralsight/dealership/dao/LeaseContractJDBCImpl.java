package com.pluralsight.dealership.dao;

import com.pluralsight.dealership.models.LeaseContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class LeaseContractJDBCImpl {

    private final DataSource dataSource;

    @Autowired
    public LeaseContractJDBCImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public LeaseContract getById(int id) {
        String sql = "SELECT * FROM lease_contracts WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new LeaseContract(
                        rs.getInt("id"),
                        rs.getString("customerName"),
                        rs.getString("customerEmail"),
                        rs.getInt("vehicleId"),
                        rs.getDate("contractDate")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addLeaseContract(LeaseContract contract) {
        String sql = "INSERT INTO lease_contracts (customerName, customerEmail, vehicleId, contractDate) VALUES (?, ?, ?, ?)";
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

    public void updateLeaseContract(LeaseContract contract) {
        String sql = "UPDATE lease_contracts SET customerName = ?, customerEmail = ?, vehicleId = ?, contractDate = ? WHERE id = ?";
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

    public void deleteLeaseContract(int id) {
        String sql = "DELETE FROM lease_contracts WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
