package ventana;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import model.Curso;
import model.controladores.CursoControlador;
import utils.CacheImagenes;

public class PanelGestionCurso extends JPanel {

	public static int LOAD_FIRST = 0;
	public static int LOAD_PREV = 1;
	public static int LOAD_NEXT = 2;
	public static int LOAD_LAST = 3;
	public static int NEW = 4;
	public static int SAVE = 5;
	public static int REMOVE = 6;

	Curso actual = null;

	private JTextField jtfId = new JTextField(5);
	private JTextField jtfDescripcion = new JTextField(20);

	public PanelGestionCurso() {
		super();
		//this.add(barraHerramientas(), BorderLayout.NORTH);
		actual = CursoControlador.getInstancia().findFirst();
		this.setLayout(new BorderLayout());
		this.add(barraHerramientas(), BorderLayout.NORTH);
		this.add(construir(), BorderLayout.CENTER);
		
		cargarDatosActual();

	}

	/**
	 * 
	 */

	private JPanel construir() {

		JPanel panelGestion = new JPanel();
		
		panelGestion.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		// Inclusión del toolbar en el dialog
//		c.fill = GridBagConstraints.NORTH;
//		c.gridx = 0;
//		c.gridy = 0;
//		c.gridwidth = 2;
//		c.insets = new Insets(10, 10, 0, 0);
//		panelGestion.add(barraHerramientas(), c);

		// Inclusión del campo "Identificador"
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(2, 2, 2, 2);
		panelGestion.add(new JLabel("Identificador: "), c);

		c.gridx = 1;
		c.gridy = 1;
		jtfId.setEnabled(false);
		c.anchor = GridBagConstraints.WEST;
		panelGestion.add(jtfId, c);

		// Inclusión del campo "Descripción"
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(2, 2, 2, 2);
		panelGestion.add(new JLabel("Descripción: "), c);

		c.gridx = 1;
		c.gridy = 2;
		jtfId.setEnabled(false);
		c.anchor = GridBagConstraints.WEST;
		panelGestion.add(jtfDescripcion, c);

		return panelGestion;

	}

	/**
	 * 
	 */
	
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

		barra.setLayout(new FlowLayout());
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

				Curso obtenido = null;
				if (funcion == LOAD_FIRST)
					obtenido = CursoControlador.getInstancia().findFirst();
				if (funcion == LOAD_PREV)
					obtenido = CursoControlador.getInstancia().findPrevious(actual);
				if (funcion == LOAD_NEXT)
					obtenido = CursoControlador.getInstancia().findNext(actual);
				if (funcion == LOAD_LAST)
					obtenido = CursoControlador.getInstancia().findLast();
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
		limpiarPantalla();
		JOptionPane.showMessageDialog(this, "Por favor, introduzca los datos del nuevo registro");
	}

	/**
	 * 
	 */
	private void limpiarPantalla() {
		this.jtfId.setText("");
		this.jtfDescripcion.setText("");

	}

	/**
	 * @throws ParseException
	 * 
	 */
	private void guardar() throws ParseException {
		Curso nuevoRegistro = new Curso();

		if (this.jtfId.getText().trim().equals(""))
			// esto sirve para guardar nuevos registros, por eso es 0
			nuevoRegistro.setId(0);
		else
			// este sirve para guardar registros que ya existen (modificados)
			nuevoRegistro.setId(Integer.parseInt(this.jtfId.getText()));

		nuevoRegistro.setDescripcion(this.jtfDescripcion.getText());

		if (nuevoRegistro.getId() == 0) {
			// persist para nuevo identificador
			CursoControlador.getInstancia().persist(nuevoRegistro);
		} else {
			// merge para cuando hay una modificación (update)
			CursoControlador.getInstancia().merge(nuevoRegistro);
		}

		this.jtfId.setText("" + nuevoRegistro.getId());
		JOptionPane.showMessageDialog(this, "Guardado correctamente");

		this.actual = nuevoRegistro;
	}

	/**
	 * 
	 * @return
	 */

	private Curso eliminar() {
		String respuestas[] = new String[] { "Sí", "No" };
		int opcionElegida = JOptionPane.showOptionDialog(null, "¿Realmente desea eliminar el registro?",
				"Eliminación del registro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.OK_CANCEL_OPTION,
				CacheImagenes.getCacheImagenes().getIcono("confirm.png"), respuestas, respuestas[1]);

		if (opcionElegida == 0) {
			Curso nuevoAMostrar = CursoControlador.getInstancia().findPrevious(actual);
			if (nuevoAMostrar == null) {
				nuevoAMostrar = CursoControlador.getInstancia().findNext(actual);
			}
			CursoControlador.getInstancia().remove(actual);
			JOptionPane.showMessageDialog(this, "Eliminación correcta");

			if (nuevoAMostrar != null) {
				actual = nuevoAMostrar;
			} else {
				limpiarPantalla();
			}
		}
		return actual;
	}

	/**
	 * 
	 */

	private void cargarDatosActual() {

		if (this.actual != null) {
			this.jtfId.setText("" + this.actual.getId());
			this.jtfDescripcion.setText("" + this.actual.getDescripcion());

		}
	}

}
