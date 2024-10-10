package persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Estancia;

public class EstanciaDAO extends DAO {
    
    public void guardarEstancia(Estancia estancia) throws Exception {
        try {
            String sql = "INSERT INTO estancia (id_cliente, id_casa, nombre_huesped, fecha_desde, fecha_hasta) VALUES (?, ?, ?, ?, ?)";
            connectarDataBase();
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, estancia.getId_cliente());
            preparedStatement.setInt(2, estancia.getId_casa());
            preparedStatement.setString(3, estancia.getNombre_huesped());
            preparedStatement.setString(4, estancia.getFecha_desde());
            preparedStatement.setString(5, estancia.getFecha_hasta());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        } finally {
            desconectarDataBase();
        }
    }

    public void modificarEstancia(Estancia estancia) throws Exception {
        try {
            String sql = "UPDATE estancia SET id_cliente = ?, id_casa = ?, nombre_huesped = ?, fecha_desde = ?, fecha_hasta = ? WHERE id_estancia = ?";
            connectarDataBase();
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, estancia.getId_cliente());
            preparedStatement.setInt(2, estancia.getId_casa());
            preparedStatement.setString(3, estancia.getNombre_huesped());
            preparedStatement.setString(4, estancia.getFecha_desde());
            preparedStatement.setString(5, estancia.getFecha_hasta());
            preparedStatement.setInt(6, estancia.getId_estancia());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        } finally {
            desconectarDataBase();
        }
    }

    public void eliminarEstancia(int id_estancia) throws Exception {
        try {
            String sql = "DELETE FROM estancia WHERE id_estancia = ?";
            connectarDataBase();
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, id_estancia);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        } finally {
            desconectarDataBase();
        }
    }

    public Estancia buscarEstanciaPorId(int id_estancia) throws Exception {
        try {
            String sql = "SELECT * FROM estancia WHERE id_estancia = ?";
            connectarDataBase();
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, id_estancia);
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                Estancia estancia = new Estancia(
                    resultSet.getInt("id_estancia"),
                    resultSet.getInt("id_cliente"),
                    resultSet.getInt("id_casa"),
                    resultSet.getString("nombre_huesped"),
                    resultSet.getString("fecha_desde"),
                    resultSet.getString("fecha_hasta")
                );
                return estancia;
            }
            return null;
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        } finally {
            desconectarDataBase();
        }
    }

    public List<Estancia> listarEstanciasPorCliente(int id_cliente) throws Exception {
        try {
            String sql = "SELECT * FROM estancia WHERE id_cliente = ?";
            connectarDataBase();
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, id_cliente);
            resultSet = preparedStatement.executeQuery();
            
            List<Estancia> estancias = new ArrayList<>();
            while (resultSet.next()) {
                Estancia estancia = new Estancia(
                    resultSet.getInt("id_estancia"),
                    resultSet.getInt("id_cliente"),
                    resultSet.getInt("id_casa"),
                    resultSet.getString("nombre_huesped"),
                    resultSet.getString("fecha_desde"),
                    resultSet.getString("fecha_hasta")
                );
                estancias.add(estancia);
            }
            return estancias;
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        } finally {
            desconectarDataBase();
        }
    }
}