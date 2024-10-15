package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Casa;
import entidades.Cliente;
import entidades.Estancia;

public class ClienteDAO extends DAO {
    public void guardarCliente(Cliente cliente) throws Exception {
        try {
            String sql = "INSERT INTO cliente (nombre, calle, numero, codigo_postal, ciudad, pais, email) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";
            connectarDataBase();
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, cliente.getNombre());
            preparedStatement.setString(2, cliente.getCalle());
            preparedStatement.setString(3, cliente.getNumero());
            preparedStatement.setString(4, cliente.getCodigo_postal());
            preparedStatement.setString(5, cliente.getCiudad());
            preparedStatement.setString(6, cliente.getPais());
            preparedStatement.setString(7, cliente.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        } finally {
            desconectarDataBase();
        }
    }

    public void modificarCliente(Cliente cliente) throws Exception {
        try {
            String sql = "UPDATE cliente SET nombre = ?, calle = ?, numero = ?, codigo_postal = ?, ciudad = ?, " +
                        "pais = ?, email = ? WHERE id_cliente = ?";
            connectarDataBase();
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, cliente.getNombre());
            preparedStatement.setString(2, cliente.getCalle());
            preparedStatement.setString(3, cliente.getNumero());
            preparedStatement.setString(4, cliente.getCodigo_postal());
            preparedStatement.setString(5, cliente.getCiudad());
            preparedStatement.setString(6, cliente.getPais());
            preparedStatement.setString(7, cliente.getEmail());
            preparedStatement.setInt(8, cliente.getId_cliente());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        } finally {
            desconectarDataBase();
        }
    }

    public void eliminarCliente(int id_cliente) throws Exception {
        try {
            String sql = "DELETE FROM cliente WHERE id_cliente = ?";
            connectarDataBase();
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, id_cliente);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        } finally {
            desconectarDataBase();
        }
    }

    public Cliente buscarClientePorId(int id_cliente) throws Exception {
        try {
            String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
            connectarDataBase();
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, id_cliente);
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_cliente(resultSet.getInt("id_cliente"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setCalle(resultSet.getString("calle"));
                cliente.setNumero(resultSet.getString("numero"));
                cliente.setCodigo_postal(resultSet.getString("codigo_postal"));
                cliente.setCiudad(resultSet.getString("ciudad"));
                cliente.setPais(resultSet.getString("pais"));
                cliente.setEmail(resultSet.getString("email"));
                return cliente;
            }
            return null;
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        } finally {
            desconectarDataBase();
        }
    }

    public List<Cliente> listarClientes() throws Exception {
        try {
            String sql = "SELECT * FROM cliente";
            connectarDataBase();
            statement = conexion.createStatement();
            resultSet = statement.executeQuery(sql);
            
            List<Cliente> clientes = new ArrayList<>();
            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_cliente(resultSet.getInt("id_cliente"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setCalle(resultSet.getString("calle"));
                cliente.setNumero(resultSet.getString("numero"));
                cliente.setCodigo_postal(resultSet.getString("codigo_postal"));
                cliente.setCiudad(resultSet.getString("ciudad"));
                cliente.setPais(resultSet.getString("pais"));
                cliente.setEmail(resultSet.getString("email"));
                clientes.add(cliente);
            }
            return clientes;
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        } finally {
            desconectarDataBase();
        }
    }

    public List<Cliente> listarClientesConEstancia() throws Exception {
        List<Cliente> clientes = new ArrayList<>();

        try {
            String sql = "SELECT " +
                    "e.id_estancia, e.nombre_huesped, e.fecha_desde AS estancia_desde, e.fecha_hasta AS estancia_hasta, " +
                    "c.id_cliente, c.nombre AS cliente_nombre, c.ciudad AS cliente_ciudad, c.email AS cliente_email, " +
                    "casa.calle AS casa_calle, casa.numero AS casa_numero, casa.ciudad AS casa_ciudad, casa.tipo_vivienda " +
                    "FROM estancias e " +
                    "INNER JOIN clientes c ON e.id_cliente = c.id_cliente " +
                    "INNER JOIN casas casa ON e.id_casa = casa.id_casa;";

            connectarDataBase();
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Crear cliente
                Cliente cliente = new Cliente();
                cliente.setId_cliente(resultSet.getInt("id_cliente"));
                cliente.setNombre(resultSet.getString("cliente_nombre"));
                cliente.setCiudad(resultSet.getString("cliente_ciudad"));
                cliente.setEmail(resultSet.getString("cliente_email"));

                // Crear estancia
                Estancia estancia = new Estancia();
                estancia.setId_estancia(resultSet.getInt("id_estancia"));
                estancia.setNombre_huesped(resultSet.getString("nombre_huesped"));
                estancia.setFecha_desde(resultSet.getString("estancia_desde"));
                estancia.setFecha_hasta(resultSet.getString("estancia_hasta"));

                // Crear casa
                Casa casa = new Casa();
                casa.setCalle(resultSet.getString("casa_calle"));
                casa.setNumero(resultSet.getInt("casa_numero"));
                casa.setCiudad(resultSet.getString("casa_ciudad"));
                casa.setTipo_vivienda(resultSet.getString("tipo_vivienda"));

                // Asociar estancia y casa al cliente
                estancia.setCasa(casa);
                cliente.setEstancia(estancia);

                // Añadir cliente a la lista
                clientes.add(cliente);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            desconectarDataBase();
        }

        return clientes;
    }
    
}
