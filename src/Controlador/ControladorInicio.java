package Controlador;

import Modelo.Ciudad;
import Modelo.ModeloCiudad;
import Modelo.ModeloEmpleado;
import Modelo.ModeloFactura;
import Modelo.ModeloProductos;
import Modelo.Persona;
import Modelo.Producto;
import Vista.VistaCiudades;
import Vista.VistaEmpleado;
import Vista.VistaFactura;
import Vista.VistaInicio;
import Vista.VistaProductos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Andres
 */
public class ControladorInicio implements ActionListener {

    private Persona p;
    private ControladorCiudades ciudadesC;
    private VistaInicio inicioV;

    public ControladorInicio(Persona p, VistaInicio inicioV) {
        this.p = p;
        this.inicioV = inicioV;
        this.inicioV.btnFactura.addActionListener(this);
        this.inicioV.btnCiudades.addActionListener(this);
        this.inicioV.btnProductos.addActionListener(this);
        this.inicioV.btnEmpleados.addActionListener(this);
        this.inicioV.btnSalir.addActionListener(this);

    }

    public void iniciar() {
        inicioV.setTitle("INICIO");
        if (this.p.getCargo() == 1) {
            inicioV.lblBienvenido.setText("Bienvenido señor: " + this.p.getNombre());
            inicioV.lblCargo.setText("Cargo: Administrador");
        } else if (this.p.getCargo() == 2) {
            inicioV.lblBienvenido.setText("Bienvenido señor: " + this.p.getNombre());
            inicioV.lblCargo.setText("Cargo: Empleado");
            inicioV.btnEmpleados.setVisible(false);
            inicioV.btnCiudades.setVisible(false);
            inicioV.btnProductos.setVisible(false);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == inicioV.btnCiudades) {
            Ciudad ciu = new Ciudad();
            ModeloCiudad ciudM = new ModeloCiudad();
            VistaCiudades vistaCiudades = new VistaCiudades();

            ControladorCiudades Controladorciu = new ControladorCiudades(ciu, ciudM, vistaCiudades);
            Controladorciu.iniciar();
            vistaCiudades.setVisible(true);
        } else if (e.getSource() == inicioV.btnEmpleados) {
            Persona per = new Persona();
            ModeloEmpleado personaM = new ModeloEmpleado();
            VistaEmpleado personaVista = new VistaEmpleado();

            ControladorEmpleado ControladorciEmpleado = new ControladorEmpleado(per, personaM, personaVista);
            ControladorciEmpleado.iniciar();
            personaVista.setVisible(true);
        } else if (e.getSource() == inicioV.btnProductos) {
            Producto pro = new Producto();
            ModeloProductos ProdM = new ModeloProductos();
            VistaProductos ProdVista = new VistaProductos();

            ControladorProductos ControlProductos = new ControladorProductos(pro, ProdM, ProdVista);
            ControlProductos.iniciar();
            ProdVista.setVisible(true);
        } else if (e.getSource() == inicioV.btnFactura) {
            Producto pro = new Producto();
            ModeloProductos ProdM = new ModeloProductos();
            ModeloFactura facM = new ModeloFactura();
            VistaFactura facturaVista = new VistaFactura();

            ControladorFactura contrFactur = new ControladorFactura(p, pro, facturaVista, facM, ProdM);
            contrFactur.iniciar();
            facturaVista.setVisible(true);
        } else if (e.getSource() == inicioV.btnSalir) {
            if (JOptionPane.showConfirmDialog(null, "Seguro que desea salir?", "SALIR", 0) == 0) {
                System.exit(0);
            }
        } else {
            JOptionPane.showMessageDialog(null, "no se que boton estas apretando");
        }

    }

}
