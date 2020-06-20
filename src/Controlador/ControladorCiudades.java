package Controlador;

import Modelo.Ciudad;
import Modelo.ModeloCiudad;
import Vista.VistaCiudades;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Andres
 */
public class ControladorCiudades implements ActionListener {

    private Ciudad ciu;
    private ModeloCiudad ciudM;
    private VistaCiudades vistaCiudades;

    public ControladorCiudades(Ciudad ciu, ModeloCiudad ciudM, VistaCiudades vistaCiudades) {
        this.ciu = ciu;
        this.ciudM = ciudM;
        this.vistaCiudades = vistaCiudades;
        this.vistaCiudades.btnGuardar.addActionListener(this);
         this.vistaCiudades.btnSalir.addActionListener(this);
        this.vistaCiudades.txtCiudad.addActionListener(this);
        this.vistaCiudades.txtPoblacion.addActionListener(this);

    }
    
    public void iniciar(){
        vistaCiudades.setTitle("Ciudades");
    }
    
    @Override
    public void  actionPerformed(ActionEvent e){
        if(e.getSource()== vistaCiudades.btnGuardar){
          ciu=  new Ciudad(vistaCiudades.txtCiudad.getText(), Integer.parseInt(vistaCiudades.txtPoblacion.getText()));
          if(ciudM.InstertarCiudad(ciu)){
              JOptionPane.showMessageDialog(null,"registro guardado");
          }else{
              JOptionPane.showMessageDialog(null,"no se puedo registrar");
          }
        } else if(e.getSource()== vistaCiudades.btnSalir){
             this.vistaCiudades.hide();
        }else{
            JOptionPane.showMessageDialog(null,"algun error");
        }
    }

}
