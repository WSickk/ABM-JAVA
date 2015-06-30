/*
 * Entidad.java
 * Clase abstracta para manejar entidades
 * @autor Fernando Araujo
 */
package modelo;

import java.sql.ResultSet;

public abstract class Entidad {
    private String entidad;
    private Conexion conexion;

    /**
     * Constrcutor Entidad
     */
    public Entidad() {
    } // end Entidad()

    public Entidad(String entidad, Conexion conexion) {
        this.entidad = entidad;
        this.conexion = conexion;
    } // end Entidad()

    public Conexion getConexion() {
        return conexion;
    } // end getGestionConexion()

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    } // end setGestionConexion()

    /**
     * @return the entidad
     */
    public String getEntidad() {
        return entidad;
    } // end getEntidad()

    /**
     * @param entidad
     *            the entidad to set
     */
    public void setEntidad(String entidad) {
        this.entidad = entidad// <editor-fold defaultstate="collapsed" desc="comment">
                ;// </editor-fold>
    } // end setEntidad()

    // Metodo que entrega un string entre comillas simples
    // necesario para comandos MySQL
    public static String quotate(String content) {
        return "'" + content + "'";
    } // end quotate()

    public static String quotate(int content) {
        return "'" + content + "'";
    } // end quotate()

    public static String quotate(char content) {
        return "'" + content + "'";
    } // end quotate()

    /**
     * Buscar registro
     */
    public abstract boolean buscar();
    

    /**
     * Agregar registro
     */
    public abstract void agregar();

    /**
     * Modificar registro
     */
    public abstract void modificar();

    /**
     * Eliminar registro
     */
    public abstract void eliminar();

    public abstract ResultSet leer();

} // end Entidad

