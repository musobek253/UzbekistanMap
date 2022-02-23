package uz.map.uzbekistanmap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.map.uzbekistanmap.dto.AddressDTO;
import uz.map.uzbekistanmap.dto.ApiResponse;
import uz.map.uzbekistanmap.model.Address;
import uz.map.uzbekistanmap.repositary.AddressRepository;
import uz.map.uzbekistanmap.service.AddressService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressRepository addressRepository;
    private final AddressService addressService;
    @Autowired
    public AddressController(AddressRepository addressRepository, AddressService addressService) {
        this.addressRepository = addressRepository;
        this.addressService = addressService;
    }
    @PostMapping("/add")
    public ApiResponse addAddress(@RequestBody AddressDTO addressDTO){
        return addressService.addAddress(addressDTO);
    }

    @PutMapping("/{id}")
    public ApiResponse editedAddress(@PathVariable Integer id,@RequestBody AddressDTO addressDTO){
        return addressService.editAddress(id, addressDTO);
    }
    @GetMapping("/all")
    public List<Address> getAddress(){
        return addressRepository.findAll();

    }
    @GetMapping("/{id}")
    public Address getById(@PathVariable Integer id){
        Optional<Address> byId = addressRepository.findById(id);
        return byId.orElseGet(Address::new);
    }

    //dstrictId buyicha Addresslarni qaytarish

    @GetMapping("/districtId/{districtId}")
    public List<Address> getByDistrictId(@PathVariable Integer districtId){
        List<Address> addressByDistrictIdNative = addressRepository.getAddressByDistrictIdNative(districtId);
        return addressByDistrictIdNative;
    }
}
