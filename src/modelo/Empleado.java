/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author WSick
 */
public class Empleado extends Entidad{
    private String apeynom;
    private int dni;
    private String zona;

    public Empleado() {
    }

    public Empleado(String apeynom, int dni, String zona, Conexion conexion) {
        super("empleado", conexion);
        this.apeynom = apeynom;
        this.dni = dni;
        this.zona = zona;
    }

    public Empleado(String apeynom, int dni, String zona) {
        this.apeynom = apeynom;
        this.dni = dni;
        this.zona = zona;
    }

    public Empleado(Conexion conexion) {
        super("empleado", conexion);
    }
    
    

    public String getApeynom() {
        return apeynom;
    }

    public void setApeynom(String apeynom) {
        this.apeynom = apeynom;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    @Override
    public boolean buscar() {
        boolean resultado = false;
        String sql = "SELECT * FROM empleados WHERE dni ="+getDni()+"";
        
        try {
            ResultSet rs = getConexion().getStatement().executeQuery(sql);
            if (rs.next()) {
                setApeynom(rs.getString("apeynom"));
                setZona(rs.getString("zona"));
                setDni(rs.getInt("dni"));
                System.out.println(sql);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error al buscar el Empleado con ese DNI");
            System.err.print(e);
        }
        
        return resultado;
    }

    @Override
    public void agregar() {
        String sql = "INSERT INTO empleados (apeynom, dni, zona) VALUES('"+getApeynom()+"','"+getDni()+"','"+getZona()+"')";
        try {
            getConexion().getStatement().executeUpdate(sql);
            System.out.println(sql);
        } catch (Exception e) {
            System.out.print("Error al agregar un Empleado a la base de datos");
            System.err.print(e);
        }
    }

    @Override
    public void modificar() {
        String sql = "UPDATE empleados SET apeynom ='"+getApeynom()+"' ,zona ='"+getZona()+"' WHERE dni ='"+getDni()+"'";
        try {
            getConexion().getStatement().executeUpdate(sql);
            System.out.println(sql);
        } catch (Exception e) {
            System.out.println("Error al Modificar el empleado");
            System.err.print(e);
            System.out.println(sql);
        }
    }

    @Override
    public void eliminar() {
        String sql = "DELETE FROM empleados WHERE dni = "+getDni()+"";
        try {
            getConexion().getStatement().executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Error al Eliminar un Empleado");
            System.err.print(e);
        }
    }

    @Override
    public ResultSet leer() {
        String sql = "SELECT * FROM empleados";
        try {
            ResultSet rs = getConexion().getStatement().executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
            System.out.println("Problemas al realizarce el Select sobre la Tabla de empleados. ");
            System.err.print(ex);
        }
        return null;
    }
    
    
    
}
