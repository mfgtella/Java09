package org.generation;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CajeroAutomatico {
    public static void main(String[] args) {
        double saldo = 10000.00;
        int intentosFallidos = 0;
        List<String> movimientos = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Opciones del cajero:");
            System.out.println("1) Retirar dinero");
            System.out.println("2) Hacer depósitos");
            System.out.println("3) Consultar saldo");
            System.out.println("4) Quejas");
            System.out.println("5) Ver últimos movimientos");
            System.out.println("9) Salir del cajero");
            System.out.print("Elija una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después de la entrada del número

            switch (opcion) {
                case 1:
                    double cantidadRetiro = retirarDinero(saldo, scanner);
                    if (cantidadRetiro > 0) {
                        saldo -= cantidadRetiro;
                        movimientos.add(obtenerFechaHora() + " Retiro de $" + cantidadRetiro);
                        System.out.println("¿Desea donar $200 para la graduación de ch30? (S/N): ");
                        String donar = scanner.nextLine();
                        if (donar.equalsIgnoreCase("S")) {
                            saldo -= 200;
                            System.out.println("Gracias por su donación.");
                        }
                        System.out.println("Retiro exitoso.");
                    }
                    break;
                case 2:
                    double cantidadDeposito = hacerDepositos(saldo, scanner);
                    if (cantidadDeposito > 0) {
                        saldo += cantidadDeposito;
                        movimientos.add(obtenerFechaHora() + " Depósito de $" + cantidadDeposito);
                        System.out.println("Depósito exitoso.");
                    }
                    break;
                case 3:
                    consultarSaldo(saldo);
                    break;
                case 4:
                    quejas();
                    break;
                case 5:
                    verUltimosMovimientos(movimientos);
                    break;
                case 9:
                    salirDelCajero();
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción inválida, por favor, vuelva a intentar.");
                    intentosFallidos++;
                    if (intentosFallidos >= 3) {
                        System.out.println("Ha excedido el número de intentos permitidos. Saliendo del cajero.");
                        scanner.close();
                        return;
                    }
            }
        }
    }

    private static double retirarDinero(double saldo, Scanner scanner) {
        System.out.println("Saldo disponible para retirar: $" + saldo);
        System.out.print("Ingrese la cantidad a retirar (múltiplos de $50.00 y no más de $6,000.00): ");
        double cantidad = scanner.nextDouble();

        if (cantidad % 50 == 0 && cantidad <= 6000 && cantidad <= saldo) {
            return cantidad;
        } else {
            System.out.println("Cantidad de retiro no válida.");
            return 0;
        }
    }

    private static double hacerDepositos(double saldo, Scanner scanner) {
        System.out.print("Ingrese la cantidad a depositar: ");
        double cantidad = scanner.nextDouble();

        if (cantidad > 0) {
            return cantidad;
        } else {
            System.out.println("Cantidad de depósito no válida.");
            return 0;
        }
    }

    private static void consultarSaldo(double saldo) {
        System.out.println("Saldo disponible: $" + saldo);
    }

    private static void quejas() {
        System.out.println("No disponible por el momento, intente más tarde.");
    }

    private static void verUltimosMovimientos(List<String> movimientos) {
        System.out.println("Últimos movimientos:");
        int contador = 1;
        for (String movimiento : movimientos) {
            if (contador > 5) {
                break;
            }
            System.out.println(movimiento);
            contador++;
        }
    }

    private static void salirDelCajero() {
        System.out.println("Gracias por usar el cajero. ¡Hasta luego!");
    }

    private static String obtenerFechaHora() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        return dateFormat.format(new Date());
    }
}
