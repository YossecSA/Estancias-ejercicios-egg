package servicios;

import persistencia.ClienteDAO;
import entidades.Cliente;

import java.util.List;

public class ClienteService {
    private final ClienteDAO clienteDAO;

    public ClienteService() {
        this.clienteDAO = new ClienteDAO();
    }

    public void guardarCliente(Cliente cliente) throws Exception {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }
        // Puedes agregar validaciones adicionales aquí
        clienteDAO.guardarCliente(cliente);
    }

    


    public void modificarCliente(Cliente cliente) throws Exception {
        if (cliente == null || cliente.getId_cliente() <= 0) {
            throw new IllegalArgumentException("Cliente inválido");
        }
        clienteDAO.modificarCliente(cliente);
    }

    public void eliminarCliente(int id_cliente) throws Exception {
        if (id_cliente <= 0) {
            throw new IllegalArgumentException("ID de cliente inválido");
        }
        clienteDAO.eliminarCliente(id_cliente);
    }

    public Cliente buscarClientePorId(int id_cliente) throws Exception {
        if (id_cliente <= 0) {
            throw new IllegalArgumentException("ID de cliente inválido");
        }
        return clienteDAO.buscarClientePorId(id_cliente);
    }

    public List<Cliente> listarClientes() throws Exception {
        return clienteDAO.listarClientes();
    }
}
