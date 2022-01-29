package lehmanbrothers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Enrique
 */
public class Movimientos {

    //Atributos
    private double cantidad;
    private String fecha;
    private String concepto, remitente, tipoMovimiento;
    DateTimeFormatter formatear = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");// formateo de la hora para proporcionarla como se desea.
   

    String fechaFormateada;

    //constructor
    public Movimientos(String tipoMovimiento, double cantidad, String remitente, String concepto) {
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
        this.remitente = remitente;
        this.concepto = concepto;
        this.fecha = LocalDateTime.now().format(formatear);

    }

    //Getters
    public double getCantidad() {
        return cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public String getConcepto() {
        return concepto;
    }

    public String getRemitente() {
        return remitente;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

}
