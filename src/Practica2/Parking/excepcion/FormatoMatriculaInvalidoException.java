package Practica2.Parking.excepcion;

public class FormatoMatriculaInvalidoException extends IllegalArgumentException {

    public FormatoMatriculaInvalidoException() {
        super("Formato de matricula no valido. Usa uno de estos formatos: 0000-XXX, 0000XXX o 0000 XXX.");
    }
}
