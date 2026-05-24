package model;
public class Monedero {
    private double saldoAcumulado = 0;

    public boolean pagarConTarjeta(double importe) {
        System.out.println("[TARJETA] Conectando con el terminal bancario...");
        System.out.println("[TARJETA] Operacion autorizada por " + importe + " euros.");
        return true; 
    }

    public boolean pagarConEfectivo(double cantidad) {
        if (cantidad < 0.50) {
            System.out.println("[MONEDERO] Error: No se aceptan monedas inferiores a 0.50 euros.");
            return false;
        }

        if (cantidad > 5.0) {
            System.out.println("[MONEDERO] Error: No se aceptan billetes superiores a 5 euros.");
            return false;
        }

        if (cantidad == 0.50 || cantidad == 1.0 || cantidad == 2.0 || cantidad == 5.0) {
            saldoAcumulado += cantidad;
            System.out.printf("[MONEDERO] Dinero aceptado. Saldo actual: %.2f euros.\n", saldoAcumulado);
            return true;
        } else {
            System.out.println("[MONEDERO] Error: Denominacion no reconocida por el lector.");
            return false;
        }
    }
    

    public void darCambio(double precioProducto) {

        double cambio = saldoAcumulado - precioProducto;
        
        if (cambio <= 0) {
            this.saldoAcumulado = 0;
            return;
        }

        System.out.printf("[MONEDERO] Su cambio es de %.2f euros.\n", cambio);

        if (cambio % 0.50 != 0) {
            System.out.println("[AVISO] El cambio incluye fracciones menores a 0.50 euros.");
            System.out.println("[AVISO] Por favor, recoja sus monedas");
        } else {
            System.out.println("[MONEDERO] Cambio entregado en monedas de curso legal (min. 0.50e).");
        }
        
        this.saldoAcumulado = 0; // Resetear saldo tras la compra
    }

    public double getSaldoAcumulado() {
        return saldoAcumulado;
    }
}