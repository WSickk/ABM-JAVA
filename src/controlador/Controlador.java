/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;
import modelo.Empleado;
import vista.VDialog;
import vista.VPrincipal;

/**
 *
 * @author WSick
 */
public class Controlador {

    public static void agregar(VPrincipal aThis, Conexion mdbc) {
        Empleado empleado = new Empleado("",0,"",mdbc);
        VDialog ventana = new VDialog(aThis, true);
        boolean bandera;
        do {
            bandera = false;
            ventana.setVisible(true);
            if (ventana.getEstado() == 1) {
                String apeynom = ventana.getjTextField1().getText();
                String dni = ventana.getjTextField2().getText();
                String zona = ventana.getjTextField3().getText();
                try {
                    empleado.setApeynom(apeynom);
                    empleado.setDni(Integer.valueOf(dni));
                    empleado.setZona(zona);
                    if (!empleado.buscar()) {
                        empleado.agregar();
                        actualizarModelo(aThis.getjTable1(), mdbc);
                    } else {
                        JOptionPane.showMessageDialog(aThis, "DNI ya encontrado","ERROR",JOptionPane.ERROR_MESSAGE);
                        ventana.getjTextField2().selectAll();
                        ventana.getjTextField2().requestFocus();
                        bandera = true;
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(aThis, "Datos mal ingresados","Mensaje de error",JOptionPane.ERROR_MESSAGE);
                    ventana.getjTextField1().selectAll();
                    ventana.getjTextField1().requestFocus();
                    bandera = true;
                    System.err.print(e);
                }
            }
        } while (bandera);

    }

    public static void eliminar(VPrincipal aThis, Conexion mdbc) throws SQLException {
        Empleado empleado = new Empleado("",0,"",mdbc);
        int idx = aThis.getjTable1().getSelectedRow();
        if (idx > -1) {
            int opcion = JOptionPane.showConfirmDialog(aThis, "Esta seguro que desea borrar el empleado?","ALERTA",JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                empleado.setDni(Integer.valueOf(aThis.getjTable1().getValueAt(idx, 1).toString()));
                empleado.eliminar();
                actualizarModelo(aThis.getjTable1(), mdbc);
            }
        } else {
            JOptionPane.showMessageDialog(aThis,"Debe selecionar un Empleado","ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void modificar(VPrincipal aThis, Conexion mdbc) {
        Empleado empleado = new Empleado("", 0, "", mdbc);
        VDialog modi = new VDialog(aThis, true);
        int idx = aThis.getjTable1().getSelectedRow();
        if (idx > -1) {
            modi.getjTextField1().setText(aThis.getjTable1().getValueAt(idx, 0).toString());
            modi.getjTextField2().setText(aThis.getjTable1().getValueAt(idx, 1).toString());
            modi.getjTextField2().setEditable(false);
            modi.getjTextField3().setText(aThis.getjTable1().getValueAt(idx, 2).toString());
            boolean bandera;
            do {
                bandera = false;
                modi.setVisible(true);
                if (modi.getEstado() == 1) {
                    String apeynom = modi.getjTextField1().getText();
                    String dni = modi.getjTextField2().getText();
                    String zona = modi.getjTextField3().getText();
                    try {
                        empleado.setApeynom(apeynom);
                        empleado.setDni(Integer.parseInt(dni));
                        empleado.setZona(zona);
                        
                        if (!empleado.buscar()) {
                            empleado.modificar();
                            actualizarModelo(aThis.getjTable1(), mdbc);
                        } else {
                            JOptionPane.showMessageDialog(aThis, "Empleado no encontrado","Error",JOptionPane.ERROR_MESSAGE);
                            modi.getjTextField2().selectAll();
                            modi.getjTextField2().requestFocus();
                            bandera = true;
                        }
                        
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(aThis, "Datos invalidos","Error",JOptionPane.ERROR_MESSAGE);
                        modi.getjTextField1().selectAll();
                        modi.getjTextField1().requestFocus();
                        bandera = true;
                    }
                }
            } while (bandera);
        } else {
            JOptionPane.showMessageDialog(aThis, "", "", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void actualizarModelo(JTable tabla, Conexion mdbc) throws SQLException{
        Empleado empleado = new Empleado("",0,"",mdbc);
        ResultSet rs = empleado.leer();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre y Apellido");
        modelo.addColumn("DNI");
        modelo.addColumn("Zona");
        while (rs.next()) {
            Object[] fila = new Object[3];
            fila[0] = rs.getString("apeynom");
            fila[1] = rs.getString("dni");
            fila[2] = rs.getString("zona");
            modelo.addRow(fila);
        }
        tabla.setModel(modelo);
    }
}
