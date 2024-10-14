import java.util.List;
import java.util.Scanner;

import entidades.Familia;
import servicios.FamiliaService;

public class App {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        FamiliaService familiaSC = new FamiliaService();
        int opcion = 0;

        do {
            // Mostrar menú
            System.out.println("Seleccione una opción:");
            System.out.println("1. Listar familias con al menos 3 hijos y edad máxima menor a 10");
            System.out.println("2. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    List<Familia> familias = familiaSC.listarFamiliasEjercicio1();

                    if (familias.isEmpty()) {
                        System.out.println("No se encontraron familias que cumplan los criterios.");
                    } else {
                        System.out.printf("%-5s %-20s %-15s %-15s%n", "ID", "Nombre", "Número de Hijos", "Edad Máxima");
                        System.out.println("---------------------------------------------------------------");

                        for (Familia familia : familias) {
                            System.out.printf("%-5d %-20s %-15d %-15d%n", 
                                familia.getId(),
                                familia.getNombre(),
                                familia.getNum_hijos(),
                                familia.getEdad_maxima());
                        }
                    }
                    break;

                case 2:
                    // Salir del programa
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 2); // Seguir mostrando el menú hasta que el usuario elija la opción de salir

        sc.close(); // Cerrar el scanner
    }
}
