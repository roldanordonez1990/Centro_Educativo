package ventana;

import java.awt.BorderLayout;
import java.awt.MenuBar;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Menu extends JMenuBar {

	public Menu() {
		JMenu entidad = new JMenu("Entidades");
		
		entidad.add(crearMenuItemLanzamientoJDialog("Gesti�n del Curso", new PanelGestionCurso()));
		entidad.add(crearMenuItemLanzamientoJDialog("Gesti�n de las Materias", new PanelGestionMateria()));
		entidad.add(crearMenuItemLanzamientoJDialog("Gesti�n de Estudiantes", new PanelGestionEstudiante()));
		entidad.add(crearMenuItemLanzamientoJDialog("Gesti�n de Profesores", new PanelGestionProfesor()));
		

		this.add(entidad);
		
		
	}
	

	/**
	 * 
	 * @param titulo
	 * @param panel
	 * @return
	 */
	
	//M�todo en el cual crearemos el JDialog
	public JMenuItem crearMenuItemLanzamientoJDialog(String titulo, JPanel panel) {

		JMenuItem item = new JMenuItem(titulo);

		item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog dialogo = new JDialog();
				
				dialogo.setResizable(true);

				dialogo.setTitle(titulo);

				dialogo.setContentPane(panel);

				// empaquetar el di�logo hace que todos los componentes ocupen el espacio que
				// deben
				dialogo.pack();

				// Esto significa que el usuario no podr� picar en otro lado hasta que se cierre
				// la ventana del di�logo
				dialogo.setModal(true);

				//Aqu� indicamos las coordenadas donde queramos que aparezca el JDialog
				dialogo.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2 - dialogo.getWidth() / 2),
						(Toolkit.getDefaultToolkit().getScreenSize().height / 2 - dialogo.getHeight() / 2));
				
				dialogo.setVisible(true);

			}
		});
		return item;

	}

}
