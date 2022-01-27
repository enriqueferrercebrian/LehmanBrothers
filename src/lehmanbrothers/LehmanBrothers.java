package lehmanbrothers;

import java.util.Scanner;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author Enrique
 */
public class LehmanBrothers {

    public static void main(String[] args) {

        Persona titular = crearUsuario();
        CuentaBancaria nuevaCuenta = new CuentaBancaria(pinSeguridad(), seleccionPais(), titular.getNombre());

        while (seguir) {
            try {

                menu();

                switch (respuestaUsuario) {

                    case 1:
                        if (comprobarPin(nuevaCuenta)) {

                            muestraInfoCuenta(nuevaCuenta, titular);
                        } else {
                            System.out.println("Losiento, El pin no es correcto.");
                        }
                        break;
                    case 2:

                        hacerIngreso(nuevaCuenta);
                        break;

                    case 3:

                        if (comprobarPin(nuevaCuenta)) {

                            hacerRetirada(nuevaCuenta);

                        } else {
                            System.out.println("Losiento, El pin no es correcto.");
                        }

                        break;
                    case 4:

                        if (comprobarPin(nuevaCuenta)) {

                            registrarAutorizado(nuevaCuenta , autorizadoDNI);

                        } else {
                            System.out.println("Losiento, El pin no es correcto.");
                        }

                        break;

                    case 5:

                        if (comprobarPin(nuevaCuenta)) {

                            eliminarAutorizado(nuevaCuenta);

                        } else {
                            System.out.println("Losiento, El pin no es correcto.");
                        }

                        break;

                    case 6:
                        if (comprobarPin(nuevaCuenta)) {
                            menuHistorialMovimientos();
                            switch (respuestaUsuario) {

                                case 1:
                                    historialMovimientos(nuevaCuenta);
                                    break;

                                case 2:
                                    historialIngresos(nuevaCuenta);
                                    break;

                                case 3:
                                    historialRetiradas(nuevaCuenta);
                                    break;

                                case 4:
                                    break;

                            }
                        } else {
                            System.out.println("Losiento, El pin no es correcto.");
                        }

                        break;

                    case 0:
                        System.out.println("Gracias por confiar en nosotros");
                        seguir = false;
                        break;
                }
            } catch (Exception e) {
                System.out.println("\n-----------------------\nPorfavor, Solo digitos.\n-----------------------");
            }

        }

    }

    //Metodos
    public static String seleccionPais() {

        while (paisCorrecto) {

            tmpPais = generarIBAN(pais());
            paisCorrecto = false;

        }
        return tmpPais;

    }

    public static Persona crearUsuario() {
        System.out.println("Introduce el nombre del titular");
        nomTitular = /*sc.nextLine()*/ "Enrique";
        System.out.println("Introduce el DNI");
        DNI = /*sc.nextLine()*/ "74015881H";

        Persona titular = new Persona(comprobarDni(DNI), nomTitular);

        return titular;
    }

    public static String pinSeguridad() {

        do {

            System.out.println("Introduce tu PIN de 4 cifras");
            pin = sc.nextLine() /*"1919"*/;

            if (pin.matches("[0-9]*") && pin.length() == 4) {

                System.out.println("Porfavor, vuelva a Introducir el PIN de 4 cifras");
                pin2 = sc.nextLine() /*"1919"*/;

                if ((pin2.matches("[0-9]*") && pin2.length() == 4)) {
                    if (!pin2.contentEquals(pin)) {
                        System.out.println("porfavor, que sean iguales, repita:");
                    }
                } else {
                    System.out.println("Porfavor, Indique un Pin de 4 digitos numericos.\n------------------------------------------------");
                    pin2 = "00000";
                }

            } else {
                System.out.println("Porfavor, Indique un Pin de 4 digitos numericos.\n------------------------------------------------");

            }

        } while (!pin2.contentEquals(pin));

        System.out.println("pin guardado correctamente.");

        return pin;

    }

