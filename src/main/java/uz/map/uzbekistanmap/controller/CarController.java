package uz.map.uzbekistanmap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.map.uzbekistanmap.dto.ApiResponse;
import uz.map.uzbekistanmap.dto.CarDTO;
import uz.map.uzbekistanmap.model.Car;
import uz.map.uzbekistanmap.repositary.CarRepositary;
import uz.map.uzbekistanmap.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    private final CarRepositary carRepositary;
    private final CarService carService;
    @Autowired
    public CarController(CarRepositary carRepositary, CarService carService) {
        this.carRepositary = carRepositary;
        this.carService = carService;
    }
    @PostMapping("/add")
    public ApiResponse addCar(@RequestBody CarDTO carDTO){
        return carService.addCar(carDTO);
    }

    @GetMapping("/all")
    public List<Car> getAll(){
        return carRepositary.findAll();
    }

    @GetMapping("/redionId/{regionId}")
    public List<Car> getByRegionIdCars(@PathVariable Integer regionId){
        return carRepositary.getCarByRegionId(regionId);
    }

    @GetMapping("/userById/{userId}")
    public List<Car> getByUesrId(@PathVariable Integer userId){
        return carRepositary.getCarByUserNative(userId);
    }
}
