package lehmanbrothers;

import java.util.Scanner;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Enrique
 */
public class LehmanBrothers {

    public static void main(String[] args) {

        Persona titular = crearUsuario(); // Creamos la persona Titular llamando a un metodo llamado (crearUsuario), el cual recoge el nombre y el DNI de la persona (comprobando que es un DNI correcto).
        CuentaBancaria nuevaCuenta = new CuentaBancaria(pinSeguridad(), seleccionPais(), titular.getNombre());
        // creamos una cuenta bancaria nueva creando un pin de seguridad para acceder a algunas partes del codigo, ya que hay algunas partes no accesibles para autorizados,
        //ademas, generamos un IBAN simulando un poco la realidad, segun el pais la ciudad y la sucursal de esta y generando codigos de control para generar un codigo unico y robusto.
        //Por ultimo le proporcionamos el nombre del titular que se asigna a esta cuenta.

        while (seguir) {
            try {
                //llamamos al metodo menu el cual nos muestra las opciones y nos devuelve un int, que lo ultilizaremos en el switch siguiente para elegir la opcion correcta.
                menu();

                switch (respuestaUsuario) {

                    case 1:
                        if (comprobarPin(nuevaCuenta)) { // en casi todas las opciones (menos en la de ingresar) necesitaremos el pin del titular para efectuar cambios o ver informacion, le pediremos que meta un pi
                            //y lo compararemos con el asociado a la cuenta y el titular.

                            muestraInfoCuenta(nuevaCuenta, titular); // creamos un String que muestra en pantalla el IBAN y el titular de la cuenta, ademas del saldo, 
                            // ademas de todo esto, solo cuando hay un o mas autorizados se mostraran.
                        } else {
                            System.out.println("Losiento, El pin no es correcto."); // si el pin no es correcto se lo hacemos saber al usuario y lo llevamos de nuevo al menu.
                        }
                        break;
                    case 2:

                        hacerIngreso(nuevaCuenta);// este es el unico metodo (opcion) el cual no requiere pin, ya que un ingreso puede realizarse por cualquier motico, como nomina extra, bizum.
                        muestraInfoCuenta(nuevaCuenta, titular);

                        break;

                    case 3:

                        if (comprobarPin(nuevaCuenta)) {//comprobarmos que el pin del titular y la cuenta sea correcto para aceder.

                            hacerRetirada(nuevaCuenta);// en este metodo llamamos al metodo haer retirada para retirar dinero, siendo las limitaciones no poder tener menso de 50euros, notificar a hacienda si es mas 3000 euros
                            // y si tenemos un pequeño descubierto, notificarselo al usuario.
                            muestraInfoCuenta(nuevaCuenta, titular);

                        } else {
                            System.out.println("Losiento, El pin no es correcto.");
                        }

                        break;
                    case 4:

                        if (comprobarPin(nuevaCuenta)) {

                            registrarAutorizado(nuevaCuenta, autorizadoDNI);// generamos una nueva persona autorizada comprobando un correcto DNI y el nombre y lo agregamos a una lista de autorizados.
                            muestraInfoCuenta(nuevaCuenta, titular);

                        } else {
                            System.out.println("Losiento, El pin no es correcto.");
                        }

                        break;

                    case 5:

                        if (comprobarPin(nuevaCuenta)) {

                            eliminarAutorizado(nuevaCuenta);// Nos permite eliminar un autorizado si coincide alguno de estos con el DNI introducido, ademas, si intentamos dejar la cuanta a 0 no nos lo permite, ya que debe tener minimo  un titular.
                            muestraInfoCuenta(nuevaCuenta, titular);

                        } else {
                            System.out.println("Losiento, El pin no es correcto.");
                        }

                        break;

                    case 6:
                        if (comprobarPin(nuevaCuenta)) {
                            menuHistorialMovimientos();// aqui he decidido crear un sub menu, como en la vida real, podriamos ver todos los movimientos para exportar a excel o similar.
                            switch (respuestaUsuario) {

                                case 1:
                                    historialMovimientos(nuevaCuenta);
                                    break;

                                case 2:
                                    historialIngresos(nuevaCuenta);// podemos seleccionar solo los movimientos catalogados como Ingresos.
                                    break;

                                case 3:
                                    historialRetiradas(nuevaCuenta);// podemos seleccionar solo los movimientos catalogados como Retiradas.
                                    break;

                                case 4:
                                    break;

                            }
                            muestraInfoCuenta(nuevaCuenta, titular);

                        } else {
                            System.out.println("Losiento, El pin no es correcto.");
                        }

                        break;

                    case 0:
                        System.out.println("Gracias por confiar en nosotros");
                        seguir = false;
                        break;

                    default:
                        System.out.println("Porfavor, seleccione una opcion valida del 0 al 6.");// cuando el usuario no proporciona una opcion valida, le devuelvo al bucle con este System.out
                        break;

                }
            } catch (Exception e) {
                System.out.println("\n-----------------------\nPorfavor, Solo digitos.\n-----------------------"); // otra comprobacion para hacer un control de excepciones el cual no interrumpe la ejecucion del programa.
            }

        }

    }
    //Metodos

