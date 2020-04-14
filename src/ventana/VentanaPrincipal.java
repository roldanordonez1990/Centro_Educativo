package ventana;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

import utils.Apariencia;
import utils.CacheImagenes;

public class VentanaPrincipal extends JFrame {

	public static int ANCHO = 450;
	public static int ALTO = 400;
	public static String TITULO_APLICACION = "Centro Educativo";
	static JMenu jmenu = new JMenu();
	
	
	private CacheImagenes cacheImagenes;
	public static BufferedImage iconoApp;

	// Establecer la apariencia típica de Windows
	static {
		Apariencia.setAparienciasOrdenadas(Apariencia.aparienciasOrdenadas);
	}

	public VentanaPrincipal() {
		super(TITULO_APLICACION);

		cacheImagenes = new CacheImagenes();
		iconoApp = cacheImagenes.getImagen("conectando.gif");
		setIconImage(iconoApp);

		this.setExtendedState(JFrame.NORMAL);
		this.setMinimumSize(new Dimension(ANCHO, ALTO));
		this.setJMenuBar(new Menu());
		this.setResizable(true);
		this.setLayout(new BorderLayout());
		this.add(new ToolBar(), BorderLayout.NORTH);

		agregarGestionCierreAplicacion();

		// Construcción elementos básicos sobre el ContentPanel
		// this.setContentPane(new JTabbedPaneGestion());
		//this.getContentPane().add(jtabbetMenu, BorderLayout.CENTER);
	}

	/**
	 * 
	 */
	private void agregarGestionCierreAplicacion() {
		// Configuración del evento de cerrado
		// Para más información debes estudiar Javadoc WindowListener y WindowAdapter
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				String posiblesRespuestas[] = { "Sí", "No" };
				// En esta opción se utiliza un showOptionDialog en el que personalizo el icono
				// mostrado
				int opcionElegida = JOptionPane.showOptionDialog(null, "¿Realmente desea cerrar la aplicación?",
						TITULO_APLICACION, JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
						cacheImagenes.getIcono("confirm.png"), posiblesRespuestas, posiblesRespuestas[1]);
				if (opcionElegida == 0) {
					System.exit(0);
				}
			}
		});
	}
}
