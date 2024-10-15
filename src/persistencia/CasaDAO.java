package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Casa;

public class CasaDAO extends DAO {
    
    public void guardarCasa(Casa casa) throws Exception {
        try {
            String sql = "INSERT INTO casa (calle, numero, codigo_postal, ciudad, pais, fecha_desde, fecha_hasta, " +
                        "tiempo_minimo, tiempo_maximo, precio_habitacion, tipo_vivienda) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            connectarDataBase();
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, casa.getCalle());
            preparedStatement.setInt(2, casa.getNumero());
            preparedStatement.setString(3, casa.getCodigo_postal());
            preparedStatement.setString(4, casa.getCiudad());
            preparedStatement.setString(5, casa.getPais());
            preparedStatement.setString(6, casa.getFecha_desde());
            preparedStatement.setString(7, casa.getFecha_hasta());
            preparedStatement.setInt(8, casa.getTiempo_minimo());
            preparedStatement.setInt(9, casa.getTiempo_maximo());
            preparedStatement.setDouble(10, casa.getPrecio_habitacion());
            preparedStatement.setString(11, casa.getTipo_vivienda());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        } finally {
            desconectarDataBase();
        }
    }

    public int insertarCasa(Casa casa) throws Exception {
        int idGenerado = 0;
        String sql = "INSERT INTO casa (calle, numero, codigo_postal, ciudad, pais, fecha_desde, fecha_hasta, tiempo_minimo, tiempo_maximo, precio_habitacion, tipo_vivienda) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            connectarDataBase();
            PreparedStatement ps = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, casa.getCalle());
            ps.setInt(2, casa.getNumero());
            ps.setString(3, casa.getCodigo_postal());
            ps.setString(4, casa.getCiudad());
            ps.setString(5, casa.getPais());
            ps.setString(6, casa.getFecha_desde());
            ps.setString(7, casa.getFecha_hasta());
            ps.setInt(8, casa.getTiempo_minimo());
            ps.setInt(9, casa.getTiempo_maximo());
            ps.setDouble(10, casa.getPrecio_habitacion());
            ps.setString(11, casa.getTipo_vivienda());

            ps.executeUpdate();
            
            // Obtener el ID autogenerado
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                idGenerado = generatedKeys.getInt(1);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            throw new Exception("Error al insertar casa: " + ex.getMessage());
        } finally {
            desconectarDataBase();
        }
        return idGenerado; // Retorna el ID generado
    }

    public void modificarCasa(Casa casa) throws Exception {
        try {
            String sql = "UPDATE casa SET calle = ?, numero = ?, codigo_postal = ?, ciudad = ?, pais = ?, " +
                        "fecha_desde = ?, fecha_hasta = ?, tiempo_minimo = ?, tiempo_maximo = ?, " +
                        "precio_habitacion = ?, tipo_vivienda = ? WHERE id_casa = ?";
            connectarDataBase();
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, casa.getCalle());
            preparedStatement.setInt(2, casa.getNumero());
            preparedStatement.setString(3, casa.getCodigo_postal());
            preparedStatement.setString(4, casa.getCiudad());
            preparedStatement.setString(5, casa.getPais());
            preparedStatement.setString(6, casa.getFecha_desde());
            preparedStatement.setString(7, casa.getFecha_hasta());
            preparedStatement.setInt(8, casa.getTiempo_minimo());
            preparedStatement.setInt(9, casa.getTiempo_maximo());
            preparedStatement.setDouble(10, casa.getPrecio_habitacion());
            preparedStatement.setString(11, casa.getTipo_vivienda());
            preparedStatement.setInt(12, casa.getId_casa());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        } finally {
            desconectarDataBase();
        }
    }

    public void eliminarCasa(int id_casa) throws Exception {
        try {
            String sql = "DELETE FROM casa WHERE id_casa = ?";
            connectarDataBase();
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, id_casa);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        } finally {
            desconectarDataBase();
        }
    }

    public Casa buscarCasaPorId(int id_casa) throws Exception {
        try {
            String sql = "SELECT * FROM casa WHERE id_casa = ?";
            connectarDataBase();
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, id_casa);
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                Casa casa = new Casa();
                casa.setId_casa(resultSet.getInt("id_casa"));
                casa.setCalle(resultSet.getString("calle"));
                casa.setNumero(resultSet.getInt("numero"));
                casa.setCodigo_postal(resultSet.getString("codigo_postal"));
                casa.setCiudad(resultSet.getString("ciudad"));
                casa.setPais(resultSet.getString("pais"));
                casa.setFecha_desde(resultSet.getString("fecha_desde"));
                casa.setFecha_hasta(resultSet.getString("fecha_hasta"));
                casa.setTiempo_minimo(resultSet.getInt("tiempo_minimo"));
                casa.setTiempo_maximo(resultSet.getInt("tiempo_maximo"));
                casa.setPrecio_habitacion(resultSet.getDouble("precio_habitacion"));
                casa.setTipo_vivienda(resultSet.getString("tipo_vivienda"));
                return casa;
            }
            return null;
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        } finally {
            desconectarDataBase();
        }
    }

    public List<Casa> listarCasas() throws Exception {
        try {
            String sql = "SELECT * FROM casa";
            connectarDataBase();
            statement = conexion.createStatement();
            resultSet = statement.executeQuery(sql);
            
            List<Casa> casas = new ArrayList<>();
            while (resultSet.next()) {
                Casa casa = new Casa();
                casa.setId_casa(resultSet.getInt("id_casa"));
                casa.setCalle(resultSet.getString("calle"));
                casa.setNumero(resultSet.getInt("numero"));
                casa.setCodigo_postal(resultSet.getString("codigo_postal"));
                casa.setCiudad(resultSet.getString("ciudad"));
                casa.setPais(resultSet.getString("pais"));
                casa.setFecha_desde(resultSet.getString("fecha_desde"));
                casa.setFecha_hasta(resultSet.getString("fecha_hasta"));
                casa.setTiempo_minimo(resultSet.getInt("tiempo_minimo"));
                casa.setTiempo_maximo(resultSet.getInt("tiempo_maximo"));
                casa.setPrecio_habitacion(resultSet.getDouble("precio_habitacion"));
                casa.setTipo_vivienda(resultSet.getString("tipo_vivienda"));
                casas.add(casa);
            }
            return casas;
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        } finally {
            desconectarDataBase();
        }
    }

}
