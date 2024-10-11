package servicios;

import java.util.List;
import entidades.Comentario;
import persistencia.ComentarioDAO;

public class ComentarioService {
    private ComentarioDAO comentarioDAO;

    public ComentarioService() {
        comentarioDAO = new ComentarioDAO();
    }

    public void guardarComentario(Comentario comentario) throws Exception {
        validarComentario(comentario);
        comentarioDAO.guardarComentario(comentario);
    }

    public void modificarComentario(Comentario comentario) throws Exception {
        if (comentario.getId_comentario() <= 0) {
            throw new Exception("El ID del comentario debe ser mayor que 0.");
        }
        validarComentario(comentario);
        comentarioDAO.modificarComentario(comentario);
    }

    public void eliminarComentario(int id_comentario) throws Exception {
        if (id_comentario <= 0) {
            throw new Exception("El ID del comentario a eliminar debe ser mayor que 0.");
        }
        comentarioDAO.eliminarComentario(id_comentario);
    }

    public Comentario buscarComentarioPorId(int id_comentario) throws Exception {
        if (id_comentario <= 0) {
            throw new Exception("El ID del comentario a buscar debe ser mayor que 0.");
        }
        return comentarioDAO.buscarComentarioPorId(id_comentario);
    }

    public List<Comentario> listarComentariosPorCasa(int id_casa) throws Exception {
        if (id_casa <= 0) {
            throw new Exception("El ID de la casa debe ser mayor que 0.");
        }
        return comentarioDAO.listarComentariosPorCasa(id_casa);
    }

    private void validarComentario(Comentario comentario) throws Exception {
        if (comentario == null) {
            throw new Exception("El comentario no puede ser nulo.");
        }
        if (comentario.getId_casa() <= 0) {
            throw new Exception("El ID de la casa debe ser mayor que 0.");
        }
        if (comentario.getComentario() == null || comentario.getComentario().trim().isEmpty()) {
            throw new Exception("El comentario no puede estar vacío.");
        }
        // Aquí puedes añadir más validaciones si es necesario.
    }
}
