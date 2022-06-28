package me.parzibyte.sistemaventasspringboot;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CierreCajaRepository extends CrudRepository <CierreCaja,Integer>{
    @Query("from CierreCaja c where c.username =:userName")
    CierreCaja findLastByUsername(@Param("userName")String username);
}
