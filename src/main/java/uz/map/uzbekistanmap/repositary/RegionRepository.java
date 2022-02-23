package uz.map.uzbekistanmap.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.map.uzbekistanmap.model.Region;
@Repository
public interface RegionRepository extends JpaRepository<Region,Integer> {
    boolean existsByName(String name);
}
