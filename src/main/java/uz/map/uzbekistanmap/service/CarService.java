package uz.map.uzbekistanmap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.map.uzbekistanmap.dto.ApiResponse;
import uz.map.uzbekistanmap.dto.CarDTO;
import uz.map.uzbekistanmap.model.Car;
import uz.map.uzbekistanmap.repositary.CarRepositary;
import uz.map.uzbekistanmap.repositary.RegionRepository;

@Service
public class CarService {
    private final RegionRepository regionRepository;
    private final CarRepositary carRepositary;
    @Autowired
    public CarService(RegionRepository regionRepository, CarRepositary carRepositary) {
        this.regionRepository = regionRepository;
        this.carRepositary = carRepositary;
    }

    public ApiResponse addCar(CarDTO carDTO){
        if (carRepositary.existsByStateNumber(carDTO.getStateNumber()))
            return new ApiResponse("Already exist StateNumber Car",false);
        if (!regionRepository.findById(carDTO.getRegionId()).isPresent())
            return new ApiResponse("Not found Region",false);
        Car car = new Car();
        car.setModel(carDTO.getModel());
        car.setMadeYear(carDTO.getMadeYear());
        car.setType(carDTO.getType());
        car.setRegion(regionRepository.findById(carDTO.getRegionId()).get());
        car.setStateNumber(carDTO.getStateNumber());
        carRepositary.save(car);
        return new ApiResponse("Successfully Added",true);
    }

}
