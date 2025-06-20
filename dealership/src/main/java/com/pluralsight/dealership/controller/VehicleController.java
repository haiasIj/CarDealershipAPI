package com.pluralsight.dealership.controller;

import com.pluralsight.dealership.dao.VehicleJDBCImpl;
import com.pluralsight.dealership.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vehicles")

public class VehicleController {
    private final VehicleJDBCImpl vehicleDAO;

    @Autowired
    public VehicleController(VehicleJDBCImpl vehicleDAO) {
        this.vehicleDAO = vehicleDAO;
    }

    @GetMapping
    public List<Vehicle> searchVehicle (
        @RequestParam(required = false) Double minPrice,
        @RequestParam(required = false) Double maxPrice,
        @RequestParam(required = false) String make,
        @RequestParam(required = false) String model,
        @RequestParam(required = false) Integer minYear,
        @RequestParam(required = false) Integer maxYear,
        @RequestParam(required = false) String color,
        @RequestParam(required = false) Integer minMiles,
        @RequestParam(required = false) Integer maxMiles,
        @RequestParam(required = false) String type) {


        return vehicleDAO.getAllVehicles().stream()
                .filter(v -> minPrice == null || v.getPrice() >= minPrice)
                .filter(v -> maxPrice == null || v.getPrice() <= maxPrice)
                .filter(v -> make == null || v.getMake().equalsIgnoreCase(make))
                .filter(v -> model == null || v.getModel().equalsIgnoreCase(model))
                .filter(v -> minYear == null || v.getYear() >= minYear)
                .filter(v -> maxYear == null || v.getYear() <= maxYear)
                .filter(v -> color == null || v.getColor().equalsIgnoreCase(color))
                .filter(v -> minMiles == null || v.getOdometer() >= minMiles)
                .filter(v -> maxMiles == null || v.getOdometer() <= maxMiles)
                .filter(v -> type == null || v.getVehicleType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }
    @PostMapping
    public void addVehicle(@RequestBody Vehicle vehicle) {
        vehicleDAO.addVehicle(vehicle);
    }
    @PutMapping("/{id}")
    public void updateVehicle(@PathVariable int id, @RequestBody Vehicle vehicle) {
        vehicle.setId(id);
        vehicleDAO.updateVehicle(vehicle);
    }
    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable int id) {
        vehicleDAO.deleteVehicle(id);
    }

}
