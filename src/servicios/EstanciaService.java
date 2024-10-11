package servicios;

import java.util.List;
import java.util.Optional;
import entidades.Estancia;
import persistencia.EstanciaDAO;

public class EstanciaService {
    private EstanciaDAO estanciaDAO;

    public EstanciaService() {
        estanciaDAO = new EstanciaDAO();
    }

    /**
     * Guarda una nueva estancia.
     *
     * @param id_cliente    El ID del cliente.
     * @param id_casa      El ID de la casa.
     * @param nombre_huesped El nombre del huésped.
     * @param fecha_desde   La fecha de inicio de la estancia.
     * @param fecha_hasta   La fecha de finalización de la estancia.
     * @throws InvalidEstanciaException si los datos de la estancia son inválidos.
     */
    public void guardarEstancia(int id_cliente, int id_casa, String nombre_huesped, String fecha_desde, String fecha_hasta) throws Exception {
        Estancia _estancia = new Estancia(id_cliente, id_casa, nombre_huesped, fecha_desde, fecha_hasta);
        validarEstancia(_estancia);
        estanciaDAO.guardarEstancia(_estancia);
    }

    /**
     * Modifica una estancia existente.
     *
     * @param id_estancia   El ID de la estancia a modificar.
     * @param id_cliente    El ID del cliente.
     * @param id_casa      El ID de la casa.
     * @param nombre_huesped El nombre del huésped.
     * @param fecha_desde   La fecha de inicio de la estancia.
     * @param fecha_hasta   La fecha de finalización de la estancia.
     * @throws InvalidEstanciaException si los datos de la estancia son inválidos.
     */
    public void modificarEstancia(int id_estancia, int id_cliente, int id_casa, String nombre_huesped, String fecha_desde, String fecha_hasta) throws Exception {
        if (id_estancia <= 0) {
            throw new Exception("El ID de la estancia debe ser mayor que 0.");
        }
        Estancia _estancia = new Estancia(id_estancia, id_cliente, id_casa, nombre_huesped, fecha_desde, fecha_hasta);
        validarEstancia(_estancia);
        estanciaDAO.modificarEstancia(_estancia);
    }

    /**
     * Elimina una estancia existente.
     *
     * @param id_estancia   El ID de la estancia a eliminar.
     * @throws InvalidEstanciaException si el ID de la estancia es inválido.
     */
    public void eliminarEstancia(int id_estancia) throws Exception {
        if (id_estancia <= 0) {
            throw new Exception("El ID de la estancia a eliminar debe ser mayor que 0.");
        }
        estanciaDAO.eliminarEstancia(id_estancia);
    }

    /**
     * Busca una estancia por su ID.
     *
     * @param id_estancia   El ID de la estancia a buscar.
     * @return Un Optional que contiene la estancia encontrada o vacío si no existe.
     */
    public Optional<Estancia> buscarEstanciaPorId(int id_estancia) throws Exception {
        if (id_estancia <= 0) {
            throw new Exception("El ID de la estancia a buscar debe ser mayor que 0.");
        }
        return Optional.ofNullable(estanciaDAO.buscarEstanciaPorId(id_estancia));
    }

    /**
     * Lista las estancias de un cliente específico.
     *
     * @param id_cliente    El ID del cliente.
     * @return Lista de estancias del cliente.
     */
    public List<Estancia> listarEstanciasPorCliente(int id_cliente) throws Exception {
        if (id_cliente <= 0) {
            throw new Exception("El ID del cliente debe ser mayor que 0.");
        }
        return estanciaDAO.listarEstanciasPorCliente(id_cliente);
    }

    private void validarEstancia(Estancia estancia) throws Exception {
        if (estancia == null) {
            throw new Exception("La estancia no puede ser nula.");
        }
        if (estancia.getId_cliente() <= 0) {
            throw new Exception("El ID del cliente debe ser mayor que 0.");
        }
        if (estancia.getId_casa() <= 0) {
            throw new Exception("El ID de la casa debe ser mayor que 0.");
        }
        if (estancia.getNombre_huesped() == null || estancia.getNombre_huesped().trim().isEmpty()) {
            throw new Exception("El nombre del huésped no puede estar vacío.");
        }
        if (estancia.getFecha_desde() == null || estancia.getFecha_desde().trim().isEmpty()) {
            throw new Exception("La fecha desde no puede estar vacía.");
        }
        if (estancia.getFecha_hasta() == null || estancia.getFecha_hasta().trim().isEmpty()) {
            throw new Exception("La fecha hasta no puede estar vacía.");
        }
        // Validar que fecha_hasta sea mayor que fecha_desde
        if (estancia.getFecha_hasta().compareTo(estancia.getFecha_desde()) < 0) {
            throw new Exception("La fecha hasta debe ser mayor o igual que la fecha desde.");
        }
    }
}
