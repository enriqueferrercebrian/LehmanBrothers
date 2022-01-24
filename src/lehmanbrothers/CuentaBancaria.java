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
    private static final long IBANMinimo = 0000000001;
    private static final long IBANMaximo = 9999999999L;
//contructor 

//contructor
    public CuentaBancaria(String iban, String nomTitular) {
        this.iban = iban;
        this.nomTitular = nomTitular;
        saldo = 0;
        historicoMovimientos = new ArrayList<>();
    }

    // Getters
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
    public void ingreso(Movimientos nuevoMovimiento) {
        double cantidad = nuevoMovimiento.getCantidad();
        saldo = saldo + cantidad;

        historicoMovimientos.add(nuevoMovimiento);

    }

    public int retirada(Movimientos nuevoMovimiento) {
        Double cantidad = nuevoMovimiento.getCantidad();

        if (saldo - cantidad <= -50) {
            System.out.println("Aviso, Saldo negativo");
            controlMovimiento = -1;

        } else if (cantidad >= 3000) {
            saldo = saldo - cantidad;
            System.out.println("Aviso, notificacion a hacienda");
            historicoMovimientos.add(nuevoMovimiento);
            controlMovimiento = 1;
        } else {
            saldo = saldo - cantidad;
            historicoMovimientos.add(nuevoMovimiento);
            controlMovimiento = 1;

        }
        return controlMovimiento;
    }

    // Seccion de autorizados en la cuenta
    // autorizar
    public boolean autorizar(String autorizadoDNI, String autorizadoNombre) {
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

        for (Persona buscarAutorizado : autorizados) {
            if (buscarAutorizado.getDNI().equalsIgnoreCase(autorizadoDNI)) {
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

    //Generar iban
    public static String generarIBAN(int respuestaUsuario) {

        switch (respuestaUsuario) {

            case 1:
                pais = "ES";
                codPais = "01";
                switch (LehmanBrothers.ciudadesEspanya()) {
                    case 1:
                        codCiudad = "3333";
                        switch (LehmanBrothers.oficinasMadrid()) {
                            case 1:
                                codDir = "3333";

                                break;
                            case 2:
                                codDir = "6666";

                                break;
                            case 3:
                                codDir = "9999";

                                break;
                        }
                        break;
                    case 2:
                        codCiudad = "6666";

                        switch (LehmanBrothers.oficinasBarcelona()) {
                            case 1:
                                codDir = "3333";
                                break;

                            case 2:
                                codDir = "6666";
                                break;

                            case 3:
                                codDir = "9999";
                                break;
                        }
                        break;
                    case 3:
                        codCiudad = "9999";

                        switch (LehmanBrothers.oficinasValencia()) {
                            case 1:
                                codDir = "3333";

                                break;
                            case 2:
                                codDir = "6666";
                                break;
                            case 3:
                                codDir = "9999";
                                break;
                        }
                        break;
                }
                break;

            case 2:
                pais = "AN";
                codPais = "02";

                switch (LehmanBrothers.ciudadesAndorra()) {
                    case 1:
                        codCiudad = "3333";

                        switch (LehmanBrothers.oficinasAndorraLaVella()) {
                            case 1:
                                codDir = "3333";
                                break;
                            case 2:
                                codDir = "6666";
                                break;
                            case 3:
                                codDir = "9999";
                                break;
                        }
                        break;
                    case 2:
                        codCiudad = "6666";

                        switch (LehmanBrothers.oficinasEscaldesEngordany()) {
                            case 1:
                                codDir = "3333";
                                break;

                            case 2:
                                codDir = "6666";
                                break;

                            case 3:
                                codDir = "9999";
                                break;
                        }
                        break;
                    case 3:
                        codCiudad = "9999";

                        switch (LehmanBrothers.oficinasEncamp()) {
                            case 1:
                                codDir = "3333";
                                break;
                            case 2:
                                codDir = "6666";
                                break;
                            case 3:
                                codDir = "9999";
                                break;
                        }
                        break;
                }
                break;

            case 3:
                pais = "SU";
                codPais = "03";

                switch (LehmanBrothers.ciudadesSuiza()) {
                    case 1:
                        codCiudad = "3333";

                        switch (LehmanBrothers.oficinasZurich()) {
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                        }
                        break;
                    case 2:
                        codCiudad = "6666";

                        switch (LehmanBrothers.oficinasGinebra()) {
                            case 1:
                                break;

                            case 2:
                                break;

                            case 3:
                                break;
                        }
                        break;
                    case 3:
                        codCiudad = "9999";

                        switch (LehmanBrothers.oficinasBasilea()) {
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                        }
                        break;
                }
                break;

        }
        ibanRandom = ((int) Math.floor(Math.random() * (IBANMaximo - IBANMinimo + 1) + IBANMinimo)) + "";
        iban = pais + codPais + codCiudad + codDir + (String.valueOf(codCiudad).charAt(0)) + (String.valueOf(codDir).charAt(0)) + ibanRandom;
        if (iban.length() == 24 && iban.substring(0, 2).equalsIgnoreCase("ES")) {
        } else {
            System.out.println("Formato de IBAN no sorportado.");
        }

        return iban;
    }

}
