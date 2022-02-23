package uz.map.uzbekistanmap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.map.uzbekistanmap.dto.ApiResponse;
import uz.map.uzbekistanmap.model.Region;
import uz.map.uzbekistanmap.repositary.RegionRepository;

import java.util.Optional;

@Service
public class RegionService {
        private final RegionRepository regionRepository;
    @Autowired
    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public ApiResponse addRegion(Region region){
        if (regionRepository.existsByName(region.getName()))
            return  new ApiResponse("Already exist Region",false);
        Region region1 = new Region();
        region1.setName(region.getName());
        regionRepository.save(region1);
        return new ApiResponse("Successfully Added",true);
    }

    public ApiResponse editRegion(Integer id,Region region){
        Optional<Region> optionalRegion = regionRepository.findById(id);
        if (!optionalRegion.isPresent())
            return new ApiResponse("Region not found",false);
        if (regionRepository.existsByName(region.getName()))
            return new ApiResponse("Already exist Region",false);
        Region region1 = optionalRegion.get();
        region1.setName(region.getName());
        regionRepository.save(region1);
        return new ApiResponse("Successfully edited",true);
    }
    public ApiResponse deletedRegion(Integer id){
        Optional<Region> regionOptional = regionRepository.findById(id);
        if (regionOptional.isPresent()){
            regionRepository.deleteById(id);
            return  new ApiResponse("Successfully deleted",true);
        }else return new ApiResponse("Region not found",false);

    }
}
