package ventana;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar {

	public ToolBar() {
		this.add(creaBoton(0,"", "gotostart.png", "Ir a Curso"));
		this.add(creaBoton(1,"", "valign16.png", "Ir a Estudiante"));
		this.add(creaBoton(2,"", "valign16.png", "Ir a Profesor"));
		this.add(creaBoton(3,"", "valign16.png", "Ir a Materia"));
		this.add(creaBoton(4,"", "gotoend.png", "Ir a Valoración-Materia"));
	}

	/**
	 * 
	 */
	
	private JButton creaBoton(int num, String titulo, String icono, String toolTip) {
        JButton jbt = new JButton();
        
        jbt.setText(titulo);
        jbt.setToolTipText(toolTip); //es el texto que aparece cuando dejamos el ratón encima
        
        
        
        jbt.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	//VentanaPrincipal.jtabbetMenu.setSelectedIndex(num);
           
            	System.out.println("Has hecho clic en el botón: \"" + toolTip + "\"");
            }
        });
        
        try {
        	jbt.setIcon(CacheImagenes.getCacheImagenes().getIcono(icono));  
          } catch (Exception ex) {
        	  ex.printStackTrace();
          }
        return jbt;
	}
}
