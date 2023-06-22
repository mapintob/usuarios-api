package tech.escalab.usuariosapi.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
@Entity
@Table(name = "telefonos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Telefono {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int telefonoId;
    @Min(value = 1,message = "el numero de telefono es obligatorio")
    @Column(nullable = false)
    private int numero;
    @Column(nullable = false)
    private int codigoCiudad;
    @Column(nullable = false)
    private int codigoPais;

    @Min(value = 1, message = "el numero de telefono es obligatorio")
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    @Min(value = 1, message = "el codigo de Ciudad de telefono es obligatorio")
    public int getCodigoCiudad() {
        return codigoCiudad;
    }
    public void setCodigoCiudad(int codigoCiudad) {
        this.codigoCiudad = codigoCiudad;
    }
    @Min(value = 1, message = "el codigo de Pais de telefono es obligatorio")
    public int getCodigoPais() {
        return codigoPais;
    }
    public void setCodigoPais(int codigoPais) {
        this.codigoPais = codigoPais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telefono telefono = (Telefono) o;
        return numero == telefono.numero && codigoCiudad == telefono.codigoCiudad && codigoPais == telefono.codigoPais;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, codigoCiudad, codigoPais);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Telefono{");
        sb.append("telefonoId=").append(telefonoId);
        sb.append(", numero=").append(numero);
        sb.append(", codigoCiudad=").append(codigoCiudad);
        sb.append(", codigoPais=").append(codigoPais);
        sb.append('}');
        return sb.toString();
    }
}
