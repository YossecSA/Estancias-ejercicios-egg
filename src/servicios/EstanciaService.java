package servicios;

import java.util.List;
import entidades.Estancia;
import persistencia.EstanciaDAO;

public class EstanciaService {
    private EstanciaDAO estanciaDAO;

    public EstanciaService() {
        estanciaDAO = new EstanciaDAO();
    }

    public void guardarEstancia(Estancia estancia) throws Exception {
        validarEstancia(estancia);
        estanciaDAO.guardarEstancia(estancia);
    }

    public void modificarEstancia(Estancia estancia) throws Exception {
        if (estancia.getId_estancia() <= 0) {
            throw new Exception("El ID de la estancia debe ser mayor que 0.");
        }
        validarEstancia(estancia);
        estanciaDAO.modificarEstancia(estancia);
    }

    public void eliminarEstancia(int id_estancia) throws Exception {
        if (id_estancia <= 0) {
            throw new Exception("El ID de la estancia a eliminar debe ser mayor que 0.");
        }
        estanciaDAO.eliminarEstancia(id_estancia);
    }

    public Estancia buscarEstanciaPorId(int id_estancia) throws Exception {
        if (id_estancia <= 0) {
            throw new Exception("El ID de la estancia a buscar debe ser mayor que 0.");
        }
        return estanciaDAO.buscarEstanciaPorId(id_estancia);
    }

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
