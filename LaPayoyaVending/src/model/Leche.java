package model;
import excepciones.Notificador;

public class Leche extends Producto{
    private int tanqueLecheml;

    public Leche(double precio, int capacidadInicial, Notificador n) {
        super("Leche Fresca", precio, n);
        this.tanqueLecheml = capacidadInicial;
    }

    @Override
    public boolean hayStock(int ml) {
        return tanqueLecheml >= ml;
    }

    @Override
    public void dispensar(int ml) {
        this.tanqueLecheml -= ml;
        System.out.println("[DISPENSADOR] Sirviendo " + ml + "ml de leche fresca de la Sierra.");
        if (tanqueLecheml < 2000) {
            notificador.notificar("NIVEL CRITICO: Deposito de LECHE por debajo de 2L.");
        }
    }

    public double getPrecio(int ml) {
        double precioFinal;
        switch (ml) {
            case 500:  
                precioFinal = precio;       
                break;
            case 1000: 
                precioFinal = precio*2; 
                break;
            default: throw new IllegalArgumentException(
                "Tamaño no disponible para Leche: " + ml + "ml. Use 500 o 1000.");
        }
        return precioFinal;
    }
}