    public static boolean comprobarPin(CuentaBancaria nuevaCuenta) {

        for (int i = 0; i < 3; ++i) {
            System.out.println("Introduce tu PIN de 4 cifras");
            pin = sc.nextLine();

            if (pin.matches("[0-9]*") && pin.length() == 4) {

                if (pin.equalsIgnoreCase(nuevaCuenta.getPin())) {
                    return true;
                }
            } else {
                System.out.println("Porfavor, Indique un Pin de 4 digitos numericos.\n------------------------------------------------");
                System.out.println("Intento: " + (i + 1) + " de 3. \n");
            }
        }
        return false;
    }

    public static String comprobarDni(String DNI) {

        while (!checkDNI) {

            numDNI = Integer.parseInt(DNI.substring(0, 8));

            resto = numDNI % 23;

            letraDNI = asignacionLetra[resto];

            if (DNI.substring(8).equalsIgnoreCase(letraDNI)) {

                checkDNI = true;
            } else {
                System.out.println("novaaa");
            }

        }
        return DNI;

    }
//-------------------------------------------------------------------------------------------------------
    // Muestreo de Paises.

    public static int pais() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. España.");
        System.out.println("2. Andorra.");
        System.out.println("3. Suiza.");
        return eleccionPais = /*Integer.parseInt(sc.nextLine())*/ (int) (Math.random() * 3 + 1);

    }

//-------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------    
    // Ciudades de los paises
    public static int ciudadesEspanya() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Madrid.");
        System.out.println("2. Barcelona.");
        System.out.println("3. Valencia.");
        return respuestaUsuario = /*Integer.parseInt(sc.nextLine())*/ (int) (Math.random() * 3 + 1);

    }

    public static int ciudadesAndorra() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Andorra La Vella.");
        System.out.println("2. Escaldes-Engordany.");
        System.out.println("3. Encamp.");
        return respuestaUsuario = /*Integer.parseInt(sc.nextLine())*/ (int) (Math.random() * 3 + 1);

    }

    public static int ciudadesSuiza() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Zurich.");
        System.out.println("2. Ginebra.");
        System.out.println("3. Basilea.");
        return respuestaUsuario = /*Integer.parseInt(sc.nextLine())*/ (int) (Math.random() * 3 + 1);

    }
//-------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------    
    // oficinas de las ciudades
    // oficinas España

    public static int oficinasMadrid() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Calle Ensanche.");
        System.out.println("2. Puerta del Sol.");
        System.out.println("3. El Retiro.");
        return respuestaUsuario = /*Integer.parseInt(sc.nextLine())*/ (int) (Math.random() * 3 + 1);

    }

    public static int oficinasBarcelona() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Calle Perico.");
        System.out.println("2. Calle Santa Maria.");
        System.out.println("3. Plaza España.");
        return respuestaUsuario = /*Integer.parseInt(sc.nextLine())*/ (int) (Math.random() * 3 + 1);
    }

    public static int oficinasValencia() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Calle Islas Baleares.");
        System.out.println("2. Avenida Francia.");
        System.out.println("3. Calle Xativa.");
        return respuestaUsuario = /*Integer.parseInt(sc.nextLine())*/ (int) (Math.random() * 3 + 1);

    }
    // Oficinas Andorra

    public static int oficinasAndorraLaVella() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Calle Contador.");
        System.out.println("2. Calle Libra.");
        System.out.println("3. Avenida Principal.");
        return respuestaUsuario = /*Integer.parseInt(sc.nextLine())*/ (int) (Math.random() * 3 + 1);

    }

    public static int oficinasEscaldesEngordany() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Calle Libertad.");
        System.out.println("2. Calle Gabriel Miro.");
        System.out.println("3. Plaza Iglesia.");
        return respuestaUsuario = /*Integer.parseInt(sc.nextLine())*/ (int) (Math.random() * 3 + 1);

    }

    public static int oficinasEncamp() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Calle Italia.");
        System.out.println("2. Calle Limbo.");
        System.out.println("3. Calle Dinamarca.");
        return respuestaUsuario = /*Integer.parseInt(sc.nextLine())*/ (int) (Math.random() * 3 + 1);

    }
    // Oficinas Suiza

    public static int oficinasZurich() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Bahnhofstrasse.");
        System.out.println("2. Seefeldquai.");
        System.out.println("3. Niederdorf.");
        return respuestaUsuario = /*Integer.parseInt(sc.nextLine())*/ (int) (Math.random() * 3 + 1);

    }

    public static int oficinasGinebra() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Calle Purgatorio.");
        System.out.println("2. Calle Cruz De Oro.");
        System.out.println("3. Calle Todas Las Almas.");
        return respuestaUsuario = /*Integer.parseInt(sc.nextLine())*/ (int) (Math.random() * 3 + 1);

    }

    public static int oficinasBasilea() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Heuberg.");
        System.out.println("2. Kornhausgasse.");
        System.out.println("3. Missionsstrasse.");
        return respuestaUsuario = /*Integer.parseInt(sc.nextLine())*/ (int) (Math.random() * 3 + 1);

    }
