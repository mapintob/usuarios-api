package tech.escalab.usuariosapi.commons.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleError {
    private int status;
    private String mensaje;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleError that = (DetalleError) o;
        return status == that.status && Objects.equals(mensaje, that.mensaje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, mensaje);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("status:").append(status);
        sb.append("mensaje:'").append(mensaje).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static List<DetalleError> mapearError(int status, List<FieldError> fieldErrors){
        List<DetalleError> resultado = new ArrayList<>();
        for (FieldError error: fieldErrors){
            DetalleError detalle = new DetalleError();
            detalle.setStatus(status);
            detalle.setMensaje(error.getDefaultMessage());
            resultado.add(detalle);
        }

        return  resultado;

    }
    public static String mapearErrorSQL(String mensaje){
        String resultado = "";

        String palabra = "correo";

        boolean error = mensaje.contains(palabra);

        if(error){
            resultado = "El correo ya esta registrado";

        }else if (mensaje.contains("telefonos")) {
            resultado = "El numero de telefono debe ser unico";
            }else{
                resultado = "otro error de integridad de SQL ";
        }

        return resultado;

    }

}
