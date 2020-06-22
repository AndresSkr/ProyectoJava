package Controlador;

import Modelo.ModeloEmpleado;
import Modelo.Persona;
import Vista.VistaEmpleado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Andres
 */
public class ControladorEmpleado implements ActionListener, KeyListener, MouseListener {

    private Persona p;
    private ModeloEmpleado PersonaM;
    private VistaEmpleado vistaEmpleado;

    public ControladorEmpleado(Persona p, ModeloEmpleado PersonaM, VistaEmpleado vistaEmpleado) {
        this.p = p;
        this.PersonaM = PersonaM;
        this.vistaEmpleado = vistaEmpleado;
        this.vistaEmpleado.btnGuardar.addActionListener(this);
        this.vistaEmpleado.btnNuevo.addActionListener(this);
        this.vistaEmpleado.btnEditar.addActionListener(this);
        this.vistaEmpleado.btnEliminar.addActionListener(this);
        this.vistaEmpleado.btnSalir.addActionListener(this);
        this.vistaEmpleado.jcbCargo.addActionListener(this);
        this.vistaEmpleado.txtCiudad.addActionListener(this);
        this.vistaEmpleado.txtApellidos.addActionListener(this);
        this.vistaEmpleado.txtNombres.addActionListener(this);
        this.vistaEmpleado.txtId.addActionListener(this);
        this.vistaEmpleado.txtTelefono.addActionListener(this);
        this.vistaEmpleado.txtUsuario.addActionListener(this);
        this.vistaEmpleado.txtBuscar.addKeyListener(this);
        this.vistaEmpleado.txtContra.addActionListener(this);
        this.vistaEmpleado.tblEmpleados.addMouseListener(this);
        this.vistaEmpleado.tblEmpleados.setModel(PersonaM.consultarEmpleado());
    }

    public void iniciar() {
        vistaEmpleado.setTitle("Empleados");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaEmpleado.btnGuardar) {

            p = new Persona(vistaEmpleado.txtNombres.getText(), vistaEmpleado.txtApellidos.getText(),
                    vistaEmpleado.txtCiudad.getText(), vistaEmpleado.txtId.getText(), vistaEmpleado.txtTelefono.getText(), vistaEmpleado.jcbCargo.getSelectedIndex());
            if (PersonaM.InstertarEmpleado(p, vistaEmpleado.txtUsuario.getText(), vistaEmpleado.txtContra.getText())) {
                JOptionPane.showMessageDialog(null, "registro guardado");
                Limpiar();
                this.vistaEmpleado.tblEmpleados.setModel(PersonaM.consultarEmpleado());
            } else {
                JOptionPane.showMessageDialog(null, "no se puedo registrar");
            }

        } else if (e.getSource() == vistaEmpleado.btnEditar) {

            int fila = vistaEmpleado.tblEmpleados.getSelectedRow();
            String id = (String) vistaEmpleado.tblEmpleados.getValueAt(fila, 0);
            if (PersonaM.ActuaizarEmpleado(id, vistaEmpleado.txtNombres.getText(),
                    vistaEmpleado.txtApellidos.getText(), vistaEmpleado.txtCiudad.getText(), vistaEmpleado.txtTelefono.getText())) {
                Limpiar();
                this.vistaEmpleado.tblEmpleados.setModel(PersonaM.consultarEmpleado());
            } else {
                JOptionPane.showMessageDialog(null, "error al actualizar el registro ");
            }

        } else if (e.getSource() == vistaEmpleado.btnEliminar) {
            int fila = vistaEmpleado.tblEmpleados.getSelectedRow();
            String id = (String) vistaEmpleado.tblEmpleados.getValueAt(fila, 5);
            PersonaM.EliminarEmpleado(id);
            Habilitar();
            this.vistaEmpleado.tblEmpleados.setModel(PersonaM.consultarEmpleado());
        } else if (e.getSource() == vistaEmpleado.btnNuevo) {
            Habilitar();
        } else if (e.getSource() == vistaEmpleado.btnSalir) {
            PersonaM.Desconectar();
            this.vistaEmpleado.hide();
        }
    }

    public void Habilitar() {
        vistaEmpleado.txtUsuario.setEditable(true);
        vistaEmpleado.txtContra.setEditable(true);
        vistaEmpleado.txtId.setEditable(true);
        vistaEmpleado.jcbCargo.setEditable(true);
        Limpiar();
    }

    public void Limpiar() {
        vistaEmpleado.txtNombres.setText("");
        vistaEmpleado.txtApellidos.setText("");
        vistaEmpleado.txtCiudad.setText("");
        vistaEmpleado.txtTelefono.setText("");
        vistaEmpleado.txtId.setText("");
        vistaEmpleado.txtUsuario.setText("");
        vistaEmpleado.txtContra.setText("");
        vistaEmpleado.jcbCargo.setSelectedIndex(0);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //JOptionPane.showMessageDialog(null, vistaCiudades.txtBuscar.getText());
        vistaEmpleado.tblEmpleados.setModel(PersonaM.filtrarEmpleados(vistaEmpleado.txtBuscar.getText()));
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        int filaSelecionada = vistaEmpleado.tblEmpleados.rowAtPoint(e.getPoint());
        vistaEmpleado.txtId.setText(vistaEmpleado.tblEmpleados.getValueAt(filaSelecionada, 0).toString());
        vistaEmpleado.txtNombres.setText(vistaEmpleado.tblEmpleados.getValueAt(filaSelecionada, 1).toString());
        vistaEmpleado.txtApellidos.setText(vistaEmpleado.tblEmpleados.getValueAt(filaSelecionada, 2).toString());
        vistaEmpleado.txtCiudad.setText(vistaEmpleado.tblEmpleados.getValueAt(filaSelecionada, 3).toString());
        vistaEmpleado.txtTelefono.setText(vistaEmpleado.tblEmpleados.getValueAt(filaSelecionada, 4).toString());
        vistaEmpleado.txtId.setEditable(false);
        vistaEmpleado.jcbCargo.setEditable(false);
        vistaEmpleado.txtUsuario.setEditable(false);
        vistaEmpleado.txtContra.setEditable(false);

    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

}
