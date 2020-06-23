package Controlador;

import Modelo.ModeloProductos;
import Modelo.Producto;
import Vista.VistaProductos;
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
public class ControladorProductos implements ActionListener, KeyListener, MouseListener {

    private Producto prod;
    private ModeloProductos prodM;
    private VistaProductos ProducVista;

    public ControladorProductos(Producto prod, ModeloProductos prodM, VistaProductos ProducVista) {
        this.prod = prod;
        this.prodM = prodM;
        this.ProducVista = ProducVista;
        this.ProducVista.btnGuardar.addActionListener(this);
        this.ProducVista.btnEditar.addActionListener(this);
        this.ProducVista.btnEliminar.addActionListener(this);
        this.ProducVista.btnSalir.addActionListener(this);
        this.ProducVista.txtNombre.addActionListener(this);
        this.ProducVista.txtPrecio.addActionListener(this);
        this.ProducVista.txtCantidad.addActionListener(this);
        this.ProducVista.txtBuscar.addKeyListener(this);
        this.ProducVista.tblProductos.addMouseListener(this);
        this.ProducVista.tblProductos.setModel(prodM.consultarProductos());
    }

    public void iniciar() {
        ProducVista.setTitle("Productoes");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ProducVista.btnGuardar) {

            prod = new Producto(ProducVista.txtNombre.getText(), Float.parseFloat(ProducVista.txtPrecio.getText()), Integer.parseInt(ProducVista.txtCantidad.getText()), 1);
            if (prodM.InstertarProductos(prod)) {
                JOptionPane.showMessageDialog(null, "registro guardado");
                Limpiar();
                this.ProducVista.tblProductos.setModel(prodM.consultarProductos());
            } else {
                JOptionPane.showMessageDialog(null, "no se puedo registrar");
            }

        } else if (e.getSource() == ProducVista.btnEditar) {

            int fila = ProducVista.tblProductos.getSelectedRow();
            String id = (String) ProducVista.tblProductos.getValueAt(fila, 0);
            if (prodM.ActuaizarProductos(ProducVista.txtNombre.getText(), Float.parseFloat(ProducVista.txtPrecio.getText()), id, Integer.parseInt(ProducVista.txtCantidad.getText()))) {
                Limpiar();
                this.ProducVista.tblProductos.setModel(prodM.consultarProductos());
            } else {
                JOptionPane.showMessageDialog(null, "error al actualizar el registro ");
            }

        } else if (e.getSource() == ProducVista.btnEliminar) {
            int fila = ProducVista.tblProductos.getSelectedRow();
            String id = (String) ProducVista.tblProductos.getValueAt(fila, 0);
            prodM.EliminarProducto(id);
            Limpiar();
            this.ProducVista.tblProductos.setModel(prodM.consultarProductos());
        } else if (e.getSource() == ProducVista.btnSalir) {
            prodM.Desconectar();
            this.ProducVista.hide();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //JOptionPane.showMessageDialog(null, ProducVista.txtBuscar.getText());
        ProducVista.tblProductos.setModel(prodM.filtrarProductos(ProducVista.txtBuscar.getText()));
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        int filaSelecionada = ProducVista.tblProductos.rowAtPoint(e.getPoint());

        ProducVista.txtNombre.setText(ProducVista.tblProductos.getValueAt(filaSelecionada, 1).toString());
        ProducVista.txtPrecio.setText(ProducVista.tblProductos.getValueAt(filaSelecionada, 2).toString());
        ProducVista.txtCantidad.setText(ProducVista.tblProductos.getValueAt(filaSelecionada, 3).toString());

    }

    public void Limpiar() {
        ProducVista.txtNombre.setText("");
        ProducVista.txtPrecio.setText("");
        ProducVista.txtCantidad.setText("");
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
