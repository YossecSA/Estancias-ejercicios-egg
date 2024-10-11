package servicios;

import java.util.List;
import entidades.Familia;
import persistencia.FamiliaDAO;

public class FamiliaService {
    private FamiliaDAO familiaDAO;

    public FamiliaService() {
        familiaDAO = new FamiliaDAO();
    }

    public void guardarFamilia(Familia familia) throws Exception {
        validarFamilia(familia);
        familiaDAO.guardarFamilia(familia);
    }

    public void modificarFamilia(Familia familia) throws Exception {
        if (familia.getId() <= 0) {
            throw new Exception("El ID de la familia debe ser mayor que 0.");
        }
        validarFamilia(familia);
        familiaDAO.modificarFamilia(familia);
    }

    public void eliminarFamilia(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("El ID de la familia a eliminar debe ser mayor que 0.");
        }
        familiaDAO.eliminarFamilia(id);
    }

    public Familia buscarFamiliaPorId(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("El ID de la familia a buscar debe ser mayor que 0.");
        }
        return familiaDAO.buscarFamiliaPorId(id);
    }

    public List<Familia> listarFamilias() throws Exception {
        return familiaDAO.listarFamilias();
    }

    private void validarFamilia(Familia familia) throws Exception {
        if (familia == null) {
            throw new Exception("La familia no puede ser nula.");
        }
        if (familia.getNombre() == null || familia.getNombre().trim().isEmpty() || familia.getNombre().length() > 50) {
            throw new Exception("El nombre de la familia no puede estar vacío y no debe exceder los 50 caracteres.");
        }
        if (familia.getEdad_minima() < 0) {
            throw new Exception("La edad mínima no puede ser negativa.");
        }
        if (familia.getEdad_maxima() < familia.getEdad_minima()) {
            throw new Exception("La edad máxima debe ser mayor o igual que la edad mínima.");
        }
        if (familia.getNum_hijos() < 0) {
            throw new Exception("El número de hijos no puede ser negativo.");
        }
        if (familia.getEmail() != null && !familia.getEmail().isEmpty()) {
            if (familia.getEmail().length() > 100) {
                throw new Exception("El email no debe exceder los 100 caracteres.");
            }
            if (!familia.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                throw new Exception("El formato del email es inválido.");
            }
        }
    }
}
