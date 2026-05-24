package model;
import excepciones.Notificador;

public class Queso extends Producto {
    private int unidades;

    public Queso(double precio, int stockInicial, Notificador n) {
        super("Queso Payoyo (Cuna 250g)", precio, n);
        this.unidades = stockInicial;
    }

    @Override
    public boolean hayStock(int cantidad) {
        return unidades >= cantidad;
    }

    @Override
    public void dispensar(int cantidad) {
        this.unidades -= cantidad;
        System.out.println("[DISPENSADOR] Entregando " + cantidad + " cuna(s) de queso.");
        if (unidades <= 0) {
            notificador.notificar("STOCK AGOTADO: No quedan cunas de queso.");
        }
    }
}