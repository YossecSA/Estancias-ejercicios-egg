package servicios;

import java.util.List;

import entidades.Comentario;
import persistencia.ComentarioDAO;

public class ComentarioService {
    private ComentarioDAO comentarioDAO;

    public ComentarioService() {
        comentarioDAO = new ComentarioDAO();
    }

    public void guardarComentario(int id_casa, String comentario) throws Exception {

        validarIdCasa(id_casa);
        validarComentarioTexto(comentario);
        Comentario _comentario = new Comentario(id_casa, comentario);

        comentarioDAO.guardarComentario(_comentario);
    }

    public void modificarComentario(int id_comentario, int id_casa, String comentario) throws Exception {

        validarIdComentario(id_comentario);
        validarIdCasa(id_casa);
        validarComentarioTexto(comentario);

        Comentario _comentario = new Comentario(id_comentario,id_casa, comentario);
        comentarioDAO.modificarComentario(_comentario);
    }

    public void eliminarComentario(int id_comentario) throws Exception {
        validarIdComentario(id_comentario);

        comentarioDAO.eliminarComentario(id_comentario);
    }

    public Comentario buscarComentarioPorId(int id_comentario) throws Exception {
        validarIdComentario(id_comentario);
        return comentarioDAO.buscarComentarioPorId(id_comentario);
    }

    public List<Comentario> listarComentariosPorCasa(int id_casa) throws Exception {
        validarIdCasa(id_casa);
        return comentarioDAO.listarComentariosPorCasa(id_casa);
    }

    private void validarIdComentario(int id_comentario) throws Exception {
        if(id_comentario < 0){
            throw new Exception("El id comentario no puede ser nulo.");
        }
    }

    private void validarIdCasa(int id_casa) throws Exception {
        if (id_casa <= 0) {
            throw new Exception("El ID de la casa debe ser mayor que 0.");
        }
    }

    private void validarComentarioTexto(String comentario) throws Exception {
        if (comentario == null || comentario.trim().isEmpty()) {
            throw new Exception("El comentario no puede estar vacío.");
        }
    }
}
