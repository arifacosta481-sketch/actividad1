import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class FastDeliveryApp {

    // Clase simple para representarel pedido del cliente
    static class Pedido {
        int id;
        String cliente;
        double distanciaKm; // Criterio de ordenamiento

        public Pedido(int id, String cliente, double distanciaKm) {
            this.id = id;
            this.cliente = cliente;
            this.distanciaKm = distanciaKm;
        }

        @Override
        public String toString() {
            return "Pedido #" + id + " (" + cliente + ") - " + distanciaKm + " km";
        }
    }

    public static void main(String[] args) {
        System.out.println("=== FAST DELIVERY APP ===\n");

        // 1. LISTAS (ArrayList) - Catálogo y Carrito
        List<String> carrito = new ArrayList<>();
        carrito.add("Hamburguesa");
        carrito.add("Papas Fritas");
        carrito.add("Refresco");
        carrito.remove("Papas Fritas"); // Baja automática/eliminación
        System.out.println("1. Carrito del cliente: " + carrito);

        // 2. PILA (Stack) - Historial de pantallas y Deshacer
        Stack<String> historial = new Stack<>();
        historial.push("Inicio");
        historial.push("Detalle Pedido");
        System.out.println("2. Pantalla actual: " + historial.peek());
        System.out.println("   Regresando a: " + historial.pop()); // Botón atrás

        // 3. COLA (Queue) - Pedidos en Cocina (FIFO)
        Queue<String> colaCocina = new LinkedList<>();
        colaCocina.add("Pedido #1");
        colaCocina.add("Pedido #2");
        System.out.println("3. Cocina atiende primero a: " + colaCocina.poll()); // Primer entrado, primer atendido

        // 4. ALGORITMO DE ORDENAMIENTO (QuickSort - Menor a Mayor)
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido(101, "Carlos", 4.2));
        pedidos.add(new Pedido(102, "Ana", 1.5));
        pedidos.add(new Pedido(103, "Luis", 8.0));
        pedidos.add(new Pedido(104, "Sofia", 2.3));

        System.out.println("\n4. Pedidos SIN ORDENAR:");
        imprimirLista(pedidos);

        // Aplicar ordenamiento por menor distancia
        quickSort(pedidos, 0, pedidos.size() - 1);

        System.out.println("\n4. Pedidos ORDENADOS (Ruta óptima de menor a mayor distancia):");
        imprimirLista(pedidos);
    }

    // --- ALGORITMO QUICKSORT (Menor a Mayor) ---
    public static void quickSort(List<Pedido> lista, int inicio, int fin) {
        if (inicio < fin) {
            int pivoteIndex = particion(lista, inicio, fin);
            quickSort(lista, inicio, pivoteIndex - 1);
            quickSort(lista, pivoteIndex + 1, fin);
        }
    }

    private static int particion(List<Pedido> lista, int inicio, int fin) {
        double pivote = lista.get(fin).distanciaKm;
        int i = inicio - 1;

        for (int j = inicio; j < fin; j++) {
            // Condición para ordenar de menor a mayor (<)
            if (lista.get(j).distanciaKm < pivote) {
                i++;
                // Intercambio
                Pedido temp = lista.get(i);
                lista.set(i, lista.get(j));
                lista.set(j, temp);
            }
        }

        // Colocar el pivote en su posición final
        Pedido temp = lista.get(i + 1);
        lista.set(i + 1, lista.get(fin));
        lista.set(fin, temp);

        return i + 1;
    }

    public static void imprimirLista(List<Pedido> lista) {
        for (Pedido p : lista) {
            System.out.println("  " + p);
        }
    }
}