package persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entidades.Familia;

public class FamiliaDAO extends DAO {

    public void guardarFamilia(Familia familia) throws Exception {
        try {
            String sql = "INSERT INTO familia (nombre, edad_minima, edad_maxima, num_hijos, email) VALUES (?, ?, ?, ?, ?)";
            connectarDataBase();
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, familia.getNombre());
            preparedStatement.setInt(2, familia.getEdad_minima());
            preparedStatement.setInt(3, familia.getEdad_maxima());
            preparedStatement.setInt(4, familia.getNum_hijos());
            preparedStatement.setString(5, familia.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        } finally {
            desconectarDataBase();
        }
    }

    public void modificarFamilia(Familia familia) throws Exception {
        try {
            String sql = "UPDATE familia SET nombre = ?, edad_minima = ?, edad_maxima = ?, num_hijos = ?, email = ? WHERE id = ?";
            connectarDataBase();
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, familia.getNombre());
            preparedStatement.setInt(2, familia.getEdad_minima());
            preparedStatement.setInt(3, familia.getEdad_maxima());
            preparedStatement.setInt(4, familia.getNum_hijos());
            preparedStatement.setString(5, familia.getEmail());
            preparedStatement.setInt(6, familia.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        } finally {
            desconectarDataBase();
        }
    }

    public void eliminarFamilia(int id) throws Exception {
        try {
            String sql = "DELETE FROM familia WHERE id = ?";
            connectarDataBase();
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        } finally {
            desconectarDataBase();
        }
    }

    public Familia buscarFamiliaPorId(int id) throws Exception {
        try {
            String sql = "SELECT * FROM familia WHERE id = ?";
            connectarDataBase();
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                Familia familia = new Familia(
                    resultSet.getInt("id"),
                    resultSet.getString("nombre"),
                    resultSet.getInt("edad_minima"),
                    resultSet.getInt("edad_maxima"),
                    resultSet.getInt("num_hijos"),
                    resultSet.getString("email")
                );
                return familia;
            }
            return null;
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        } finally {
            desconectarDataBase();
        }
    }

    public List<Familia> listarFamilias() throws Exception {
        try {
            String sql = "SELECT * FROM familia";
            connectarDataBase();
            preparedStatement = conexion.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            
            List<Familia> familias = new ArrayList<>();
            while (resultSet.next()) {
                Familia familia = new Familia(
                    resultSet.getInt("id"),
                    resultSet.getString("nombre"),
                    resultSet.getInt("edad_minima"),
                    resultSet.getInt("edad_maxima"),
                    resultSet.getInt("num_hijos"),
                    resultSet.getString("email")
                );
                familias.add(familia);
            }
            return familias;
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        } finally {
            desconectarDataBase();
        }
    }

    public List<Familia> listarFamiliasEjercicio1() throws Exception {
        try {
            String sql = "SELECT id_familia, nombre, num_hijos, edad_maxima FROM familias WHERE familias.num_hijos >= 3 AND familias.edad_maxima < 10;";
            connectarDataBase();
            preparedStatement = conexion.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            
            List<Familia> familias = new ArrayList<>();
            while (resultSet.next()) {
                Familia familia = new Familia(
                    resultSet.getInt("id_familia"), 
                    resultSet.getString("nombre"),
                    resultSet.getInt("num_hijos"),
                    resultSet.getInt("edad_maxima") 
                );
                familias.add(familia);
            }
            return familias;
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        } finally {
            desconectarDataBase();
        }
    }

}
