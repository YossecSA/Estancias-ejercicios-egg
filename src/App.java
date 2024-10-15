import java.util.List;
import java.util.Scanner;

import entidades.Casa;
import entidades.Cliente;
import entidades.Estancia;
import entidades.Familia;
import servicios.CasaService;
import servicios.ClienteService;
import servicios.EstanciaService;
import servicios.FamiliaService;

public class App {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        FamiliaService familiaSC = new FamiliaService();
        CasaService casaSC = new CasaService();
        ClienteService clienteService = new ClienteService();
        EstanciaService estanciaService = new EstanciaService();

        int opcion = 0;

        do {
            // Mostrar menú
            System.out.println("Seleccione una opción:");
            System.out.println("1. Listar familias con al menos 3 hijos y edad máxima menor a 10");
            System.out.println("2. listar las casas disponibles para el periodo comprendido entre el 1 de agosto de 2020 y el 31 de agosto de 2020 en Reino Unido");
            System.out.println("3. Buscar y listar  todas aquellas familias cuya dirección de email sea Hotmail. ");
            System.out.println("4. Consulta la BD para que te devuelva aquellas casas disponibles a partir de una fecha dada y un número de días específico. ");
            System.out.println("5. Buscar y listar los datos de todos los clientes que en algún momento realizaron una estancia y la descripción de la casa donde la realizaron. ");
            
            System.out.println("7. Salir");
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

                case 3: 
                    List<Familia> familiasHotmail = familiaSC.listarFamiliasHotmail();
                
                    if (familiasHotmail.isEmpty()) {
                        System.out.println("No se encontraron familias con correo Hotmail.");
                    } else {
                        // Imprimir encabezados de la tabla
                        System.out.printf("%-10s | %-15s | %-6s | %-10s | %-25s%n", 
                            "ID", "Nombre", "Hijos", "Edad Máx", "Email");
                        System.out.println("--------------------------------------------------------------");
                
                        // Imprimir los datos de las familias
                        for (Familia familia : familiasHotmail) {
                            System.out.printf("%-10d | %-15s | %-6d | %-10d | %-25s%n", 
                                familia.getId(), 
                                familia.getNombre(), 
                                familia.getNum_hijos(), 
                                familia.getEdad_maxima(), 
                                familia.getEmail());
                        }
                    }
                    break;

                case 4: 
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Ingrese la fecha desde (YYYY-MM-DD): ");
                    String fechaDesde = scanner.nextLine();
                    System.out.print("Ingrese el número de días: ");
                    int numeroDias = scanner.nextInt();
                
                    List<Casa> casasDisponibles = casaSC.listarCasasDisponibles(fechaDesde, numeroDias);
                
                    if (casasDisponibles.isEmpty()) {
                        System.out.println("No se encontraron casas disponibles para el periodo solicitado.");
                    } else {
                        System.out.printf("%-10s | %-15s | %-6s | %-10s | %-25s%n", 
                            "ID", "Calle", "Número", "Ciudad", "Pais");
                        System.out.println("--------------------------------------------------------------");
                
                        for (Casa casa : casasDisponibles) {
                            System.out.printf("%-10d | %-15s | %-6d | %-10s | %-25s%n", 
                                casa.getId_casa(), 
                                casa.getCalle(), 
                                casa.getNumero(), 
                                casa.getCiudad(), 
                                casa.getPais());
                        }
                    }
                    break;

                case 5:
                    try {
                        List<Cliente> clientesConEstancia = clienteService.listarClientesEstanciaDescripcion();

                        if (clientesConEstancia.isEmpty()) {
                            System.out.println("No se encontraron clientes con estancias registradas.");
                        } else {
                            // Encabezado de la tabla
                            System.out.printf("%-5s | %-15s | %-15s | %-20s | %-15s | %-10s | %-15s | %-5s | %-15s | %-10s%n",
                                    "ID", "Cliente", "Ciudad", "Email", "Huésped", "Desde", "Hasta", "Casa", "Calle", "Tipo Vivienda");
                            System.out.println("---------------------------------------------------------------------------------------------" +
                                            "------------------------------------------------------");

                            // Mostrar los datos de los clientes con estancia y la descripción de la casa
                            for (Cliente cliente : clientesConEstancia) {
                                Estancia estancia = cliente.getEstancia();
                                Casa casa = estancia.getCasa();

                                System.out.printf("%-5d | %-15s | %-15s | %-20s | %-15s | %-10s | %-10s | %-5d | %-15s | %-10s%n",
                                        cliente.getId_cliente(), // ID Cliente
                                        cliente.getNombre(),     // Nombre Cliente
                                        cliente.getCiudad(),     // Ciudad Cliente
                                        cliente.getEmail(),      // Email Cliente
                                        estancia.getNombre_huesped(), // Nombre Huésped
                                        estancia.getFecha_desde(),    // Fecha desde
                                        estancia.getFecha_hasta(),    // Fecha hasta
                                        casa.getNumero(),          // Número de casa
                                        casa.getCalle(),           // Calle de la casa
                                        casa.getTipo_vivienda()     // Tipo de vivienda
                                );
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 6:
                    try {
                        List<Estancia> estancias = estanciaService.listarEstanciasConClientesCasa();
                
                        if (estancias.isEmpty()) {
                            System.out.println("No se encontraron estancias registradas.");
                        } else {
                            // Mostrar los datos de estancias, clientes y casas
                            System.out.printf("%-5s | %-20s | %-10s | %-15s | %-15s | %-10s | %-10s | %-15s | %-10s%n",
                                    "ID", "Cliente", "País", "Ciudad", "Casa Calle", "Casa Número", "Casa Ciudad", "Casa País", "Tipo Vivienda");
                            System.out.println("------------------------------------------------------------------------------------------------------" +
                                                "---------------------------------------------");
                
                            for (Estancia estancia : estancias) {
                                Cliente cliente = estancia.getCliente();
                                Casa casa = estancia.getCasa();
                
                                System.out.printf("%-5d | %-20s | %-10s | %-15s | %-15s | %-10d | %-10s | %-15s | %-10s%n",
                                        estancia.getId_estancia(),
                                        cliente.getNombre(),
                                        cliente.getPais(),
                                        cliente.getCiudad(),
                                        casa.getCalle(),
                                        casa.getNumero(),
                                        casa.getCiudad(),
                                        casa.getPais(),
                                        casa.getTipo_vivienda()
                                );
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                

                case 7:
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
