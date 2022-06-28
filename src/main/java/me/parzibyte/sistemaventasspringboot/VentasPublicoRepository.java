package me.parzibyte.sistemaventasspringboot;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
public interface VentasPublicoRepository extends CrudRepository<Venta, Integer> {
    @Query("from Venta v where v.fechaYHora >=:today and v.username =:username")
    public Iterable<Venta> mostrarVentasDeHoy(@Param("today") String today, @Param("username") String username);
}
