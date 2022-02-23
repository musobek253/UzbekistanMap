package uz.map.uzbekistanmap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.map.uzbekistanmap.dto.ApiResponse;
import uz.map.uzbekistanmap.model.Region;
import uz.map.uzbekistanmap.repositary.RegionRepository;
import uz.map.uzbekistanmap.service.RegionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/region")
public class RegionController {
    private final RegionService regionService;
    private final RegionRepository regionRepository;
    @Autowired
    public RegionController(RegionService regionService, RegionRepository regionRepository) {
        this.regionService = regionService;
        this.regionRepository = regionRepository;
    }

    @PostMapping("/add")
    public ApiResponse addRegion(@RequestBody Region region){
        return regionService.addRegion(region);
    }

    @PutMapping("/{id}")
    public ApiResponse editRegion(@PathVariable Integer id,@RequestBody Region region){
        return regionService.editRegion(id, region);
    }
    @GetMapping("/all")
    public List<Region> getRegionAll(){
        return regionRepository.findAll();
    }
    @GetMapping("/{id}")
    public Region getById(@PathVariable Integer id){
        Optional<Region> byId = regionRepository.findById(id);
        if (byId.isPresent())
            return byId.get();
        return new Region();
    }

}
