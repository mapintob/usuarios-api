package tech.escalab.usuariosapi.commons.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorHttp {
    private List<DetalleError> errores;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorHttp errorHttp = (ErrorHttp) o;
        return Objects.equals(errores, errorHttp.errores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errores);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("").append(errores);
        return sb.toString();
    }
}
