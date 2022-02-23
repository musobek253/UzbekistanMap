package uz.map.uzbekistanmap.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.map.uzbekistanmap.model.District;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District,Integer> {
    boolean existsByNameAndRegionId(String name,Integer regionId);

    @Query(value = "select * from district join region r on r.id = district.region_id where r.id=:regionId", nativeQuery = true)
    List<District> getDistrictByRegionIdNative(Integer regionId);

}
