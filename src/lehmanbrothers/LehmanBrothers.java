package lehmanbrothers;

import java.util.Scanner;
import java.util.List;

/**
 *
 * @author Enrique
 */
public class LehmanBrothers {

    public static void main(String[] args) {

        pais();
        System.out.println("Introduce el nombre del titular");
        nomTitular = /*sc.nextLine()*/ "Enriqueeeee";
        System.out.println("Introduce el DNI");
        DNI = /*sc.nextLine()*/ "74015881H";
        Persona titular = new Persona(DNI, nomTitular);
        CuentaBancaria nuevaCuenta = new CuentaBancaria(CuentaBancaria.generarIBAN(respuestaUsuario), nomTitular);
        seguir = true;
        while (seguir == true) {
            respuestaUsuario = 8;
            menu();

            switch (respuestaUsuario) {

                case 1:
                    // muestraInfoCuenta(nuevaCuenta, titular);
                    muestraInfoCuenta(nuevaCuenta, titular);
                    break;
                case 2:

                    hacerIngreso(nuevaCuenta);
                    break;

                case 3:

                    hacerRetirada(nuevaCuenta);
                    break;
                case 4:

                    registrarAutorizado(nuevaCuenta);
                    break;

                case 5:
                    eliminarAutorizado(nuevaCuenta);
                    break;

                case 6:

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

                    break;

                case 0:
                    System.out.println("Gracias por confiar en nosotros");
                    seguir = false;
            }

        }

    }

    //Metodos
//-------------------------------------------------------------------------------------------------------
    // Muestreo de Paises.
    public static void pais() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. España.");
        System.out.println("2. Andorra.");
        System.out.println("3. Suiza.");
        respuestaUsuario = Integer.parseInt(sc.nextLine());
        
 
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
        return respuestaUsuario = Integer.parseInt(sc.nextLine());

    }

    public static int ciudadesAndorra() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Andorra La Vella.");
        System.out.println("2. Escaldes-Engordany.");
        System.out.println("3. Encamp.");
        return respuestaUsuario = Integer.parseInt(sc.nextLine());

    }

    public static int ciudadesSuiza() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Zurich.");
        System.out.println("2. Ginebra.");
        System.out.println("3. Basilea.");
        return respuestaUsuario = Integer.parseInt(sc.nextLine());

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
        return respuestaUsuario = Integer.parseInt(sc.nextLine());

    }

    public static int oficinasBarcelona() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Calle Perico.");
        System.out.println("2. Calle Santa Maria.");
        System.out.println("3. Plaza España.");
        return respuestaUsuario = Integer.parseInt(sc.nextLine());

    }

    public static int oficinasValencia() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Calle Islas Baleares.");
        System.out.println("2. Avenida Francia.");
        System.out.println("3. Calle Xativa.");
        return respuestaUsuario = Integer.parseInt(sc.nextLine());

    }
    // Oficinas Andorra

    public static int oficinasAndorraLaVella() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Calle Contador.");
        System.out.println("2. Calle Libra.");
        System.out.println("3. Avenida Principal.");
        return respuestaUsuario = Integer.parseInt(sc.nextLine());

    }

    public static int oficinasEscaldesEngordany() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Calle Libertad.");
        System.out.println("2. Calle Gabriel Miro.");
        System.out.println("3. Plaza Iglesia.");
        return respuestaUsuario = Integer.parseInt(sc.nextLine());

    }

    public static int oficinasEncamp() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Calle Italia.");
        System.out.println("2. Calle Limbo.");
        System.out.println("3. Calle Dinamarca.");
        return respuestaUsuario = Integer.parseInt(sc.nextLine());

    }
    // Oficinas Suiza

    public static int oficinasZurich() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Bahnhofstrasse.");
        System.out.println("2. Seefeldquai.");
        System.out.println("3. Niederdorf.");
        return respuestaUsuario = Integer.parseInt(sc.nextLine());

    }

    public static int oficinasGinebra() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Calle Purgatorio.");
        System.out.println("2. Calle Cruz De Oro.");
        System.out.println("3. Calle Todas Las Almas.");
        return respuestaUsuario = Integer.parseInt(sc.nextLine());

    }

    public static int oficinasBasilea() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Heuberg.");
        System.out.println("2. Kornhausgasse.");
        System.out.println("3. Missionsstrasse.");
        return respuestaUsuario = Integer.parseInt(sc.nextLine());

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

        infoCuenta = nuevaCuenta.getIban() + " " + nuevaCuenta.getNomTitular()
                + " " + " " + nuevaCuenta.formatoEuros(nuevaCuenta.getSaldo()) + " "
                + Titular.getDNI() + " \n\n";

        if (nuevaCuenta.getAutorizados().size() > 1) {

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

        Movimientos nuevoIngreso = new Movimientos(tipoMovimiento, cantidad, remitente, concepto);

        nuevaCuenta.ingreso(nuevoIngreso);
        String resultadoDelIngreso = "*Ingreso realizado con éxito*" + "\n";
        if (cantidad >= 3000) {
            resultadoDelIngreso = resultadoDelIngreso + "\n" + "Aviso, notificacion a hacienda";
        } else if (cantidad <= 0) {
            resultadoDelIngreso = "Introduzca un importe igual o superior a 0\n";
        }
        System.out.println("");
        System.out.println(resultadoDelIngreso);
        if (cantidad > 0) {
            System.out.println("Se han ingresado: " + nuevaCuenta.formatoEuros(cantidad) + "\n");
        }
        System.out.println("Saldo: " + nuevaCuenta.formatoEuros(nuevaCuenta.getSaldo()));
        System.out.println("");

    }
