package uz.map.uzbekistanmap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.map.uzbekistanmap.dto.ApiResponse;
import uz.map.uzbekistanmap.dto.UserDTO;
import uz.map.uzbekistanmap.model.Address;
import uz.map.uzbekistanmap.model.UserS;
import uz.map.uzbekistanmap.repositary.AddressRepository;
import uz.map.uzbekistanmap.repositary.CarRepositary;
import uz.map.uzbekistanmap.repositary.DistrictRepository;
import uz.map.uzbekistanmap.repositary.UserSRepositary;

@Service
public class UserService {

    private final UserSRepositary userSRepositary;
    private final AddressRepository addressRepository;
    private final CarRepositary carRepositary;
    private final DistrictRepository districtRepository;
    @Autowired
    public UserService(UserSRepositary userSRepositary, AddressRepository addressRepository, CarRepositary carRepositary, DistrictRepository districtRepository) {
        this.userSRepositary = userSRepositary;
        this.addressRepository = addressRepository;
        this.carRepositary = carRepositary;
        this.districtRepository = districtRepository;
    }

    public ApiResponse addUsers(UserDTO userDTO){
        Address address = new Address();

        if (!districtRepository.findById(userDTO.getDistrictId()).isPresent())
            return new ApiResponse("not found discrit",false);
        if (!carRepositary.existsByIdIn(userDTO.getCarsId()))
            return new ApiResponse("cars not found",false);
        address.setStreet(userDTO.getStreet());
        address.setHouseNumber(userDTO.getHouseNumber());
        address.setDistrict(districtRepository.findById(userDTO.getDistrictId()).get());
        Address address1 = addressRepository.save(address);
        UserS userS = new UserS();
        userS.setAddress(address1);
        userS.setCars(carRepositary.findAllByIdIn(userDTO.getCarsId()));
        userS.setName(userDTO.getName());
        userSRepositary.save(userS);
        return new ApiResponse("Successfully added",true);
    }
}
