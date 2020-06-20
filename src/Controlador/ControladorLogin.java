package Controlador;

import Modelo.ModeloLogin;
import Modelo.Persona;
import Vista.VistaInicio;
import Vista.VistaLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Andres
 */
public class ControladorLogin implements ActionListener {

    private Persona p;
    private ModeloLogin loginM;
    private VistaLogin LoginV;

    public ControladorLogin(Persona p, ModeloLogin loginM, VistaLogin vistaLogin) {
        this.p = p;
        this.loginM = loginM;
        this.LoginV = vistaLogin;
        this.LoginV.btnIngresar.addActionListener(this);
        this.LoginV.txtUsuario.addActionListener(this);
        this.LoginV.txtClave.addActionListener(this);

    }

    public void iniciar() {
        LoginV.setTitle("LOGIN");
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == LoginV.btnIngresar) {
            p = loginM.Ingreso(LoginV.txtUsuario.getText(), LoginV.txtClave.getText());
            if (p != null) {
                
                VistaInicio inicioV = new VistaInicio(p);

                ControladorInicio InicioC = new ControladorInicio(p, inicioV);

                InicioC.iniciar();
                inicioV.setVisible(true);
                LoginV.hide();
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
            }
        }

    }

}
