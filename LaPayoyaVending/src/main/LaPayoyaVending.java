package main;

import excepciones.Notificador;
import java.util.Scanner;
import model.Leche;
import model.Monedero;
import model.Nata;
import model.Producto;
import model.Queso;

public class LaPayoyaVending {
    private Queso queso;
    private Nata nata;
    private Leche leche;
    private Monedero monedero;
    private Notificador n;
 
    public LaPayoyaVending() {
        this.n = (mensaje) -> System.out.println("[ALERTA SISTEMA] " + mensaje);
        this.queso   = new Queso(6.50, 10, n);
        this.leche   = new Leche(1.00, 10000, n);
        this.nata    = new Nata(2.00, 10000, n);
        this.monedero = new Monedero();
    }

    private void mostrarMenuPrincipal() {
        System.out.println("\n------------------------");
        System.out.println("    LA PAYOYA VENDING   ");
        System.out.println("------------------------");
 
        // Notificaciones de stock bajo visibles en el menu
        if (!queso.hayStock(5))
            System.out.println("QUESO: Stock bajo");
        if (!leche.hayStock(2000))
            System.out.println("[!] LECHE: stock bajo");
        if (!nata.hayStock(2000))
            System.out.println("[!] NATA: stock bajo");
 
        System.out.println();
        System.out.println("[1] Comprar queso   (cuña 250g - 6.50€)");
        System.out.println("[2] Comprar leche   (500ml - 1.00 euros / 1L - 2.00 euros)");
        System.out.println("[3] Comprar nata    (100ml - 1.00 euros / 250ml - 2.00 euros / 500ml - 3.50 euros)");
        System.out.println("[0] Salir");
        System.out.println("------------------------");
    }

    private void comprarQueso(Scanner teclado) {

        if (!queso.hayStock(0)) {
            System.out.println("[!] Lo sentimos, no quedan cuñas de queso.");
            return;
        }
        procesarCompra(teclado, queso, 6.50, 1);
    }
 
    private void comprarLeche(Scanner teclado) {
        System.out.println("[1] 500ml  - 1.00 euros");
        System.out.println("[2] 1L     - 2.00 euros");
        System.out.print("Diga cantidad: ");
        int sel = teclado.nextInt();
        int ml;
        if (sel == 1) {
            ml = 500;
        } else {
            ml = 1000;
        }
 
        try {
            double precio = leche.getPrecio(ml);
            if (!leche.hayStock(ml)) {
                System.out.println("[!] Stock de leche insuficiente.");
                return;
            }
            procesarCompra(teclado, leche, precio, ml);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    private void comprarNata(Scanner teclado) {
        System.out.println("\n[1] 100ml  - 1.00 euros");
        System.out.println("[2] 250ml  - 2.00 euros");
        System.out.println("[3] 500ml  - 3.50 euros");
        System.out.print("Diga cantidad: ");
        int sel = teclado.nextInt();
        int ml;
        switch (sel) {
            case 1:
                ml = 100;
                break;
            case 2:
                ml = 250;
                break;
            default:
                ml = 500;
                break;
        }
 
        try {
            double precio = nata.getPrecio(ml);
            if (!nata.hayStock(ml)) {
                System.out.println("[!] Stock de nata insuficiente.");
                return;
            }
            procesarCompra(teclado, nata, precio, ml);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    private void procesarCompra(Scanner teclado, Producto p, double precio, int cantidad) {
        System.out.printf("\nTotal: %.2f\n", precio);
        System.out.println("[T] Pago con tarjeta");
        System.out.println("[E] Efectivo");
        System.out.print("Metodo de pago: ");
        String metodo = teclado.next().toUpperCase();
 
        boolean pagado = false;
 
        switch (metodo) {
            case "T":
                pagado = monedero.pagarConTarjeta(precio); break;
            case "E":
                System.out.println("Introduzca monedas o billetes (0.5, 1, 2, 5 euros).");
                while (monedero.getSaldoAcumulado() < precio) {
                    System.out.printf("  Saldo: %.2f€ — Introduzca importe: ", monedero.getSaldoAcumulado());
                    double moneda = teclado.nextDouble();
                    monedero.pagarConEfectivo(moneda);
                }
                pagado = true;
                break;
            default:
                System.out.println("[ERROR] Metodo no reconocido. Operacion cancelada.");
                break;
        }
 
        if (pagado) {
            p.dispensar(cantidad);
            if (metodo.equals("E")) monedero.darCambio(precio);
            System.out.println("¡Disfrute su producto de La Payoya!");
        }
    }

    public void ejecutar() {
        Scanner teclado = new Scanner(System.in);
        int opcion;
 
        do {
            mostrarMenuPrincipal();
 
            System.out.print("Introduzca codigo: ");
            opcion = teclado.nextInt();
 
            switch (opcion) {
                case 1:
                    comprarQueso(teclado); break;
                case 2:
                    comprarLeche(teclado); break;
                case 3: 
                    comprarNata(teclado); break;
                case 0: 
                    System.out.println("\nGracias por su visita. ¡Hasta pronto!");
                    break;
                default: 
                    System.out.println("Codigo no valido. Intentelo de nuevo.");
                    break;
            }
        } while (opcion != 0);
 
        teclado.close();
    }

    public static void main(String[] args) {
        LaPayoyaVending maquina = new LaPayoyaVending();
        maquina.ejecutar();
    }
}
