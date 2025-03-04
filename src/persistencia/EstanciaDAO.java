package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Casa;
import entidades.Cliente;
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

    public List<Estancia> listarEstanciasConClientesYCasas() throws Exception {
        List<Estancia> estancias = new ArrayList<>();
    
        try {
            String sql = "SELECT " +
                    "e.id_estancia, e.nombre_huesped, e.fecha_desde AS estancia_desde, e.fecha_hasta AS estancia_hasta, " +
                    "c.id_cliente, c.nombre AS cliente_nombre, c.pais AS cliente_pais, c.ciudad AS cliente_ciudad, " +
                    "casa.calle AS casa_calle, casa.numero AS casa_numero, casa.ciudad AS casa_ciudad, " +
                    "casa.pais AS casa_pais, casa.tipo_vivienda " +
                    "FROM estancias e " +
                    "INNER JOIN clientes c ON e.id_cliente = c.id_cliente " +
                    "INNER JOIN casas casa ON e.id_casa = casa.id_casa;";
    
            connectarDataBase();
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
    
            while (resultSet.next()) {
                // Crear estancia
                Estancia estancia = new Estancia();
                estancia.setId_estancia(resultSet.getInt("id_estancia"));
                estancia.setNombre_huesped(resultSet.getString("nombre_huesped"));
                estancia.setFecha_desde(resultSet.getString("estancia_desde"));
                estancia.setFecha_hasta(resultSet.getString("estancia_hasta"));
    
                // Crear cliente
                Cliente cliente = new Cliente();
                cliente.setId_cliente(resultSet.getInt("id_cliente"));
                cliente.setNombre(resultSet.getString("cliente_nombre"));
                cliente.setPais(resultSet.getString("cliente_pais"));
                cliente.setCiudad(resultSet.getString("cliente_ciudad"));
    
                // Crear casa
                Casa casa = new Casa();
                casa.setCalle(resultSet.getString("casa_calle"));
                casa.setNumero(resultSet.getInt("casa_numero"));
                casa.setCiudad(resultSet.getString("casa_ciudad"));
                casa.setPais(resultSet.getString("casa_pais"));
                casa.setTipo_vivienda(resultSet.getString("tipo_vivienda"));
    
                // Asociar cliente y casa a la estancia
                estancia.setCliente(cliente);
                estancia.setCasa(casa);
    
                // Añadir la estancia a la lista
                estancias.add(estancia);
            }
    
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            desconectarDataBase();
        }
    
        return estancias;
    }
}