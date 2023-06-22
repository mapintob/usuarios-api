package tech.escalab.usuariosapi.commons.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorSQL {
    private int status;
    private String mensaje;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorSQL errorSQL = (ErrorSQL) o;
        return status == errorSQL.status && Objects.equals(mensaje, errorSQL.mensaje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, mensaje);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("status: ").append(status);
        sb.append("mensaje: '").append(mensaje).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
