package uz.map.uzbekistanmap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.map.uzbekistanmap.dto.AddressDTO;
import uz.map.uzbekistanmap.dto.ApiResponse;
import uz.map.uzbekistanmap.model.Address;
import uz.map.uzbekistanmap.repositary.AddressRepository;
import uz.map.uzbekistanmap.repositary.DistrictRepository;

import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private  final DistrictRepository districtRepository;
    @Autowired
    public AddressService(AddressRepository addressRepository, DistrictRepository districtRepository) {
        this.addressRepository = addressRepository;
        this.districtRepository = districtRepository;
    }

    public ApiResponse addAddress(AddressDTO addressDTO){
        if (addressRepository.existsByStreetAndHouseNumberAndDistrictId(addressDTO.getStreet(),addressDTO.getHouseNumber(),addressDTO.getDistrictId()))
            return new ApiResponse("Already exist address",false);
        if (!districtRepository.findById(addressDTO.getDistrictId()).isPresent())
            return new ApiResponse("District not found", false);
        Address address = new Address();
        address.setStreet(addressDTO.getStreet());
        address.setHouseNumber(addressDTO.getHouseNumber());
        address.setDistrict(districtRepository.findById(addressDTO.getDistrictId()).get());
        addressRepository.save(address);
        return new ApiResponse("Successfully added",true);
    }

    public ApiResponse editAddress(Integer id,AddressDTO addressDTO){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent()){
            return new ApiResponse("Address not found",false);
        }
        if (addressRepository.existsByStreetAndHouseNumberAndDistrictId(addressDTO.getStreet(),addressDTO.getHouseNumber(),addressDTO.getDistrictId()))
            return new ApiResponse("Already exist address",false);
        if (!districtRepository.findById(addressDTO.getDistrictId()).isPresent())
            return new ApiResponse("District not found", false);
        Address address = optionalAddress.get();

        address.setStreet(addressDTO.getStreet());
        address.setHouseNumber(addressDTO.getHouseNumber());
        address.setDistrict(districtRepository.findById(addressDTO.getDistrictId()).get());
        addressRepository.save(address);
        return new ApiResponse("Successfully edited",true);
    }

    public ApiResponse deletedAddress(Integer id){
        Optional<Address> byId = addressRepository.findById(id);
        if (byId.isPresent()){
            addressRepository.deleteById(id);
            return new ApiResponse("Successfully deleted",true);
        }return new ApiResponse("Address not found",false);
    }
}
