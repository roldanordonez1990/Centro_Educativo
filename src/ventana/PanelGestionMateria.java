package ventana;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import model.Curso;
import model.Materia;
import model.controladores.CursoControlador;
import model.controladores.MateriaControlador;
import utils.CacheImagenes;

public class PanelGestionMateria extends JPanel {

	public static int LOAD_FIRST = 0;
	public static int LOAD_PREV = 1;
	public static int LOAD_NEXT = 2;
	public static int LOAD_LAST = 3;
	public static int NEW = 4;
	public static int SAVE = 5;
	public static int REMOVE = 6;

	Materia actual = null;

	JTextField jtfId = new JTextField(5);
	JTextField jtfNombre = new JTextField(20);
	JTextField jtfAcronimo = new JTextField(20);
	JComboBox<Curso> jcbCurso = new JComboBox<Curso>();

	/**
	 * 
	 */

	public PanelGestionMateria() {
		super();
	
		actual = MateriaControlador.getInstancia().findFirst();
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
		panelGestion.add(new JLabel("Nombre: "), c);

		c.gridx = 1;
		c.gridy = 2;
		jtfId.setEnabled(false);
		c.anchor = GridBagConstraints.WEST;
		panelGestion.add(jtfNombre, c);

		// Inclusión del campo "Acrónimo"
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(2, 2, 2, 2);
		panelGestion.add(new JLabel("Acrónimo: "), c);

		c.gridx = 1;
		c.gridy = 3;
		jtfId.setEnabled(false);
		c.anchor = GridBagConstraints.WEST;
		panelGestion.add(jtfAcronimo, c);

		// Inclusión del campo "Descripción Curso"
		List<Curso> listaCursos = CursoControlador.getInstancia().findAllCursos();

		for (Curso cu : listaCursos) {
			jcbCurso.addItem(cu);
		}
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.EAST;
		panelGestion.add(new JLabel("Curso: "), c);

		c.gridx = 1;
		c.gridy = 4;
		c.anchor = GridBagConstraints.WEST;
		panelGestion.add(jcbCurso, c);

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

		//barra.setLayout(new FlowLayout());
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

				Materia obtenido = null;
				if (funcion == LOAD_FIRST)
					obtenido = MateriaControlador.getInstancia().findFirst();
				if (funcion == LOAD_PREV)
					obtenido = MateriaControlador.getInstancia().findPrevious(actual);
				if (funcion == LOAD_NEXT)
					obtenido = MateriaControlador.getInstancia().findNext(actual);
				if (funcion == LOAD_LAST)
					obtenido = MateriaControlador.getInstancia().findLast();
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
		this.jtfNombre.setText("");
		this.jtfAcronimo.setText("");
		this.jcbCurso.setSelectedIndex(0);

	}

	/**
	 * 
	 */

	private void guardar() throws ParseException {
		Materia nuevoRegistro = new Materia();

		if (this.jtfId.getText().trim().equals(""))
			// esto sirve para guardar nuevos registros, por eso es 0
			nuevoRegistro.setId(0);
		else
			// este sirve para guardar registros que ya existen (modificados)
			nuevoRegistro.setId(Integer.parseInt(this.jtfId.getText()));

		nuevoRegistro.setNombre(this.jtfNombre.getText());
		nuevoRegistro.setAcronimo(this.jtfAcronimo.getText());
		nuevoRegistro.setCurso((Curso) this.jcbCurso.getSelectedItem());

		if (nuevoRegistro.getId() == 0) {
			// persist para nuevo identificador
			MateriaControlador.getInstancia().persist(nuevoRegistro);
		} else {
			// merge para cuando hay una modificación (update)
			MateriaControlador.getInstancia().merge(nuevoRegistro);
		}

		this.jtfId.setText("" + nuevoRegistro.getId());
		JOptionPane.showMessageDialog(this, "Guardado correctamente");

		this.actual = nuevoRegistro;
	}

	/**
	 * 
	 */

	private Materia eliminar() {
		String respuestas[] = new String[] { "Sí", "No" };
		int opcionElegida = JOptionPane.showOptionDialog(null, "¿Realmente desea eliminar el registro?",
				"Eliminación del registro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.OK_CANCEL_OPTION,
				CacheImagenes.getCacheImagenes().getIcono("confirm.png"), respuestas, respuestas[1]);

		if (opcionElegida == 0) {
			Materia nuevoAMostrar = MateriaControlador.getInstancia().findPrevious(actual);
			if (nuevoAMostrar == null) {
				nuevoAMostrar = MateriaControlador.getInstancia().findNext(actual);
			}
			MateriaControlador.getInstancia().remove(actual);
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
			this.jtfNombre.setText("" + this.actual.getNombre());
			this.jtfAcronimo.setText("" + this.actual.getAcronimo());
			this.jcbCurso.setSelectedItem(this.actual.getCurso());

		}
	}

}
