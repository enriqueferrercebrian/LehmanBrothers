package lehmanbrothers;

import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Enrique
 */
public class CuentaBancaria {

    private static String iban, nomTitular, numeroDeAutorizados, pais, codPais, codCiudad, codDir, ibanRandom;
    private double saldo, euros;
    private Persona titular;
    private DecimalFormat pasarAEuros = new DecimalFormat("###,###.##€");
    private List<Movimientos> historicoMovimientos;
    private static Set<Persona> autorizados = new HashSet<>();
    private static int controlMovimiento;
    private static String pin;
    private static boolean buenPais = true;
    private static final long IBANMinimo = 0000000001;
    private static final long IBANMaximo = 9999999999L;


//contructor
    public CuentaBancaria(String pin, String iban, String nomTitular) {
        this.pin = pin;
        this.iban = iban;
        this.nomTitular = nomTitular;
        saldo = 0;

        historicoMovimientos = new ArrayList<>(); 
    }

    // Getters
    public String getPin() {
        return pin;
    }

    public String getIban() {
        return iban;
    }

    public String getNomTitular() {
        return nomTitular;
    }

    public double getSaldo() {
        return saldo;
    }

    public String formatoEuros(double euros) {
        return pasarAEuros.format(euros);
    }

    public List<Movimientos> getHistoricoMovimientos() {
        return historicoMovimientos;
    }

    public Set<Persona> getAutorizados() {
        return autorizados;
    }

    //Funciones
    public int ingresar(double cantidad, String remitente, String concepto, String tipoMovimiento) { // realizamos una comprobacion sobre la cantidad obtenida del usuario para realizar el ingreso o no segun los requerimientos que tenemos abajo.

        if ((cantidad * 11) > 0) {

            if ((cantidad * 11) >= 3000) {
//
                controlMovimiento = 1;

            } else {
                controlMovimiento = 0;

            }
            saldo += cantidad;
        } else {
            controlMovimiento = -1;

        }

        Movimientos nuevoIngreso = new Movimientos(tipoMovimiento, cantidad, remitente, concepto); // metodo encargado de registrar un nuevo movimiento de ingreso y devolviendo un digito de control para informar al usuario.
        historicoMovimientos.add(nuevoIngreso);
        return controlMovimiento;
    }

    public int retirada(double cantidad, String remitente, String concepto, String tipoMovimiento) {// en este metodo restamos la cantidad del usuario al saldo y registramos el movimiento.

        saldo = getSaldo() - cantidad;
        controlMovimiento = -1;

        Movimientos nuevaRetirada = new Movimientos(tipoMovimiento, cantidad, remitente, concepto);
        historicoMovimientos.add(nuevaRetirada);
        return (int) saldo;
    }

    // Seccion de autorizados en la cuenta
    // autorizar
    public boolean autorizar(String autorizadoDNI, String autorizadoNombre) {// generamos un objet o de tipo persona comprobando el DNI y el nombre y añiedonlo a este, el cual añadimos a la lista de autorizados.
        Persona autorizado = new Persona(autorizadoDNI, autorizadoNombre);
        boolean autorizadoResgistrado = false;
        if (autorizados.toString().contains(autorizadoDNI)) {
            autorizadoResgistrado = false;
        } else {
            autorizadoResgistrado = autorizados.add(autorizado);

        }
        return autorizadoResgistrado;

    }

    // quitar Autorizados
    public boolean quitarAutorizado(String autorizadoDNI) {
        boolean autorizadoEliminado = false;

        for (Persona buscarAutorizado : autorizados) { // hacemos un recorrido por el List de autorizados
            if (buscarAutorizado.getDNI().equalsIgnoreCase(autorizadoDNI)) {// si lo encontramos lo eliminamos y devolvemos un true el cual nos indicara que todo ha ido bien, si no un false para que nos informe de que algo ha ido mal.
                autorizadoEliminado = autorizados.remove(buscarAutorizado);
                break;
            }
        }

        return autorizadoEliminado;
    }
// Ver autorizados

    public String verAutorizados() {
        //Se mostrara el toString() de cada objeto Persona que hay en el atributo autorizados
        if (autorizados.size() > 1) {
            return numeroDeAutorizados = "\nPersonas autorizadas: " + autorizados;
        }
        return "";

    }



}
