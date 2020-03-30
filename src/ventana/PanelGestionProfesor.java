package ventana;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import model.Estudiante;
import model.Profesor;
import model.controladores.EstudianteControlador;
import model.controladores.MateriaControlador;
import model.controladores.ProfesorControlador;
import utils.CacheImagenes;

public class PanelGestionProfesor extends JPanel {

	public static int LOAD_FIRST = 0;
	public static int LOAD_PREV = 1;
	public static int LOAD_NEXT = 2;
	public static int LOAD_LAST = 3;
	public static int NEW = 4;
	public static int SAVE = 5;
	public static int REMOVE = 6;

	Profesor actual = null;

	PanelDatosPersonales panelDatosPersonales = new PanelDatosPersonales();

	public PanelGestionProfesor() {
		this.actual = ProfesorControlador.getInstancia().findFirst();
		
		this.setLayout(new BorderLayout());
		
		this.add(barraHerramientas(), BorderLayout.NORTH);
		this.add(panelDatosPersonales, BorderLayout.CENTER);
		
		
		cargarDatosActual();
	}

	private JToolBar barraHerramientas() {

		JToolBar barra = new JToolBar();

		JButton jbtPrimero = new JButton();
		asignarFuncion(jbtPrimero, "gotostart.png", LOAD_FIRST, "Primer registro");

		JButton jbtAnterior = new JButton();
		asignarFuncion(jbtAnterior, "previous.png", LOAD_PREV, "Anterior registro");

		JButton jbtPosterior = new JButton();
		asignarFuncion(jbtPosterior, "next.png", LOAD_NEXT, "Siguiente registro");

		JButton jbtUltimo = new JButton();
		asignarFuncion(jbtUltimo, "gotoend.png", LOAD_LAST, "Último registro");

		JButton jbtNuevo = new JButton();
		asignarFuncion(jbtNuevo, "nuevo.png", NEW, "Nuevo Registro");

		JButton jbtGuardar = new JButton();
		asignarFuncion(jbtGuardar, "guardar.png", SAVE, "Guardar Registro");

		JButton jbtEliminar = new JButton();
		asignarFuncion(jbtEliminar, "eliminar.png", REMOVE, "Eliminar registro");

		// barra.setLayout(new FlowLayout());
		barra.add(jbtPrimero);
		barra.add(jbtAnterior);
		barra.add(jbtPosterior);
		barra.add(jbtUltimo);
		barra.add(jbtNuevo);
		barra.add(jbtGuardar);
		barra.add(jbtEliminar);

		return barra;
	}

	/**
	 * 
	 */

	private void asignarFuncion(JButton jbt, String icono, final int funcion, String toolTip) {
		jbt.setIcon(CacheImagenes.getCacheImagenes().getIcono(icono));
		jbt.setToolTipText(toolTip);
		jbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Profesor obtenido = null;
				if (funcion == LOAD_FIRST)
					obtenido = ProfesorControlador.getInstancia().findFirst();
				if (funcion == LOAD_PREV)
					obtenido = ProfesorControlador.getInstancia().findPrevious(actual);
				if (funcion == LOAD_NEXT)
					obtenido = ProfesorControlador.getInstancia().findNext(actual);
				if (funcion == LOAD_LAST)
					obtenido = ProfesorControlador.getInstancia().findLast();
				if (funcion == NEW)
					nuevo();
				if (funcion == SAVE)
					try {
						guardar();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if (funcion == REMOVE)
					obtenido = eliminar();

				if (obtenido != null) {
					actual = obtenido;
					cargarDatosActual();
				}
			}
		});
	}

	/**
	 * 
	 */
	private void nuevo() {
		this.panelDatosPersonales.limpiarPantalla();
		// Muy importante esta línea para poder guardar nuevos registros, tenemos que
		// crear un nuevo objeto Estudiante
		this.actual = new Profesor();
		JOptionPane.showMessageDialog(this, "Por favor, introduzca los datos del nuevo registro");
	}

	/**
	 * 
	 */

	/**
	 * 
	 */

	private void guardar() throws ParseException {

		this.actual.setNombre(this.panelDatosPersonales.getNombre());
		this.actual.setApellido1(this.panelDatosPersonales.getApellido1());
		this.actual.setApellido2(this.panelDatosPersonales.getApellido2());
		this.actual.setDni(this.panelDatosPersonales.getDni());
		this.actual.setDireccion(this.panelDatosPersonales.getDireccion());
		this.actual.setTelefono(this.panelDatosPersonales.getTelefono());
		this.actual.setEmail(this.panelDatosPersonales.getEmail());
		this.actual.setTipologiasexo(this.panelDatosPersonales.getTipologiaSexo());
		this.actual.setColor(this.panelDatosPersonales.getColorElegido());

		if (actual.getId() == 0) {
			ProfesorControlador.getInstancia().persist(this.actual);
		} else {
			ProfesorControlador.getInstancia().merge(this.actual);
		}
		JOptionPane.showMessageDialog(this, "Guardado correctamente");
	}

	/**
	 * 
	 */

	private Profesor eliminar() {
		String respuestas[] = new String[] { "Sí", "No" };
		int opcionElegida = JOptionPane.showOptionDialog(null, "¿Realmente desea eliminar el registro?",
				"Eliminación del registro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.OK_CANCEL_OPTION,
				CacheImagenes.getCacheImagenes().getIcono("confirm.png"), respuestas, respuestas[1]);

		if (opcionElegida == 0) {
			Profesor nuevoAMostrar = ProfesorControlador.getInstancia().findPrevious(actual);
			if (nuevoAMostrar == null) {
				nuevoAMostrar = ProfesorControlador.getInstancia().findNext(actual);
			}
			ProfesorControlador.getInstancia().remove(actual);
			JOptionPane.showMessageDialog(this, "Eliminación correcta");

			if (nuevoAMostrar != null) {
				actual = nuevoAMostrar;
			} else {
				panelDatosPersonales.limpiarPantalla();
			}
		}
		return actual;
	}

	/**
	 * 
	 */

	private void cargarDatosActual() {

		if (this.actual != null) {

			// Convertimos el String id a un int id con String.valueOf Char c
			panelDatosPersonales.setId(String.valueOf(this.actual.getId()));
			panelDatosPersonales.setNombre(this.actual.getNombre());
			panelDatosPersonales.setApellido1(this.actual.getApellido1());
			panelDatosPersonales.setApellido2(this.actual.getApellido2());
			panelDatosPersonales.setDni(this.actual.getDni());
			panelDatosPersonales.setDireccion(this.actual.getDireccion());
			panelDatosPersonales.setTelefono(this.actual.getTelefono());
			panelDatosPersonales.setEmail(this.actual.getEmail());
			panelDatosPersonales.setColorElegido(this.actual.getColor());

		}
	}

}
