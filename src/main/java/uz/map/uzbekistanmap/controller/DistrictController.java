package uz.map.uzbekistanmap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.map.uzbekistanmap.dto.ApiResponse;
import uz.map.uzbekistanmap.dto.DistrictDTO;
import uz.map.uzbekistanmap.model.District;
import uz.map.uzbekistanmap.repositary.DistrictRepository;
import uz.map.uzbekistanmap.service.DistrictService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/district")
public class DistrictController {

    private final DistrictRepository districtRepository;
    private final DistrictService districtService;

    @Autowired
    public DistrictController(DistrictRepository districtRepository, DistrictService districtService) {
        this.districtRepository = districtRepository;
        this.districtService = districtService;
    }
    // hammasini chaqiradi
    @GetMapping("/all")
    public List<District> getAllDistrict(){
        return districtRepository.findAll();
    }
    //Id buyicha chaqirish
    @GetMapping("/{id}")
    public District getByIdDistrict(@PathVariable Integer id){
        Optional<District> byId = districtRepository.findById(id);
        return byId.orElseGet(District::new);
    }
    // bu esa Region id buyicha chaqirish

    @GetMapping("/regionId/{regionId}")
    public List<District>GetByRegionId(@PathVariable Integer regionId) {
        List<District> districtByRegionIdNative = districtRepository.getDistrictByRegionIdNative(regionId);
        return districtByRegionIdNative;
    }

    @PostMapping("/add")
    public ApiResponse addDistrict(@RequestBody DistrictDTO districtDTO){
        return districtService.addDistrict(districtDTO);
    }
    @PutMapping("/{id}")
    public ApiResponse editDistrict(@PathVariable Integer id,@RequestBody DistrictDTO districtDTO){
        return districtService.editDistrict(id, districtDTO);
    }
}
