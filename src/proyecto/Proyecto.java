package proyecto;

import Controlador.ControladorLogin;
import Modelo.ModeloLogin;
import Modelo.Persona;
import Vista.VistaLogin;

/**
 *
 * @author Andres
 */
public class Proyecto {

    public static void main(String[] args) {
        /*Ciudad ciu = new Ciudad();
        ModeloCiudad ciudM = new ModeloCiudad();
        VistaCiudades vistaCiudades= new VistaCiudades();
        
        ControladorCiudades Controladorciu= new ControladorCiudades(ciu, ciudM, vistaCiudades);
        Controladorciu.iniciar();
        
        vistaCiudades.setVisible(true);*/
        
        Persona p= new Persona();
        ModeloLogin logM= new ModeloLogin();
        VistaLogin loginV = new VistaLogin();
        
        ControladorLogin LoginC= new ControladorLogin(p, logM, loginV);
        
        LoginC.iniciar();
        loginV.setVisible(true);
    }
}
