package entidades;

public class Estancia {
    private int id_estancia;
    private int id_cliente;
    private int id_casa;
    private String nombre_huesped;
    private String fecha_desde;
    private String fecha_hasta;

    private Casa casa;
    private Cliente cliente;

    public Estancia() {
    }
    

    public Estancia(int id_cliente, int id_casa, String nombre_huesped, String fecha_desde, String fecha_hasta) {
        this.id_cliente = id_cliente;
        this.id_casa = id_casa;
        this.nombre_huesped = nombre_huesped;
        this.fecha_desde = fecha_desde;
        this.fecha_hasta = fecha_hasta;
    }


    public Estancia(int id_estancia, int id_cliente, int id_casa, String nombre_huesped, String fecha_desde,
            String fecha_hasta) {
        this.id_estancia = id_estancia;
        this.id_cliente = id_cliente;
        this.id_casa = id_casa;
        this.nombre_huesped = nombre_huesped;
        this.fecha_desde = fecha_desde;
        this.fecha_hasta = fecha_hasta;
    }
    

    public Cliente getCliente() {
        return cliente;
    }


    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public Casa getCasa() {
        return casa;
    }

    public void setCasa(Casa casa) {
        this.casa = casa;
    }

    public int getId_estancia() {
        return id_estancia;
    }
    public void setId_estancia(int id_estancia) {
        this.id_estancia = id_estancia;
    }
    public int getId_cliente() {
        return id_cliente;
    }
    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
    public int getId_casa() {
        return id_casa;
    }
    public void setId_casa(int id_casa) {
        this.id_casa = id_casa;
    }
    public String getNombre_huesped() {
        return nombre_huesped;
    }
    public void setNombre_huesped(String nombre_huesped) {
        this.nombre_huesped = nombre_huesped;
    }
    public String getFecha_desde() {
        return fecha_desde;
    }
    public void setFecha_desde(String fecha_desde) {
        this.fecha_desde = fecha_desde;
    }
    public String getFecha_hasta() {
        return fecha_hasta;
    }
    public void setFecha_hasta(String fecha_hasta) {
        this.fecha_hasta = fecha_hasta;
    }
    
}