    public static String seleccionPais() {  // en este metodo guardamos el pais con el cual va a empezar la generacion del iban.

        while (paisCorrecto) {

            tmpPais = generarIBAN(pais());
            paisCorrecto = false;

        }
        return tmpPais;

    }

    public static Persona crearUsuario() { // metodo el cual se encarga de crear un autorizado, ademas de comprobar que su DNI no esta registrado ya y es correcto en cuanto a formato.

        System.out.println("\n-------------------------------------------------------------\nBienvenido/a a Lehman Brothers el banco mas fiable del mundo.");
        System.out.println("              14 de septiembre de 2008 \n-------------------------------------------------------------\n");
        System.out.println("Introduce el nombre del titular");
        nomTitular = sc.nextLine();
        System.out.println("Introduce el DNI");
        DNI = sc.nextLine();

        Persona titular = new Persona(comprobarDni(DNI), nomTitular);

        return titular;
    }

    public static String pinSeguridad() {// en este metodo generamos un pin de seguridad de 4 digitos que obligatoriamente has de meter 2 veces, para evitar errores de escritura la primera vez.

        do {

            System.out.println("Introduce tu PIN de 4 cifras");
            pin = sc.nextLine();

            if (pin.matches("[0-9]*") && pin.length() == 4) {

                System.out.println("Porfavor, vuelva a Introducir el PIN de 4 cifras");
                pin2 = sc.nextLine();

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

    public static boolean comprobarPin(CuentaBancaria nuevaCuenta) { //  este metodo es utilizado para comprobar que el pin sea correcto cuando accedemos a una opcion que lo requiere
        // ademas, como podemos observar, tenemos 3 intentos para acertar, ya que revasado estos intentos el programa nos dira que no es correcto y nos devolvera al menu principal.

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

    public static String comprobarDni(String DNI) {// con este metodo comprobamos, mediante un substring, que el numero del DNI dividido por 23, genere un resto correcto concordando con la letra de su DNI ( como se realiza en la vida real).

        while (!checkDNI) {
            numDNI = Integer.parseInt(DNI.substring(0, 8));

            resto = numDNI % 23;

            letraDNI = asignacionLetra[resto];

            if (DNI.substring(8).equalsIgnoreCase(letraDNI)) {

                checkDNI = true;
            } else {
                System.out.println("Introduzca un DNI correcto.");
                DNI = sc.nextLine();

            }

        }
        return DNI;

    }
//-------------------------------------------------------------------------------------------------------
    // Muestreo de Paises.

    public static int pais() { //Primer metodo que nos da a elegir la opcion de donde crear la cuenta.
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. España.");
        System.out.println("2. Andorra.");
        System.out.println("3. Suiza.");
        return eleccionPais = Integer.parseInt(sc.nextLine())/* (int) (Math.random() * 3 + 1)*/;

    }

//-------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------    
    // Ciudades de los paises
    public static int ciudadesEspanya() {// elegiremos la ciudad en la que vivimos o queremos abrir la cuenta
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Madrid.");
        System.out.println("2. Barcelona.");
        System.out.println("3. Valencia.");
        return respuestaUsuario = Integer.parseInt(sc.nextLine())/* (int) (Math.random() * 3 + 1)*/;

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
        return respuestaUsuario = Integer.parseInt(sc.nextLine())/* (int) (Math.random() * 3 + 1)*/;

    }
//-------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------    
    // oficinas de las ciudades
    // oficinas España

    public static int oficinasMadrid() { //  elegiremos la oficina a la cual va a pertenecer la cuenta.
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Calle Ensanche.");
        System.out.println("2. Puerta del Sol.");
        System.out.println("3. El Retiro.");
        return respuestaUsuario = Integer.parseInt(sc.nextLine())/* (int) (Math.random() * 3 + 1)*/;

    }

    public static int oficinasBarcelona() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Calle Perico.");
        System.out.println("2. Calle Santa Maria.");
        System.out.println("3. Plaza España.");
        return respuestaUsuario = Integer.parseInt(sc.nextLine()) /*(int) (Math.random() * 3 + 1)*/;
    }

    public static int oficinasValencia() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Calle Islas Baleares.");
        System.out.println("2. Avenida Francia.");
        System.out.println("3. Calle Xativa.");
        return respuestaUsuario = Integer.parseInt(sc.nextLine()) /*(int) (Math.random() * 3 + 1)*/;

    }
    // Oficinas Andorra

    public static int oficinasAndorraLaVella() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Calle Contador.");
        System.out.println("2. Calle Libra.");
        System.out.println("3. Avenida Principal.");
        return respuestaUsuario = Integer.parseInt(sc.nextLine()) /*(int) (Math.random() * 3 + 1)*/;

    }

    public static int oficinasEscaldesEngordany() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Calle Libertad.");
        System.out.println("2. Calle Gabriel Miro.");
        System.out.println("3. Plaza Iglesia.");
        return respuestaUsuario = Integer.parseInt(sc.nextLine()) /*(int) (Math.random() * 3 + 1)*/;

    }

