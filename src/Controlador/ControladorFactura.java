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
import java.sql.Array;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class ControladorFactura implements ActionListener, KeyListener {

    private Persona p;
    private Producto prod;
    private VistaFactura facturaVista;
    private ModeloFactura facturaM;
    private ModeloProductos prodM;
    private DefaultListModel lista;
    ArrayList<Producto> arrayListaDeProductos = new ArrayList<Producto>();

    public ControladorFactura(Persona p, Producto prod, VistaFactura facturaVista, ModeloFactura facturaM, ModeloProductos prodM) {
        this.p = p;
        this.prod = prod;
        this.facturaVista = facturaVista;
        this.facturaM = facturaM;
        this.prodM = prodM;
        this.facturaVista.btnAñadir.addActionListener(this);
        this.facturaVista.btnQuitar.addActionListener(this);
        this.facturaVista.btnFacturar.addActionListener(this);
        this.facturaVista.btnSalir.addActionListener(this);
        this.facturaVista.txtBuscar.addKeyListener(this);
        this.facturaVista.tbtProductos.setModel(prodM.consultarProductos());
        //this.facturaVista.tbtProductos.setEnabled(false);
        lista = new DefaultListModel();
        this.facturaVista.ListaProductosFacturar.setModel(lista);
    }

    public void iniciar() {
        facturaVista.setTitle("FACTURAS");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == facturaVista.btnAñadir) {
            try {

                int fila = facturaVista.tbtProductos.getSelectedRow();
                int id = Integer.parseInt(facturaVista.tbtProductos.getValueAt(fila, 0).toString());
                String nombre = (String) facturaVista.tbtProductos.getValueAt(fila, 1);
                float precio = Float.parseFloat(facturaVista.tbtProductos.getValueAt(fila, 2).toString());
                int cantidad = Integer.parseInt(JOptionPane.showInputDialog("ingrese la cantidad que desea comprar"));

                prod.setCantidad(cantidad);
                prod.setId(id);
                prod.setNombre(nombre);
                prod.setPrecio(precio);
                lista.addElement(prod.toString());
                arrayListaDeProductos.add(new Producto(nombre, precio, cantidad, id));
            } catch (Exception a) {
                JOptionPane.showMessageDialog(null, "ingrese solo numeros");
            }
        } else if (e.getSource() == facturaVista.btnQuitar) {
            try {
                int elegido = this.facturaVista.ListaProductosFacturar.getSelectedIndex();
                lista.remove(elegido);
                arrayListaDeProductos.remove(elegido);
                /*
                for (Producto arrayListaDeProducto : arrayListaDeProductos) {
                    JOptionPane.showMessageDialog(null, arrayListaDeProducto);
                }*/
            } catch (Exception a) {
                JOptionPane.showMessageDialog(null, "debe seleccionar un producto de la lista");
            }

        } else if (e.getSource() == facturaVista.btnFacturar) {

            try {
                if (arrayListaDeProductos.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "primero seleccione los productos a que va a faturar");
                } else {

                    ArrayList<Producto> arrayListaDeProductosClonado = new ArrayList<Producto>(arrayListaDeProductos);

                    if (facturaM.InsertarFactura(p, arrayListaDeProductosClonado)) {

                        Utilidad util = new Utilidad();
                        util.CrearPDFCompra(p, arrayListaDeProductos, facturaM.getfactura());
                        JOptionPane.showMessageDialog(null, "La factura ha sido registrada con exito");
                    }

                }
            } catch (Exception a) {
            }

        } else if (e.getSource() == facturaVista.btnSalir) {
            prodM.Desconectar();
            facturaM.Desconectar();
            this.facturaVista.hide();
        }
    }

    @Override
    public void keyReleased(KeyEvent e
    ) {
        facturaVista.tbtProductos.setModel(prodM.filtrarProductos(facturaVista.txtBuscar.getText()));
    }

    @Override
    public void keyTyped(KeyEvent e
    ) {

    }

    @Override
    public void keyPressed(KeyEvent e
    ) {

    }

}
