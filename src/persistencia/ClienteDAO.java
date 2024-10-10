package persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Cliente;

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
}
