package Controlador;

import Modelo.Ciudad;
import Modelo.ModeloCiudad;
import Modelo.ModeloEmpleado;
import Modelo.Persona;
import Vista.VistaCiudades;
import Vista.VistaEmpleado;
import Vista.VistaInicio;
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
        this.inicioV.btnCiudades.addActionListener(this);
        this.inicioV.btnEmpleados.addActionListener(this);
        this.inicioV.btnSalir.addActionListener(this);
    }

    public void iniciar() {
        inicioV.setTitle("INICIO");
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
        } else if (e.getSource() == inicioV.btnSalir) {
            if (JOptionPane.showConfirmDialog(null, "Seguro que desea salir?", "SALIR", 0) == 0) {
                System.exit(0);
            }
        } else {
            JOptionPane.showMessageDialog(null, "no se que boton estas apretando");
        }

    }

}
