package exceptions;

public class CargaProductoException extends RuntimeException{

    public String getMessage(){
        return "Error en la carga del producto";
    }

}
