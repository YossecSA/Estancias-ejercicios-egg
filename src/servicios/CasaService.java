package servicios;

import java.util.List;
import entidades.Casa;
import persistencia.CasaDAO;

public class CasaService {
    private CasaDAO casaDAO;

    // Constructor que inicializa el DAO para interactuar con la base de datos.
    public CasaService() {
        casaDAO = new CasaDAO();
    }

    /**
     * Método para guardar una nueva casa en la base de datos.
     *
     * @param calle           La calle de la casa.
     * @param numero          El número de la casa.
     * @param codigo_postal   El código postal de la casa.
     * @param ciudad          La ciudad donde se encuentra la casa.
     * @param pais            El país donde se encuentra la casa.
     * @param desde           La fecha de inicio de disponibilidad.
     * @param hasta           La fecha de fin de disponibilidad.
     * @param tiempo_minimo   El tiempo mínimo de estancia.
     * @param tiempo_maximo   El tiempo máximo de estancia.
     * @param precio_habitacion El precio de la habitación.
     * @param tipo_vivienda   El tipo de vivienda (por ejemplo, apartamento, casa).
     * @throws Exception Si ocurre un error durante el guardado.
     */
    public void guardarCasa(String calle, int numero, String codigo_postal, String ciudad, String pais, String desde, String hasta, int tiempo_minimo, int tiempo_maximo, Double precio_habitacion, String tipo_vivienda) throws Exception {
        // Validar los datos de la casa antes de guardarla.
        validarCasa(calle, numero, codigo_postal, ciudad, pais, desde, hasta, tiempo_minimo, tiempo_maximo, precio_habitacion, tipo_vivienda);
        
        // Crear una nueva instancia de Casa con los datos proporcionados.
        Casa nuevaCasa = new Casa(calle, numero, codigo_postal, ciudad, pais, desde, hasta, tiempo_minimo, tiempo_maximo, precio_habitacion, tipo_vivienda);
        
        // Guardar la nueva casa utilizando el DAO.
        casaDAO.guardarCasa(nuevaCasa);
    }

    /**
     * Método para insertar una nueva casa y retornar su ID.
     *
     * @param calle           La calle de la casa.
     * @param numero          El número de la casa.
     * @param codigo_postal   El código postal de la casa.
     * @param ciudad          La ciudad donde se encuentra la casa.
     * @param pais            El país donde se encuentra la casa.
     * @param desde           La fecha de inicio de disponibilidad.
     * @param hasta           La fecha de fin de disponibilidad.
     * @param tiempo_minimo   El tiempo mínimo de estancia.
     * @param tiempo_maximo   El tiempo máximo de estancia.
     * @param precio_habitacion El precio de la habitación.
     * @param tipo_vivienda   El tipo de vivienda.
     * @return El ID de la casa insertada.
     * @throws Exception Si ocurre un error durante la inserción.
     */
    public int insertarCasa(String calle, int numero, String codigo_postal, String ciudad, String pais, String desde, String hasta, int tiempo_minimo, int tiempo_maximo, Double precio_habitacion, String tipo_vivienda) throws Exception {
        // Validar los datos de la casa antes de insertarla.
        validarCasa(calle, numero, codigo_postal, ciudad, pais, desde, hasta, tiempo_minimo, tiempo_maximo, precio_habitacion, tipo_vivienda);
        
        // Crear una nueva instancia de Casa.
        Casa nuevaCasa = new Casa(calle, numero, codigo_postal, ciudad, pais, desde, hasta, tiempo_minimo, tiempo_maximo, precio_habitacion, tipo_vivienda);
        
        // Insertar la casa en la base de datos y retornar el ID.
        return casaDAO.insertarCasa(nuevaCasa);
    }

    /**
     * Método para modificar los datos de una casa existente.
     *
     * @param id_casa        El ID de la casa a modificar.
     * @param calle          La nueva calle de la casa.
     * @param numero         El nuevo número de la casa.
     * @param codigo_postal  El nuevo código postal de la casa.
     * @param ciudad         La nueva ciudad donde se encuentra la casa.
     * @param pais           El nuevo país donde se encuentra la casa.
     * @param desde          La nueva fecha de inicio de disponibilidad.
     * @param hasta          La nueva fecha de fin de disponibilidad.
     * @param tiempo_minimo  El nuevo tiempo mínimo de estancia.
     * @param tiempo_maximo  El nuevo tiempo máximo de estancia.
     * @param precio_habitacion El nuevo precio de la habitación.
     * @param tipo_vivienda  El nuevo tipo de vivienda.
     * @throws Exception Si ocurre un error durante la modificación.
     */
    public void modificarCasa(int id_casa, String calle, int numero, String codigo_postal, String ciudad, String pais, String desde, String hasta, int tiempo_minimo, int tiempo_maximo, Double precio_habitacion, String tipo_vivienda) throws Exception {
        // Validar el ID de la casa antes de modificarla.
        if (id_casa <= 0) {
            throw new IllegalArgumentException("ID de casa no puede ser menor o igual a cero.");
        }
        
        // Crear una nueva instancia de Casa con los nuevos datos.
        Casa casaModificada = new Casa(id_casa, calle, numero, codigo_postal, ciudad, pais, desde, hasta, tiempo_minimo, tiempo_maximo, precio_habitacion, tipo_vivienda);
        
        // Modificar la casa en la base de datos utilizando el DAO.
        casaDAO.modificarCasa(casaModificada);
    }

