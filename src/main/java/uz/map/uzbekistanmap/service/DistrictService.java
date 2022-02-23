package uz.map.uzbekistanmap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.map.uzbekistanmap.dto.ApiResponse;
import uz.map.uzbekistanmap.dto.DistrictDTO;
import uz.map.uzbekistanmap.model.District;
import uz.map.uzbekistanmap.repositary.DistrictRepository;
import uz.map.uzbekistanmap.repositary.RegionRepository;

import java.util.Optional;

@Service
public class DistrictService {
    private final RegionRepository regionRepository;
    private final DistrictRepository districtRepository;
    @Autowired
    public DistrictService(RegionRepository regionRepository, DistrictRepository districtRepository) {
        this.regionRepository = regionRepository;
        this.districtRepository = districtRepository;
    }

    public ApiResponse addDistrict(DistrictDTO districtDTO){
        if (districtRepository.existsByNameAndRegionId(districtDTO.getName(),districtDTO.getRegionId()))
            return new ApiResponse("Already exist District",false);
        if (!regionRepository.findById(districtDTO.getRegionId()).isPresent())
            return new ApiResponse("Region not found",false);

        District district = new District();
        district.setName(districtDTO.getName());
        district.setRegion(regionRepository.findById(districtDTO.getRegionId()).get());
        districtRepository.save(district);
        return new ApiResponse("Successfully added",true);
    }
    public ApiResponse editDistrict(Integer id,DistrictDTO districtDTO){
        Optional<District> optionalDistrict = districtRepository.findById(id);
        if (!optionalDistrict.isPresent())
            return new ApiResponse("District not found",false);
        if (districtRepository.existsByNameAndRegionId(districtDTO.getName(),districtDTO.getRegionId()))
            return new ApiResponse("Already exist district",false);
        if (!regionRepository.findById(districtDTO.getRegionId()).isPresent())
            return new ApiResponse("Region not found",false);
        District district = optionalDistrict.get();
        district.setName(districtDTO.getName());
        district.setRegion(regionRepository.findById(districtDTO.getRegionId()).get());
        districtRepository.save(district);
        return new ApiResponse("Successfully edited",true);
    }

    public ApiResponse deletedDistrict(Integer id){
        Optional<District> optionalDistrict = districtRepository.findById(id);
        if (optionalDistrict.isPresent()){
            districtRepository.deleteById(id);
            return new ApiResponse("successfully deleted",true);

        }else return new ApiResponse("District not found",false);
    }
}
