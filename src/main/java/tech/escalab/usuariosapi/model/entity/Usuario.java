package tech.escalab.usuariosapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.time.LocalDateTime;
import java.util.Objects;
@Entity
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int usuarioId;
    @Column(nullable = false)
    private String correo;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String clave;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Telefono> telefonos;
    private LocalDateTime  creado;
    private LocalDateTime modificado;
    private int isActive;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return isActive == usuario.isActive && Objects.equals(nombre, usuario.nombre) && Objects.equals(correo, usuario.correo) && Objects.equals(clave, usuario.clave) && Objects.equals(telefonos, usuario.telefonos) && Objects.equals(creado, usuario.creado) && Objects.equals(modificado, usuario.modificado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, correo, clave, telefonos, creado, modificado, isActive);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Usuario{");
        sb.append("usuarioId=").append(usuarioId);
        sb.append(", correo='").append(correo).append('\'');
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", clave='").append(clave).append('\'');
        sb.append(", telefonos=").append(telefonos);
        sb.append(", creado=").append(creado);
        sb.append(", modificado=").append(modificado);
        sb.append(", isActive=").append(isActive);
        sb.append('}');
        return sb.toString();
    }
}
