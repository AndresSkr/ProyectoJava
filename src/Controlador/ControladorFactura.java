package Controlador;

import Modelo.ModeloFactura;
import Modelo.ModeloProductos;
import Modelo.Persona;
import Modelo.Producto;
import Vista.VistaFactura;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

public class ControladorFactura implements ActionListener, KeyListener {

    private Persona p;
    private Producto prod;
    private VistaFactura facturaVista;
    private ModeloFactura facturaM;
    private ModeloProductos prodM;

    public ControladorFactura(Persona p, Producto prod, VistaFactura facturaVista, ModeloFactura facturaM, ModeloProductos prodM) {
        this.p = p;
        this.prod = prod;
        this.facturaVista = facturaVista;
        this.facturaM = facturaM;
        this.prodM = prodM;
        this.facturaVista.btnSalir.addActionListener(this);
        this.facturaVista.txtBuscar.addKeyListener(this);
        this.facturaVista.tbtProductos.setModel(prodM.consultarProductos());
        this.facturaVista.tbtProductos.setEnabled(false);
    }

    public void iniciar() {
        facturaVista.setTitle("FACTURAS");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
         if (e.getSource() == facturaVista.btnSalir) {
            prodM.Desconectar();
            facturaM.Desconectar();
            this.facturaVista.hide();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        facturaVista.tbtProductos.setModel(prodM.filtrarProductos(facturaVista.txtBuscar.getText()));
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

}
