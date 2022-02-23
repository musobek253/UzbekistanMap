package uz.map.uzbekistanmap.repositary;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.map.uzbekistanmap.model.Car;

import java.util.List;

@Repository public interface CarRepositary extends JpaRepository<Car,Integer> {
    boolean existsByStateNumber(String stateNumber);

    List<Car> getCarByRegionId(Integer regionId);

    boolean existsByIdIn(List<Integer> ids);

    List<Car> findAllByIdIn(List<Integer> ids);


    @Query(value = "select *from car join users_cars uc on car.id = uc.cars_id\n" +
            "where  uc.users_id =:userId",nativeQuery = true)
    List<Car> getCarByUserNative(Integer userId);
//    List<Car> findAllByIdInAndRegionId();
}