//-------------------------------------------------------------------------------------------------------
    // Muestreo de menu.

    public static int menu() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Muestrane mis datos bancarios.");
        System.out.println("2. Hacer un ingreso.");
        System.out.println("3. Hacer una retirada.");
        System.out.println("4. Nuevo Titular.");
        System.out.println("5. Eliminar Titular.");
        System.out.println("6. Muestrame todos los movimientos.");
        System.out.println("0. Salir.");
        return respuestaUsuario = Integer.parseInt(sc.nextLine());

    }
//menu historialMovimientos

    public static int menuHistorialMovimientos() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Muestrame todos los movimientos.");
        System.out.println("2. Muestrame todos los ingreso.");
        System.out.println("3. Muestrame todas las retiradas.");
        System.out.println("4. Atras.");
        return respuestaUsuario = Integer.parseInt(sc.nextLine());
    }
// Opcion 1 - muestra info de la cuenta.

    public static void muestraInfoCuenta(CuentaBancaria nuevaCuenta, Persona Titular) {

        infoCuenta = "\n--------------------------------------------------------" + "\n" + nuevaCuenta.getIban() + " " + nuevaCuenta.getNomTitular()
                + " " + "| Saldo: " + nuevaCuenta.formatoEuros(nuevaCuenta.getSaldo()) + "\n" + "--------------------------------------------------------";

        if (nuevaCuenta.getAutorizados().size() >= 1) {

            infoCuenta = infoCuenta + "\n" + "Personas autorizadas: ";

            for (Persona tmp : nuevaCuenta.getAutorizados()) {

                infoCuenta = infoCuenta + "\n-" + tmp;

            }
        }
        System.out.println(infoCuenta);

    }
// Opcion 2  - Hacer un ingreso.

    public static void hacerIngreso(CuentaBancaria nuevaCuenta) {
        System.out.println("Cantidad ingresada: ");
        cantidad = Double.parseDouble(sc.nextLine());

        System.out.println("Remitente: ");
        remitente = sc.nextLine();

        System.out.println("Concepto: ");
        concepto = sc.nextLine();

        tipoMovimiento = "Ingreso";

        switch (nuevaCuenta.ingresar(cantidad, remitente, concepto, tipoMovimiento)) {
            case -1:
                System.out.println("\n Introduzca un importe igual o superior a 0 \n");
                System.out.println("\n *Ingreso cancelado* \n");
                break;
            case 0:
                System.out.println("*Ingreso realizado con éxito*" + "\n");
                System.out.println("Se han ingresado: " + nuevaCuenta.formatoEuros(cantidad) + "\n");
                System.out.println("Saldo: " + nuevaCuenta.formatoEuros(nuevaCuenta.getSaldo()));
                break;
            case 1:
                System.out.println("\nAviso, notificacion a hacienda \n");
                System.out.println("*Ingreso realizado con éxito*" + "\n");
                System.out.println("Se han ingresado: " + nuevaCuenta.formatoEuros(cantidad) + "\n");
                System.out.println("Saldo: " + nuevaCuenta.formatoEuros(nuevaCuenta.getSaldo()));
                break;

        }

    }
