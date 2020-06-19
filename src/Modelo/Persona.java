
package Modelo;

/**
 *
 * @author Andres
 */
public class Persona {
    
    private  String nombre, apellido,ciudad,telefono,id;
    private  int cargo;

    public Persona(String nombre, String apellido, String ciudad, String id, String telefono, int cargo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.ciudad = ciudad;
        this.id = id;
        this.telefono = telefono;
        this.cargo = cargo;
    }
    public Persona(){
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getCargo() {
        return cargo;
    }

    public void setCargo(int cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", apellido=" + apellido + ", ciudad=" + ciudad + ", telefono=" + telefono + ", id=" + id + ", cargo=" + cargo + '}';
    }
    
    
    
         
    
}
