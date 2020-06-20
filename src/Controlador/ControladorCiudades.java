package Controlador;

import Modelo.Ciudad;
import Modelo.ModeloCiudad;
import Vista.VistaCiudades;
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
public class ControladorCiudades implements ActionListener, KeyListener, MouseListener {

    private Ciudad ciu;
    private ModeloCiudad ciudM;
    private VistaCiudades vistaCiudades;

    public ControladorCiudades(Ciudad ciu, ModeloCiudad ciudM, VistaCiudades vistaCiudades) {
        this.ciu = ciu;
        this.ciudM = ciudM;
        this.vistaCiudades = vistaCiudades;
        this.vistaCiudades.btnGuardar.addActionListener(this);
        this.vistaCiudades.btnEditar.addActionListener(this);
        this.vistaCiudades.btnEliminar.addActionListener(this);
        this.vistaCiudades.btnSalir.addActionListener(this);
        this.vistaCiudades.txtCiudad.addActionListener(this);
        this.vistaCiudades.txtPoblacion.addActionListener(this);
        this.vistaCiudades.txtBuscar.addKeyListener(this);
        this.vistaCiudades.tblCiudades.addMouseListener(this);
        this.vistaCiudades.tblCiudades.setModel(ciudM.consultarCiudades());
    }

    public void iniciar() {
        vistaCiudades.setTitle("Ciudades");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaCiudades.btnGuardar) {

            ciu = new Ciudad(vistaCiudades.txtCiudad.getText(), Integer.parseInt(vistaCiudades.txtPoblacion.getText()));
            if (ciudM.InstertarCiudad(ciu)) {
                JOptionPane.showMessageDialog(null, "registro guardado");
            } else {
                JOptionPane.showMessageDialog(null, "no se puedo registrar");
            }

        } else if (e.getSource() == vistaCiudades.btnEditar) {
            
            int fila = vistaCiudades.tblCiudades.getSelectedRow();
            String id = (String) vistaCiudades.tblCiudades.getValueAt(fila, 0);
            if (ciudM.ActuaizarCiudades(vistaCiudades.txtCiudad.getText(), vistaCiudades.txtPoblacion.getText(), id)) {
                this.vistaCiudades.tblCiudades.setModel(ciudM.consultarCiudades());
            } else {
                JOptionPane.showMessageDialog(null, "error al actualizar el registro ");
            }

        } else if (e.getSource() == vistaCiudades.btnEliminar) {

        } else if (e.getSource() == vistaCiudades.btnSalir) {
            this.vistaCiudades.hide();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //JOptionPane.showMessageDialog(null, vistaCiudades.txtBuscar.getText());
        vistaCiudades.tblCiudades.setModel(ciudM.filtrarCiudades(vistaCiudades.txtBuscar.getText()));
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        int filaSelecionada = vistaCiudades.tblCiudades.rowAtPoint(e.getPoint());
        vistaCiudades.txtCiudad.setText(vistaCiudades.tblCiudades.getValueAt(filaSelecionada, 1).toString());
        vistaCiudades.txtPoblacion.setText(vistaCiudades.tblCiudades.getValueAt(filaSelecionada, 2).toString());

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