// Opcion 3  - Hacer una Retirada.

    public static void hacerRetirada(CuentaBancaria nuevaCuenta) {
        controlMovimiento = 0;

        System.out.println("Cantidad retirada: ");
        cantidad = Double.parseDouble(sc.nextLine());

        System.out.println("Remitente: ");
        remitente = sc.nextLine();

        System.out.println("Concepto: ");
        concepto = sc.nextLine();

        tipoMovimiento = "Retirada";

        Movimientos nuevaRetirada = new Movimientos(tipoMovimiento, cantidad, remitente, concepto);

        if (nuevaCuenta.retirada(nuevaRetirada) == 1) {
            System.out.println("\n" + "*Retirada realizada con éxito*" + "\n");

            System.out.println("Se han retirado: " + nuevaCuenta.formatoEuros(cantidad) + "\n");

            System.out.println("Saldo: " + nuevaCuenta.formatoEuros(nuevaCuenta.getSaldo()));
            System.out.println("");
        }
    }
// Opcion 4  - Registrar Autorizado.

    public static void registrarAutorizado(CuentaBancaria nuevaCuenta) {
        String autorizadoDNI, autorizadoNombre;
        System.out.println("Indique el DNI del nuevo autorizado: ");
        autorizadoDNI = sc.nextLine();
        System.out.println("Indique el nombre del nuevo autorizado: ");
        autorizadoNombre = sc.nextLine();
        if (nuevaCuenta.autorizar(autorizadoDNI, autorizadoNombre)) {
            System.out.println("El nuevo autorizado se ha añadido correctamente");
            System.out.println("En esta cuenta hay " + (nuevaCuenta.getAutorizados().size()) + " personas autorizadas.\n");
        } else {
            System.out.println("Este autorizado ya existe en esta cuenta.");
        }

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

    //Variables
    private static String iban = "", nomTitular, remitente = "", concepto = "", stringConcepto = "Concepto: ",
            stringRemitente = "Remitente: ", tipoMovimiento, cantidadString = "Cantidad: ";

    private static double cantidad = 0;
    private static int respuestaUsuario = 0, controlMovimiento;
    private static String DNI;
    private static boolean ibancorrecto = false;
    private static Scanner sc = new Scanner(System.in);
    private static String infoCuenta, pais;
    private static boolean seguir;
    private static Persona titular;
    private static CuentaBancaria nuevaCuenta;
}