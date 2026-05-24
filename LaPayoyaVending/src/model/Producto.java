package model;
import excepciones.Notificador;

public abstract class Producto {
    protected String nombre;
    protected double precio;
    protected Notificador notificador;

    public Producto(String nombre, double precio, Notificador n) {
        this.nombre = nombre;
        this.precio = precio;
        this.notificador = n;
    }

    public abstract boolean hayStock(int cantidad);
    public abstract void dispensar(int cantidad);
    
    public String getNombre() { 
        return nombre;
    }
}