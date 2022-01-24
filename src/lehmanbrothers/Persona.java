
package lehmanbrothers;

/**
 *
 * @author Enrique
 */
public class Persona {
    
     private final String DNI;
    private String nombre;

      
    public Persona(String DNI, String nombre) {
        this.DNI = DNI;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDNI() {
        return DNI;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    //COMPARA ESTA PERSONA EN LA QUE ESTAMOS CON OTRO OBJETO PERSONA (PARÁMETRO otraPersona)
    public boolean igual(Persona otraPersona){
        boolean resultado=false;
        
        if(DNI.equalsIgnoreCase(otraPersona.getDNI())){
            resultado=true;
        }
        return resultado;        
       //OTRA FORMA DE HACERLO: USANDO EL MÉTODO igual(String nif)
       //   return this.igual(person.getNif());
    }
    
     //COMPARA NIF DE ESTA PERSONA EN LA QUE ESTAMOS CON OTRO NIF
     public boolean igual(String otroDNI){
        boolean resultado=false;
        
        if(DNI.equalsIgnoreCase(otroDNI)){
            resultado=true;
        }
        return resultado;
        
        //OTRA FORMA DE ESCRIBIRLO
        // return nif.equalsIgnoreCase(dni);
    }
    
    
    @Override
    public String toString(){
        return String.format("%s (%s)",nombre,DNI);
    }
}