// Opcion 3  - Hacer una Retirada.

    public static void hacerRetirada(CuentaBancaria nuevaCuenta) {
        System.out.println("Cantidad retirada: ");
        cantidad = Double.parseDouble(sc.nextLine());

        System.out.println("Remitente: ");
        remitente = sc.nextLine();

        System.out.println("Concepto: ");
        concepto = sc.nextLine();

        tipoMovimiento = "Retirada";

        if (cantidad >= 0) {
            if ((nuevaCuenta.getSaldo() - cantidad) < SALDO_MINIMO) {
                System.out.println("\n No se permite tener un saldo por debajo de -50\n");
                System.out.println("*Retirada cancelada.*\n");

            } else {
                if (nuevaCuenta.getSaldo() - cantidad < 0) {
                    System.out.println(AVISO_NEGATIVO);
                    System.out.println("Se han ingresado: " + nuevaCuenta.formatoEuros(cantidad) + "\n");
                    System.out.println("Saldo: " + nuevaCuenta.formatoEuros(nuevaCuenta.getSaldo()));
                }
                if (cantidad >= MOVIMIENTO_GRANDE) {
                    System.out.println(AVISO_HACIENDA);
                }

                nuevaCuenta.retirada(cantidad, remitente, concepto, tipoMovimiento);
                System.out.println("*Retirada realizada con éxito*\n");
                System.out.println("Se han retirado: " + nuevaCuenta.formatoEuros(cantidad) + "\n");
                System.out.println("Saldo: " + nuevaCuenta.formatoEuros(nuevaCuenta.getSaldo()));
            }
        } else {
            System.out.println("Introduzca un importe igual o superior a 0\n");

        }
//        
//       
    }
// Opcion 4  - Registrar Autorizado.

    public static String registrarAutorizado(CuentaBancaria nuevaCuenta, String autorizadoDNI) {
        System.out.println("Indique el DNI del nuevo autorizado: ");
        autorizadoDNI = sc.nextLine();
        System.out.println("Indique el nombre del nuevo autorizado: ");
        autorizadoNombre = sc.nextLine();
        if (nuevaCuenta.autorizar(comprobarDni(autorizadoDNI), autorizadoNombre)) {
            System.out.println("El nuevo autorizado se ha añadido correctamente");
            System.out.println("En esta cuenta hay " + (nuevaCuenta.getAutorizados().size()) + " personas autorizadas.\n");
        } else {
            System.out.println("Este autorizado ya existe en esta cuenta.");
        }
        return autorizadoDNI;
    }
// Opcion 5  - Eliminar Autorizado.

    public static void eliminarAutorizado(CuentaBancaria nuevaCuenta) {

        if (nuevaCuenta.getAutorizados().size() > 1) {
            System.out.println("Indique el DNI de la persona que quiere eliminar: ");
            String autorizadoDNI = sc.nextLine();

            if (nuevaCuenta.quitarAutorizado(autorizadoDNI)) {
                System.out.println("La persona autorizada se ha eliminado correctamente.");
                System.out.println("En esta cuenta hay " + (nuevaCuenta.getAutorizados().size()) + " personas autorizadas.\n");
            } else {
                System.out.println("No se encuentra ninguna persona autorizada con este DNI");
            }

        } else {
            System.out.println("No puede haber menos de un titular.\n");
        }

    }

