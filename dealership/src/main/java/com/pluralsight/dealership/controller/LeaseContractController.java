package com.pluralsight.dealership.controller;

import com.pluralsight.dealership.dao.LeaseContractJDBCImpl;
import com.pluralsight.dealership.dao.LeaseContractJDBCImpl;
import com.pluralsight.dealership.models.LeaseContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lease-contracts")

public class LeaseContractController {
    private final LeaseContractJDBCImpl leaseContractDAO;

    @Autowired
    public LeaseContractController(LeaseContractJDBCImpl leaseContractDAO) {
        this.leaseContractDAO = leaseContractDAO;
    }

    @GetMapping("/{id}")
    public LeaseContract getContractById(@PathVariable int id) {
        return leaseContractDAO.getById(id);
    }

    // POST /sales-contracts
    @PostMapping
    public void addLeaseContract(@RequestBody LeaseContract contract) {
        leaseContractDAO.addLeaseContract(contract);
    }

    // PUT /sales-contracts/{id}
    @PutMapping("/{id}")
    public void updateLeaseContract(@PathVariable int id, @RequestBody LeaseContract contract) {
        contract.setId(id);
        leaseContractDAO.updateLeaseContract(contract);
    }

    // DELETE /sales-contracts/{id}
    @DeleteMapping("/{id}")
    public void deleteLeaseContract(@PathVariable int id) {
        leaseContractDAO.deleteLeaseContract(id);
    }
}