    public static int oficinasEncamp() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Calle Italia.");
        System.out.println("2. Calle Limbo.");
        System.out.println("3. Calle Dinamarca.");
        return respuestaUsuario = Integer.parseInt(sc.nextLine()) /*(int) (Math.random() * 3 + 1)*/;

    }
    // Oficinas Suiza

    public static int oficinasZurich() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Bahnhofstrasse.");
        System.out.println("2. Seefeldquai.");
        System.out.println("3. Niederdorf.");
        return respuestaUsuario = Integer.parseInt(sc.nextLine()) /*(int) (Math.random() * 3 + 1)*/;

    }

    public static int oficinasGinebra() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Calle Purgatorio.");
        System.out.println("2. Calle Cruz De Oro.");
        System.out.println("3. Calle Todas Las Almas.");
        return respuestaUsuario = Integer.parseInt(sc.nextLine()) /*(int) (Math.random() * 3 + 1)*/;

    }

    public static int oficinasBasilea() {
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Heuberg.");
        System.out.println("2. Kornhausgasse.");
        System.out.println("3. Missionsstrasse.");
        return respuestaUsuario = Integer.parseInt(sc.nextLine()) /*(int) (Math.random() * 3 + 1)*/;

    }
//-------------------------------------------------------------------------------------------------------
    // Muestreo de menu.

    public static int menu() { // metodo el cual nos muestra todas las opciones del programa lo recoge como int y lo saca para utilizarlo en el switch.
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

// Opcion 1 - muestra info de la cuenta.
    public static void muestraInfoCuenta(CuentaBancaria nuevaCuenta, Persona Titular) {// en este metodo mostramos toda la informacion actualizada de la cuenta bancaria, como el iban nombre del titular y ademas, si tiene autorizados o no.

        infoCuenta = "\nInformación de la cuenta\n---------------------------------\n" + nuevaCuenta.getIban() + " " + nuevaCuenta.getNomTitular()
                + "\n---------------------------------\n";

        if (nuevaCuenta.getAutorizados().size() >= 1) {

            infoCuenta = infoCuenta + "\n" + "Personas autorizadas:";

            for (Persona tmp : nuevaCuenta.getAutorizados()) {

                infoCuenta = infoCuenta + "\n-" + tmp + "\n";

            }
        }
        System.out.println(infoCuenta + "\n\n Saldo: " + nuevaCuenta.formatoEuros(nuevaCuenta.getSaldo()));

    }
// Opcion 2  - Hacer un ingreso.

    public static void hacerIngreso(CuentaBancaria nuevaCuenta) { // como bien hemos dicho arriba, este metodo es el utilizado para gestionar los ingresos en la cuenta bancaria, sin autentificación de pin.
        System.out.println("Cantidad ingresada: ");
        cantidad = Double.parseDouble(sc.nextLine());

        System.out.println("Remitente: ");
        remitente = sc.nextLine();

        System.out.println("Concepto: ");
        concepto = sc.nextLine();

        tipoMovimiento = "Ingreso";

        switch (nuevaCuenta.ingresar(cantidad, remitente, concepto, tipoMovimiento)) { // retornamos la resolucion de el ingreso en forma de int y proporcionamos la informacion que debe segun en cada caso.
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
                System.out.println("Se han ingresado: " + nuevaCuenta.formatoEuros(cantidad) + "\n"); // formateamos en euros la cantifdad
                System.out.println("Saldo: " + nuevaCuenta.formatoEuros(nuevaCuenta.getSaldo()));
                break;

        }

    }
// Opcion 3  - Hacer una Retirada.

    public static void hacerRetirada(CuentaBancaria nuevaCuenta) { // mismo metodo que ingreso pero distinto desarrollo, ya que en este caso es de retirada y hemos 
        //hecho en vez de un switch unos if los cuales nos muestran como a ido la retirada.
        System.out.println("Cantidad retirada: ");
        cantidad = Double.parseDouble(sc.nextLine());

        System.out.println("Remitente: ");
        remitente = sc.nextLine();

        System.out.println("Concepto: ");
        concepto = sc.nextLine();

        tipoMovimiento = "Retirada";

        if (cantidad >= 0) {
            if ((nuevaCuenta.getSaldo() - cantidad) < SALDO_MINIMO) { //  si el saldo final es menos de -50 no le permitimos retirar.
                System.out.println("\n No se permite tener un saldo por debajo de -50\n");
                System.out.println("*Retirada cancelada.*\n");

            } else {
                if (nuevaCuenta.getSaldo() - cantidad < 0) {// si el saldo final es enrte -50 y 0 le informamos que hay un descubierto en su cuenta.
                    System.out.println(AVISO_NEGATIVO);
                    System.out.println("Se han retirado: " + nuevaCuenta.formatoEuros(cantidad) + "\n");
                    System.out.println("Saldo: " + nuevaCuenta.formatoEuros(nuevaCuenta.getSaldo()));
                }
                if (cantidad >= MOVIMIENTO_GRANDE) {// si lacantidad a retirar es mayor de 3000 le informamos de que informaremos a hacienda.
                    System.out.println(AVISO_HACIENDA);
                }

                nuevaCuenta.retirada(cantidad, remitente, concepto, tipoMovimiento);
                System.out.println("*Retirada realizada con éxito*\n");
                System.out.println("Se han retirado: " + nuevaCuenta.formatoEuros(cantidad) + "\n");
                System.out.println("Saldo: " + nuevaCuenta.formatoEuros(nuevaCuenta.getSaldo()));
            }
        } else {
            System.out.println("\n Introduzca un importe igual o superior a 0 \n");
            System.out.println("\n *Retirada cancelada* \n");
        }
//        
//       
    }
// Opcion 4  - Registrar Autorizado.

    public static String registrarAutorizado(CuentaBancaria nuevaCuenta, String autorizadoDNI) { // en este metodo registraremos a autorizados, comprobando un DNI correcto y si se encuentra ya en nuestra lista o no.
        checkDNI = true;
        while (checkDNI) {
            System.out.println("Indique el DNI del nuevo autorizado: ");
            autorizadoDNI = sc.nextLine();
            Matcher mat = PATRONDNI.matcher(autorizadoDNI);
            if (mat.matches()) {
                comprobarDni(autorizadoDNI);
                checkDNI = false;
            } else {
                System.out.println("Indique un DNI correcto.");
            }

        }

        System.out.println("Indique el nombre del nuevo autorizado: ");
        autorizadoNombre = sc.nextLine();

        if (nuevaCuenta.autorizar(autorizadoDNI, autorizadoNombre)) {
            System.out.println("El nuevo autorizado se ha añadido correctamente");
            System.out.println("En esta cuenta hay " + (nuevaCuenta.getAutorizados().size()) + " personas autorizadas.\n");
        } else {
            System.out.println("Este autorizado ya existe en esta cuenta.");
        }
        return autorizadoDNI;
    }
// Opcion 5  - Eliminar Autorizado.

    public static void eliminarAutorizado(CuentaBancaria nuevaCuenta) {// en este metodo borraremos a un autorizado que coincida con el DNI que le pedimos, ademas le informamos si el dni no esta registrado o si el DNI no es correcto, 
        // por otro lado, no permitiremos que haya 0 titulares en la cuenta.

        if (nuevaCuenta.getAutorizados().size() >= 1) {

            checkDNI = true;
            while (checkDNI) {
                System.out.println("Indique el DNI de la persona que quiere eliminar: ");
                autorizadoDNI = sc.nextLine();
                Matcher mat = PATRONDNI.matcher(autorizadoDNI);
                if (mat.matches()) {
                    comprobarDni(autorizadoDNI);
                    checkDNI = false;
                } else {
                    System.out.println("Indique un DNI correcto.");
                    String autorizadoDNI = sc.nextLine();

                }

            }
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
    public static void historialMovimientos(CuentaBancaria nuevaCuenta) {// metodo (opcion 1 del menu de movimientos) el cual nos muestra todos los movimientos.

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

    public static void historialIngresos(CuentaBancaria nuevaCuenta) {// metodo (opcion 2 del menu de movimientos) el cual nos muestra solo los ingresos.

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

    public static void historialRetiradas(CuentaBancaria nuevaCuenta) {// metodo (opcion 3 del menu de movimientos) el cual nos muestra dolo las retiradas. 

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
// 6

    public static int menuHistorialMovimientos() {// muestra el submenu de movimientos.
        System.out.println("");
        System.out.println("Selecciona una opción.");
        System.out.println("1. Muestrame todos los movimientos.");
        System.out.println("2. Muestrame todos los ingreso.");
        System.out.println("3. Muestrame todas las retiradas.");
        System.out.println("4. Atras.");
        return respuestaUsuario = Integer.parseInt(sc.nextLine());
    }

    public static String generarIBAN(int respuestaUsuario) {// metodo que genera el numero apropiado de iban

        switch (respuestaUsuario) {// proporciona unos digitos segun el pais ciudad y oficina, los cuales seran utilizados para generar unos digitos de control.

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
        // generamos un numero aleatorio para la parte del iban donde se concatenara con la resolucion de eleccion de pais ciudad y oficina, y los codigos de control de ciudad y oficina.
        ibanRandom = ((int) Math.floor(Math.random() * (IBANMAXIMO - IBANMINIMO + 1) + IBANMINIMO)) + "";
        iban = pais + codPais + codCiudad + codDir + (String.valueOf(codCiudad).charAt(0)) + (String.valueOf(codDir).charAt(0)) + ibanRandom;

        return iban;
    }

    //Variables

    private static double cantidad = 0;
    private static int respuestaUsuario = 8, controlMovimiento, eleccionPais, numDNI, resto = 0;
    private static String DNI, pin = "0", pin2 = "0", pinExp = "\\d{4}", letraDNI = "", codPais, codCiudad, codDir, ibanRandom, infoCuenta, pais, tmpPais,
            autorizadoDNI, autorizadoNombre, iban = "", nomTitular,
            remitente = "", concepto = "", stringConcepto = "Concepto: ",
            stringRemitente = "Remitente: ", tipoMovimiento, cantidadString = "Cantidad: ";
    
    private static boolean ibancorrecto = false, paisCorrecto = true, checkDNI = false, seguir = true, salirPin = false;
    private static Scanner sc = new Scanner(System.in);
    private static Persona titular;
    private static CuentaBancaria nuevaCuenta;
    private static final int SALDO_MINIMO = -50, MOVIMIENTO_GRANDE = 3000;
    private static final String AVISO_HACIENDA = "\nAviso, notificacion a hacienda \n", AVISO_NEGATIVO = "\nAviso, Tiene un descubierto en la cuenta \n de";
    private static final long IBANMINIMO = 0000000001, IBANMAXIMO = 9999999999L;
    private static final Pattern PATRONDNI = Pattern.compile("[0-9]{7,8}[A-Z a-z]");

    private static String[] asignacionLetra = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};

}
