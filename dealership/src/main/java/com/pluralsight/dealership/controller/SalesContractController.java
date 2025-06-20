package com.pluralsight.dealership.controller;

import com.pluralsight.dealership.dao.SalesContractJDBCImpl;
import com.pluralsight.dealership.models.SalesContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sales-contracts")

public class SalesContractController {
    private final SalesContractJDBCImpl salesContractDAO;

    @Autowired
    public SalesContractController(SalesContractJDBCImpl salesContractDAO) {
        this.salesContractDAO = salesContractDAO;
    }

    @GetMapping("/{id}")
    public SalesContract getContractById(@PathVariable int id) {
        return salesContractDAO.getById(id);
    }

    // POST /sales-contracts
    @PostMapping
    public void addSalesContract(@RequestBody SalesContract contract) {
        salesContractDAO.addSalesContract(contract);
    }

    // PUT /sales-contracts/{id}
    @PutMapping("/{id}")
    public void updateSalesContract(@PathVariable int id, @RequestBody SalesContract contract) {
        contract.setId(id);
        salesContractDAO.updateSalesContract(contract);
    }

    // DELETE /sales-contracts/{id}
    @DeleteMapping("/{id}")
    public void deleteSalesContract(@PathVariable int id) {
        salesContractDAO.deleteSalesContract(id);
    }
}