// Opcion 5-1 - Mostrar Movimientos.
    public static void historialMovimientos(CuentaBancaria nuevaCuenta) {

        System.out.println("                             Historico de movimientos");
        List<Movimientos> lista = nuevaCuenta.getHistoricoMovimientos();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("---------------------------------------------------------------------------");
            System.out.println(lista.get(i).getTipoMovimiento() + " | " + cantidadString + nuevaCuenta.formatoEuros(lista.get(i).getCantidad()) + " | "
                    + stringRemitente + lista.get(i).getRemitente() + " | " + stringConcepto + lista.get(i).getConcepto() + " ---> "
                    + lista.get(i).getFecha() + " ");

        }
        System.out.println("\n\nSaldo total: " + nuevaCuenta.formatoEuros(nuevaCuenta.getSaldo()));
    }
// Opcion 5-2 - Mostrar Ingresos.

    public static void historialIngresos(CuentaBancaria nuevaCuenta) {

        System.out.println("                             Historico de movimientos");
        List<Movimientos> lista = nuevaCuenta.getHistoricoMovimientos();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getTipoMovimiento().equalsIgnoreCase("Ingreso")) {
                System.out.println("---------------------------------------------------------------------------");
                System.out.println(lista.get(i).getTipoMovimiento() + " | " + cantidadString + nuevaCuenta.formatoEuros(lista.get(i).getCantidad()) + " | "
                        + stringRemitente + lista.get(i).getRemitente() + " | " + stringConcepto + lista.get(i).getConcepto() + " ---> "
                        + lista.get(i).getFecha() + " ");

            }

        }
    }
// Opcion 5-3 - Mostrar Retiradas.

    public static void historialRetiradas(CuentaBancaria nuevaCuenta) {

        System.out.println("                             Historico de movimientos");
        List<Movimientos> lista = nuevaCuenta.getHistoricoMovimientos();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getTipoMovimiento().equalsIgnoreCase("Retirada")) {
                System.out.println("---------------------------------------------------------------------------");
                System.out.println(lista.get(i).getTipoMovimiento() + " | " + cantidadString + nuevaCuenta.formatoEuros(lista.get(i).getCantidad()) + " | "
                        + stringRemitente + lista.get(i).getRemitente() + " | " + stringConcepto + lista.get(i).getConcepto() + " ---> "
                        + lista.get(i).getFecha() + " ");

            }

        }
    }

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

                        switch (LehmanBrothers.oficinasGinebra()) {
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

                        switch (LehmanBrothers.oficinasBasilea()) {
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

        }
        ibanRandom = ((int) Math.floor(Math.random() * (IBANMaximo - IBANMinimo + 1) + IBANMinimo)) + "";
        iban = pais + codPais + codCiudad + codDir + (String.valueOf(codCiudad).charAt(0)) + (String.valueOf(codDir).charAt(0)) + ibanRandom;

        return iban;
    }

    //Variables
    private static String iban = "", nomTitular, remitente = "", concepto = "", stringConcepto = "Concepto: ",
            stringRemitente = "Remitente: ", tipoMovimiento, cantidadString = "Cantidad: ";

    private static double cantidad = 0;
    private static int respuestaUsuario = 8, controlMovimiento, eleccionPais, numDNI, resto = 0;
    private static String DNI, pin = "0", pin2 = "0", pinExp = "\\d{4}", letraDNI = "";
    private static boolean ibancorrecto = false, paisCorrecto = true, checkDNI = false;
    private static Scanner sc = new Scanner(System.in);
    private static String infoCuenta, pais, tmpPais, autorizadoDNI, autorizadoNombre;
    ;
    private static boolean seguir = true, salirPin = false;
    private static Persona titular;
    private static CuentaBancaria nuevaCuenta;
    private static final int SALDO_MINIMO = -50, MOVIMIENTO_GRANDE = 3000;
    private static String AVISO_HACIENDA = "\nAviso, notificacion a hacienda \n", AVISO_NEGATIVO = "\nAviso, Tiene un descubierto en la cuenta \n de";
    private static final long IBANMinimo = 0000000001;
    private static final long IBANMaximo = 9999999999L;
    private static String codPais, codCiudad, codDir, ibanRandom;
    private static String[] asignacionLetra = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};

}
