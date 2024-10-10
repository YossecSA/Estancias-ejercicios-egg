package persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Comentario;

public class ComentarioDAO extends DAO {
    
    public void guardarComentario(Comentario comentario) throws Exception {
        try {
            String sql = "INSERT INTO comentario (id_casa, comentario) VALUES (?, ?)";
            connectarDataBase();
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, comentario.getId_casa());
            preparedStatement.setString(2, comentario.getComentario());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        } finally {
            desconectarDataBase();
        }
    }

    public void modificarComentario(Comentario comentario) throws Exception {
        try {
            String sql = "UPDATE comentario SET id_casa = ?, comentario = ? WHERE id_comentario = ?";
            connectarDataBase();
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, comentario.getId_casa());
            preparedStatement.setString(2, comentario.getComentario());
            preparedStatement.setInt(3, comentario.getId_comentario());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        } finally {
            desconectarDataBase();
        }
    }

    public void eliminarComentario(int id_comentario) throws Exception {
        try {
            String sql = "DELETE FROM comentario WHERE id_comentario = ?";
            connectarDataBase();
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, id_comentario);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        } finally {
            desconectarDataBase();
        }
    }

    public Comentario buscarComentarioPorId(int id_comentario) throws Exception {
        try {
            String sql = "SELECT * FROM comentario WHERE id_comentario = ?";
            connectarDataBase();
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, id_comentario);
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                Comentario comentario = new Comentario(
                    resultSet.getInt("id_comentario"),
                    resultSet.getInt("id_casa"),
                    resultSet.getString("comentario")
                );
                return comentario;
            }
            return null;
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        } finally {
            desconectarDataBase();
        }
    }

    public List<Comentario> listarComentariosPorCasa(int id_casa) throws Exception {
        try {
            String sql = "SELECT * FROM comentario WHERE id_casa = ?";
            connectarDataBase();
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, id_casa);
            resultSet = preparedStatement.executeQuery();
            
            List<Comentario> comentarios = new ArrayList<>();
            while (resultSet.next()) {
                Comentario comentario = new Comentario(
                    resultSet.getInt("id_comentario"),
                    resultSet.getInt("id_casa"),
                    resultSet.getString("comentario")
                );
                comentarios.add(comentario);
            }
            return comentarios;
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        } finally {
            desconectarDataBase();
        }
    }
}
