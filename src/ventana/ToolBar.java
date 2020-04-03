package ventana;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar {

	public ToolBar() {
		this.add(creaBoton(0, "", "gotostart.png", new PanelGestionCurso(), "Ir a Curso"));
		this.add(creaBoton(1, "", "valign16.png",  new PanelGestionEstudiante(), "Ir a Estudiante"));
		this.add(creaBoton(2, "", "valign16.png", new PanelGestionProfesor(),"Ir a Profesor"));
		this.add(creaBoton(3, "", "valign16.png", new PanelGestionMateria(), "Ir a Materia"));
		this.add(creaBoton(4, "", "gotoend.png", new PanelGestionValoracionNotas(), "Ir a Valoración-Materia"));
	}

	/**
	 * 
	 */

	private JButton creaBoton(int num, String titulo, String icono, JPanel panel, String toolTip) {
		JButton jbt = new JButton();

		jbt.setText(titulo);
		jbt.setToolTipText(toolTip); // es el texto que aparece cuando dejamos el ratón encima

		jbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// VentanaPrincipal.jtabbetMenu.setSelectedIndex(num);

				JDialog dialogo = new JDialog();

				dialogo.setResizable(true);

				dialogo.setTitle(titulo);

				dialogo.setContentPane(panel);

				// empaquetar el diálogo hace que todos los componentes ocupen el espacio que
				// deben
				dialogo.pack();

				// Esto significa que el usuario no podrá picar en otro lado hasta que se cierre
				// la ventana del diálogo
				dialogo.setModal(true);

				// Aquí indicamos las coordenadas donde queramos que aparezca el JDialog
				dialogo.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2 - dialogo.getWidth() / 2),
						(Toolkit.getDefaultToolkit().getScreenSize().height / 2 - dialogo.getHeight() / 2));

				dialogo.setVisible(true);

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
