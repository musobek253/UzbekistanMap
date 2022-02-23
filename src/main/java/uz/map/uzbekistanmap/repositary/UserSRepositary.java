package uz.map.uzbekistanmap.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.map.uzbekistanmap.model.UserS;
@Repository
public interface UserSRepositary extends JpaRepository<UserS,Integer> {

}
