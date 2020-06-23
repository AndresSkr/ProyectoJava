package Modelo;

public class Producto {

    private String nombre;
    private float precio;
    private int cantidad, id;

    public Producto(String nombre, float precio, int cantidad, int id) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.id = id;
    }

    public Producto() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
