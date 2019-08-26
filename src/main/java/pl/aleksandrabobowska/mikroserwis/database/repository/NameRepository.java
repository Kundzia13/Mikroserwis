package pl.aleksandrabobowska.mikroserwis.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.aleksandrabobowska.mikroserwis.database.entity.NameEntity;

import java.util.List;

@Repository
public interface NameRepository extends JpaRepository<NameEntity, Long> {
    /**
     * @return all added names
     */
    List<NameEntity> findAll();
}
