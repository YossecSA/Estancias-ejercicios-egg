package servicios;

import java.util.List;
import entidades.Casa;
import persistencia.CasaDAO;

public class CasaService {
    private CasaDAO casaDAO;

    public CasaService() {
        casaDAO = new CasaDAO();
    }

    public void guardarCasa(Casa casa) throws Exception {
        if (casa == null) {
            throw new IllegalArgumentException("La casa no puede ser nula.");
        }
        casaDAO.guardarCasa(casa);
    }

    public int insertarCasa(Casa casa) throws Exception {
        if (casa == null) {
            throw new IllegalArgumentException("La casa no puede ser nula.");
        }
        return casaDAO.insertarCasa(casa);
    }

    public void modificarCasa(Casa casa) throws Exception {
        if (casa == null || casa.getId_casa() <= 0) {
            throw new IllegalArgumentException("La casa no puede ser nula y debe tener un ID válido.");
        }
        casaDAO.modificarCasa(casa);
    }

    public void eliminarCasa(int id_casa) throws Exception {
        if (id_casa <= 0) {
            throw new IllegalArgumentException("ID de casa no puede ser menor o igual a cero.");
        }
        casaDAO.eliminarCasa(id_casa);
    }

    public Casa buscarCasaPorId(int id_casa) throws Exception {
        if (id_casa <= 0) {
            throw new IllegalArgumentException("ID de casa no puede ser menor o igual a cero.");
        }
        return casaDAO.buscarCasaPorId(id_casa);
    }

    public List<Casa> listarCasas() throws Exception {
        return casaDAO.listarCasas();
    }
}
