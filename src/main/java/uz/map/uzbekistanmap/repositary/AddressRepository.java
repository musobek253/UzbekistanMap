package uz.map.uzbekistanmap.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.map.uzbekistanmap.model.Address;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {

    boolean existsByStreetAndHouseNumberAndDistrictId(String street, Integer houseNumber, Integer district_id);


    @Query(value = "select * from " +
            "address join district d on address.district_id = " +
            "d.id where d.id =:districtId",nativeQuery = true)
    List<Address>  getAddressByDistrictIdNative(Integer districtId);

}
