import java.util.List;
import java.util.Scanner;

import entidades.Casa;
import entidades.Familia;
import servicios.CasaService;
import servicios.FamiliaService;

public class App {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        FamiliaService familiaSC = new FamiliaService();
        CasaService casaSC = new CasaService();
        int opcion = 0;

        do {
            // Mostrar menú
            System.out.println("Seleccione una opción:");
            System.out.println("1. Listar familias con al menos 3 hijos y edad máxima menor a 10");
            System.out.println("2. listar las casas disponibles para el periodo comprendido entre el 1 de agosto de 2020 y el 31 de agosto de 2020 en Reino Unido");
            System.out.println("5. Salir");
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
                    List<Casa> casaa = casaSC.listarCasasEjercicio1();
                
                    if (casaa.isEmpty()) {
                        System.out.println("No se encontraron casas que cumplan los criterios.");
                    } else {
                        // Imprimir encabezados de la tabla
                        System.out.printf("%-10s | %-15s | %-6s | %-10s | %-15s | %-12s | %-12s%n", 
                            "ID Casa", "Calle", "Número", "Ciudad", "País", "Fecha Desde", "Fecha Hasta");
                        System.out.println("--------------------------------------------------------------------------");
                
                        // Imprimir los datos de las casas
                        for (Casa casa : casaa) {
                            System.out.printf("%-10d | %-15s | %-6d | %-10s | %-15s | %-12s | %-12s%n", 
                                casa.getId_casa(), 
                                casa.getCalle(), 
                                casa.getNumero(), 
                                casa.getCiudad(), 
                                casa.getPais(), 
                                casa.getFecha_desde(), 
                                casa.getFecha_hasta());
                        }
                    }
                    break;
                
                case 5:
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
