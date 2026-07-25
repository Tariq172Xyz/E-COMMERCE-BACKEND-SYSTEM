package com.ProductSystem.Repository;

import com.ProductSystem.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {

    @Query(value = "SELECT * FROM addresses WHERE user_id =:userId",
           nativeQuery = true)
    Optional<Address>findByUserId(@Param("user_id") Long userId);
}
