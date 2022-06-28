package me.parzibyte.sistemaventasspringboot;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class CierreCaja {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "Debes especificar el nombre del usuario que cerro la caja")
    @Size(min = 1, max = 50, message = "El nombre debe medir entre 1 y 50")
    private String username;

    @NotNull(message = "Debes especificar el monto total de cierre de caja")
    @Min(value = 0, message = "El precio mínimo es 0")
    private Float monto;

    private String fechaYHora;

    public CierreCaja() {
    }

    public CierreCaja(Integer id, String username, Float monto, String fechaYHora) {
        this.id = id;
        this.username = username;
        this.monto = monto;
        this.fechaYHora = fechaYHora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public String getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(String fechaYHora) {
        this.fechaYHora = fechaYHora;
    }
}
