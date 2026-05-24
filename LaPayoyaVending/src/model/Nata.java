package model;
import excepciones.Notificador;

public class Nata extends Producto {
    private int tanqueNataml;

    public Nata(double precio, int capacidadInicial, Notificador n) {
        super("Nata Fresca", precio, n);
        this.tanqueNataml = capacidadInicial;
    }

    @Override
    public boolean hayStock(int ml) {
        return tanqueNataml >= ml;
    }

    @Override
    public void dispensar(int ml) {
        this.tanqueNataml -= ml;
        System.out.println("[DISPENSADOR] Sirviendo " + ml + "ml de nata espesa.");
        if (tanqueNataml < 2000) {
            notificador.notificar("NIVEL CRITICO: Deposito de NATA por debajo de 2L.");
        }
    }

    public double getPrecio(int ml) {
        double precioFinal;
        switch (ml) {
            case 100: 
                precioFinal = precio/2; 
                break;
            case 250: 
                precioFinal = precio; 
                break;
            case 500: 
                precioFinal = precio + 1.5;
                break;
            default: throw new IllegalArgumentException(
                "Tamaño no disponible para Nata: " + ml + "ml. Use 100, 250 o 500.");
        }
        return precioFinal;
    }
}