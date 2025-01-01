package com.Alvolante.Backend.Repository;


import com.Alvolante.Backend.Entity.ExtraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ExtraRepository extends JpaRepository<ExtraEntity, Long> {
    @Query("SELECT e " +
            "FROM ExtraEntity e " +
            "WHERE e.idExtra = id")
    ExtraEntity findExtraById(Long id);
}