    /**
     * Método para eliminar una casa existente por su ID.
     *
     * @param id_casa El ID de la casa a eliminar.
     * @throws Exception Si ocurre un error durante la eliminación.
     */
    public void eliminarCasa(int id_casa) throws Exception {
        // Validar el ID de la casa antes de eliminarla.
        if (id_casa <= 0) {
            throw new IllegalArgumentException("ID de casa no puede ser menor o igual a cero.");
        }
        // Eliminar la casa de la base de datos utilizando el DAO.
        casaDAO.eliminarCasa(id_casa);
    }

    /**
     * Método para buscar una casa por su ID.
     *
     * @param id_casa El ID de la casa a buscar.
     * @return La casa correspondiente al ID proporcionado.
     * @throws Exception Si ocurre un error durante la búsqueda.
     */
    public Casa buscarCasaPorId(int id_casa) throws Exception {
        // Validar el ID de la casa antes de buscarla.
        if (id_casa <= 0) {
            throw new IllegalArgumentException("ID de casa no puede ser menor o igual a cero.");
        }
        // Buscar la casa en la base de datos utilizando el DAO.
        return casaDAO.buscarCasaPorId(id_casa);
    }

    /**
     * Método para listar todas las casas en la base de datos.
     *
     * @return Una lista de todas las casas.
     * @throws Exception Si ocurre un error durante la búsqueda.
     */
    public List<Casa> listarCasas() throws Exception {
        // Listar todas las casas utilizando el DAO.
        return casaDAO.listarCasas();
    }

    /**
     * Método para validar los datos de una casa.
     *
     * @param calle           La calle de la casa.
     * @param numero          El número de la casa.
     * @param codigo_postal   El código postal de la casa.
     * @param ciudad          La ciudad donde se encuentra la casa.
     * @param pais            El país donde se encuentra la casa.
     * @param desde           La fecha de inicio de disponibilidad.
     * @param hasta           La fecha de fin de disponibilidad.
     * @param tiempo_minimo   El tiempo mínimo de estancia.
     * @param tiempo_maximo   El tiempo máximo de estancia.
     * @param precio_habitacion El precio de la habitación.
     * @param tipo_vivienda   El tipo de vivienda.
     * @throws Exception Si alguno de los datos no es válido.
     */
    private void validarCasa(String calle, int numero, String codigo_postal, String ciudad, String pais, String desde, String hasta, int tiempo_minimo, int tiempo_maximo, Double precio_habitacion, String tipo_vivienda) throws Exception {
        if (calle == null || calle.trim().isEmpty()) {
            throw new Exception("La calle no puede estar vacía.");
        }
        if (numero <= 0) {
            throw new Exception("El número debe ser mayor que 0.");
        }
        if (codigo_postal == null || codigo_postal.trim().isEmpty()) {
            throw new Exception("El código postal no puede estar vacío.");
        }
        if (ciudad == null || ciudad.trim().isEmpty()) {
            throw new Exception("La ciudad no puede estar vacía.");
        }
        if (pais == null || pais.trim().isEmpty()) {
            throw new Exception("El país no puede estar vacío.");
        }
        if (desde == null || desde.trim().isEmpty()) {
            throw new Exception("La fecha desde no puede estar vacía.");
        }
        if (hasta == null || hasta.trim().isEmpty()) {
            throw new Exception("La fecha hasta no puede estar vacía.");
        }
        if (tiempo_minimo <= 0) {
            throw new Exception("El tiempo mínimo de estancia debe ser mayor que 0.");
        }
        if (tiempo_maximo <= 0 || tiempo_maximo < tiempo_minimo) {
            throw new Exception("El tiempo máximo de estancia debe ser mayor que 0 y mayor que el tiempo mínimo.");
        }
        if (precio_habitacion == null || precio_habitacion <= 0) {
            throw new Exception("El precio de la habitación debe ser mayor que 0.");
        }
        if (tipo_vivienda == null || tipo_vivienda.trim().isEmpty()) {
            throw new Exception("El tipo de vivienda no puede estar vacío.");
        }
    }
}
