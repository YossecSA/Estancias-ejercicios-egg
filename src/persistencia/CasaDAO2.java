package persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Casa;


public class CasaDAO2 extends DAO {
public int insertarCasa(Casa casa) throws Exception {
        if (casa == null) {
            throw new Exception("La casa no puede ser nula");
        }
        
        String sql = "INSERT INTO casas (calle, numero, codigo_postal, ciudad, pais, fecha_desde, fecha_hasta, tiempo_minimo, tiempo_maximo, precio_habitacion, tipo_vivienda) "
                        + "VALUES ('" + casa.getCalle() + "', " 
                        + casa.getNumero() + ", '"
                        + casa.getCodigo_postal() + "', '"
                        + casa.getCiudad() + "', '"
                        + casa.getPais() + "', '"
                        + casa.getFecha_desde() + "', '"
                        + casa.getFecha_hasta() + "', "
                        + casa.getTiempo_minimo() + ", "
                        + casa.getTiempo_maximo() + ", "
                        + casa.getPrecio_habitacion() + ", '"
                        + casa.getTipo_vivienda() + "')";
            
        return insertarReturnID(sql); 
    }

    public List<Casa> listarTodasLasCasas() throws Exception {
        String sql = "SELECT * FROM casas";
        consultarDataBase(sql);
        
        List<Casa> casas = new ArrayList<>();
        while (resultSet.next()) {
            Casa casa = new Casa();
            casa.setId_casa(resultSet.getInt("id")); // Asegúrate de tener un método setId
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
    }

    public Casa buscarCasaPorId(int id) throws Exception {
        String sql = "SELECT * FROM casas WHERE id = " + id;
        consultarDataBase(sql);

        if (resultSet.next()) {
            Casa casa = new Casa();

            casa.setId_casa(resultSet.getInt("id")); 
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
    }

    public void actualizarCasa(Casa casa) throws Exception {
        if (casa == null) {
            throw new Exception("La casa no puede ser nula");
        }

        String sql = "UPDATE casas SET "
                        + "calle = '" + casa.getCalle() + "', "
                        + "numero = " + casa.getNumero() + ", "
                        + "codigo_postal = '" + casa.getCodigo_postal() + "', "
                        + "ciudad = '" + casa.getCiudad() + "', "
                        + "pais = '" + casa.getPais() + "', "
                        + "fecha_desde = '" + casa.getFecha_desde() + "', "
                        + "fecha_hasta = '" + casa.getFecha_hasta() + "', "
                        + "tiempo_minimo = " + casa.getTiempo_minimo() + ", "
                        + "tiempo_maximo = " + casa.getTiempo_maximo() + ", "
                        + "precio_habitacion = " + casa.getPrecio_habitacion() + ", "
                        + "tipo_vivienda = '" + casa.getTipo_vivienda() + "' "
                        + "WHERE id = " + casa.getId_casa();

        insertarModificarEliminarDataBase(sql);
    }

    public void eliminarCasa(int id) throws Exception {
        String sql = "DELETE FROM casas WHERE id = " + id;
        insertarModificarEliminarDataBase(sql);
    }

    public List<Casa> listarCasasDisponibles(String pais, String fechaHasta, String fechaDesde) throws Exception {
        try {
            String sql = "SELECT c.id_casa, c.calle, c.numero, c.ciudad, c.pais, e.fecha_desde, e.fecha_hasta " +
                        "FROM casas c LEFT JOIN estancias e ON e.id_casa = c.id_casa " +
                        "WHERE c.pais = ? AND (e.fecha_desde IS NULL OR e.fecha_hasta < ? OR e.fecha_desde > ?)";
    
            connectarDataBase();
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, pais);
            preparedStatement.setString(2, fechaHasta);
            preparedStatement.setString(3, fechaDesde);
    
            resultSet = preparedStatement.executeQuery();
    
            List<Casa> casas = new ArrayList<>();
            while (resultSet.next()) {
                Casa casa = new Casa(
                    resultSet.getInt("id_casa"),
                    resultSet.getString("calle"),
                    resultSet.getInt("numero"),
                    resultSet.getString("ciudad"),
                    resultSet.getString("pais"),
                    resultSet.getString("fecha_desde"),
                    resultSet.getString("fecha_hasta")
                );
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