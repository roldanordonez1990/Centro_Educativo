package ventana;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Estudiante;
import model.Materia;
import model.Profesor;
import model.Valoracionmateria;
import model.controladores.EstudianteControlador;
import model.controladores.MateriaControlador;
import model.controladores.ProfesorControlador;
import model.controladores.ValoracionMateriaControlador;

public class PanelValoracionMasiva extends JPanel {

	private static final int MAX = 10;
	private static final int MIN = 0;
	private static final int DEFAULT = 5;
	// creamos el objeto para poder utilizarlo como puntero

	Valoracionmateria actual = null;

	JComboBox<Profesor> jcbProfesores = new JComboBox<Profesor>();

	JComboBox<Materia> jcbMateria = new JComboBox<Materia>();

	JSlider slider = null;

	JLabel label = new JLabel();

	JButton jbtActualizar = new JButton("Actualizar alumnado");

	JButton jbtAnteriorTodos = new JButton("<<");

	JButton jbtAnteriorUno = new JButton("<");

	JButton jbtPosteriorTodos = new JButton(">>");

	JButton jbtPosteriorUno = new JButton(">");

	JButton jbtGuardar = new JButton("Guardar cambios");

	// Modelo del elemento JList, necesario para que podamos agregar y eliminar
	// elementos
	private DefaultListModel<Estudiante> listModelAlumnosDisponibles = new DefaultListModel<Estudiante>();
	// Lista de todas los Alumnos de la BBDD, para incluir en el elemento JList
	private JList jlistAlumnosDisponibles = new JList<Estudiante>(listModelAlumnosDisponibles);
	// A�adimos el JList de alumnos al Scroll
	private JScrollPane jspIzq = new JScrollPane(jlistAlumnosDisponibles);

	private DefaultListModel<Estudiante> listModelAlumnosSeleccionados = new DefaultListModel<Estudiante>();
	private JList jlistAlumnosSeleccionados = new JList<Estudiante>(listModelAlumnosSeleccionados);
	private JScrollPane jspDch = new JScrollPane(jlistAlumnosSeleccionados);

	/**
	 * 
	 */

	public PanelValoracionMasiva() {
		this.setLayout(new BorderLayout());
		this.add(construir(), BorderLayout.CENTER);

		this.slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				label.setText(String.valueOf(slider.getValue()));
			}
		});

	}

	/**
	 * 
	 */

	public JPanel construir() {

		JPanel panelGestion = new JPanel();

		panelGestion.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		// Inclusi�n del campo "JCBprofesores"

		List<Profesor> listProfesores = ProfesorControlador.getInstancia().findAllProfesores();

		for (Profesor prof : listProfesores) {
			jcbProfesores.addItem(prof);
		}

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(2, 2, 2, 2);
		panelGestion.add(new JLabel("Profesores: "), c);

		c.gridx = 1;
		c.gridy = 1;
		jcbProfesores.setEnabled(true);
		c.anchor = GridBagConstraints.WEST;
		panelGestion.add(jcbProfesores, c);

		// Inclusi�n del campo "JCBmateria"

		List<Materia> listMaterias = MateriaControlador.getInstancia().findAllMaterias();

		for (Materia ma : listMaterias) {
			jcbMateria.addItem(ma);
		}

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(2, 2, 2, 2);
		panelGestion.add(new JLabel("Materia: "), c);

		c.gridx = 1;
		c.gridy = 2;
		jcbProfesores.setEnabled(true);
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(2, 2, 2, 2);
		panelGestion.add(jcbMateria, c);

		// Incluimos el Slider

		slider = new JSlider(MIN, MAX, DEFAULT);
		slider.setMinorTickSpacing(MIN);
		slider.setMajorTickSpacing(MAX);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(2, 2, 2, 2);
		panelGestion.add(new JLabel("Nota: "), c);

		c.gridx = 1;
		c.gridy = 3;
		jcbProfesores.setEnabled(true);
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(2, 2, 2, 2);
		panelGestion.add(slider, c);

		// Incluimos el JLabel de nota

		// c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(2, 2, 2, 2);
		panelGestion.add(new JLabel("Nota: "), c);

		c.gridx = 3;
		c.gridy = 3;
		c.insets = new Insets(0, 0, 0, 20);
		c.anchor = GridBagConstraints.EAST;
		panelGestion.add(label, c);

		// c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(2, 2, 2, 2);
		panelGestion.add(new JLabel("Fecha: "), c);

		c.gridx = 1;
		c.gridy = 4;
		c.anchor = GridBagConstraints.WEST;
		panelGestion.add(getJFormattedTextFieldDatePersonalizado(), c);

		// Inclusi�n del bot�n de actualizar lista
		c.fill = GridBagConstraints.CENTER;
		jbtActualizar.setPreferredSize(new Dimension(150, 20));
		c.gridx = 1;
		c.gridy = 5;
		c.anchor = GridBagConstraints.CENTER;
		panelGestion.add(jbtActualizar, c);

		jbtActualizar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// AL pulsar el bot�n, cargaremos la lista de alumnos dentro de la
				// listModelAlumnosDisponibles
				listModelAlumnosDisponibles.removeAllElements();
				List<Estudiante> listAlumnos = EstudianteControlador.getInstancia().findAllEstudiantes();

				for (Estudiante estu : listAlumnos) {
					listModelAlumnosDisponibles.addElement(estu);
				}
			}

		});

		// Inclusi�n del panel con las listas y los botones
		c.gridx = 1;
		c.gridy = 6;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.CENTER;
		panelGestion.add(panelScrollListas(), c);

		c.fill = GridBagConstraints.CENTER;
		jbtGuardar.setPreferredSize(new Dimension(120, 20));
		c.gridx = 1;
		c.gridy = 7;
		c.anchor = GridBagConstraints.CENTER;
		panelGestion.add(jbtGuardar, c);

		jbtGuardar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Profesor p = (Profesor) jcbProfesores.getSelectedItem();
				Materia m = (Materia) jcbMateria.getSelectedItem();

				//Inicializo una lista de los estudiantes
				List<Estudiante> listaEstudiantes = new ArrayList<Estudiante>();

				//Recorro los estudiantes que est�n seleccionados
				for (int i = 0; i < listModelAlumnosSeleccionados.getSize(); i++) {
						
						//A�ado a la lista de estudiantes el valor i de los seleccionados
						listaEstudiantes.add(listModelAlumnosSeleccionados.get(i));
					
						//Recorro la nueva lista anteriormente a�adida con los estudiantes escogidos
					for (Estudiante es : listaEstudiantes) {
						//La valoraci�n de la nota coincidir� con el Profesor, la Materia y el Estudiante
						Valoracionmateria valoracion = ValoracionMateriaControlador.getInstancia()
								.findByEstudianteAndProfesorAndMateria(p, m, es);
						//Si la valoraci�n ya existe, a�adiremos los cambios
						if (valoracion != null) {
							valoracion.setValoracion(slider.getValue());
							valoracion.setFecha((Date) getJFormattedTextFieldDatePersonalizado().getValue());
							ValoracionMateriaControlador.getInstancia().merge(valoracion);
							
						//Si la valoraci�n de nota es nueva, a�adiremos la nueva nota
						} else {
							Valoracionmateria v = new Valoracionmateria();
							v.setEstudiante(es);
							v.setMateria(m);
							v.setProfesor(p);
							v.setFecha((Date) getJFormattedTextFieldDatePersonalizado().getValue());
							v.setValoracion(slider.getValue());
							ValoracionMateriaControlador.getInstancia().persist(v);

						}
					}
				}
			}
		});

		return panelGestion;

	}

	public JPanel getjpBotones() {

		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new GridBagLayout());
		GridBagConstraints b = new GridBagConstraints();

		jbtPosteriorTodos.setPreferredSize(new Dimension(50, 20));
		b.gridx = 0;
		b.gridy = 0;
		b.anchor = GridBagConstraints.CENTER;
		panelBotones.add(jbtPosteriorTodos, b);

		jbtPosteriorTodos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Recorremos la listModelDisponibles
				for (int i = 0; i < listModelAlumnosDisponibles.getSize(); i++) {

					// A la lista de seleccionados le a�adimos los disponibles
					listModelAlumnosSeleccionados.addElement(listModelAlumnosDisponibles.elementAt(i));
				}
				// Una vez acabado el bucle despu�s de haber recorrido todos los alumnos,
				// eliminamos toda la lista de los disponibles
				listModelAlumnosDisponibles.removeAllElements();
			}
		});

		// b.fill = GridBagConstraints.CENTER;
		jbtPosteriorUno.setPreferredSize(new Dimension(50, 20));
		b.gridx = 0;
		b.gridy = 1;
		b.anchor = GridBagConstraints.CENTER;
		panelBotones.add(jbtPosteriorUno, b);

		jbtPosteriorUno.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Guardamos los alumnos de la Jlist en un array, seleccionando sus �ndices
				int indiceAlumnosSeleccionados[] = jlistAlumnosDisponibles.getSelectedIndices();
				// Se hace el recorrido as� para que siga un orden correcto
				for (int i = indiceAlumnosSeleccionados.length - 1; i > -1; i--) {
					Estudiante es = listModelAlumnosDisponibles.elementAt(indiceAlumnosSeleccionados[i]);

					// Se a�adir�n a la listModel el que hayamos seleccionado
					listModelAlumnosSeleccionados.addElement(es);
					// Y a su vez saldr� de la listModelAlumnosDisponibles donde hab�a aparecido al
					// principio
					listModelAlumnosDisponibles.removeElement(es);
				}
			}
		});

		// b.fill = GridBagConstraints.CENTER;
		jbtAnteriorUno.setPreferredSize(new Dimension(50, 20));
		b.gridx = 0;
		b.gridy = 2;
		b.anchor = GridBagConstraints.CENTER;
		panelBotones.add(jbtAnteriorUno, b);

		jbtAnteriorUno.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Guardamos los alumnos de la Jlist en un array, seleccionando sus �ndices
				int indiceAlumnosDisponible[] = jlistAlumnosSeleccionados.getSelectedIndices();
				for (int i = indiceAlumnosDisponible.length - 1; i > -1; i--) {
					Estudiante es = listModelAlumnosSeleccionados.elementAt(indiceAlumnosDisponible[i]);

					// Se a�adir�n a la listModel el que hayamos seleccionado
					listModelAlumnosSeleccionados.removeElement(es);
					// Y a su vez saldr� de la listModelAlumnosDisponibles donde hab�a aparecido al
					// principio
					listModelAlumnosDisponibles.addElement(es);

				}
			}
		});

		// b.fill = GridBagConstraints.CENTER;
		jbtAnteriorTodos.setPreferredSize(new Dimension(50, 20));
		b.gridx = 0;
		b.gridy = 3;
		b.anchor = GridBagConstraints.CENTER;
		panelBotones.add(jbtAnteriorTodos, b);

		jbtAnteriorTodos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Recorremos la listModelDisponibles
				for (int i = 0; i < listModelAlumnosSeleccionados.getSize(); i++) {

					// A la lista de seleccionados le a�adimos los disponibles
					listModelAlumnosDisponibles.addElement(listModelAlumnosSeleccionados.elementAt(i));
				}
				// Una vez acabado el bucle despu�s de haber recorrido todos los alumnos,
				// eliminamos toda la lista de los disponibles
				listModelAlumnosSeleccionados.removeAllElements();

			}

		});

		return panelBotones;

	}

	public JPanel panelScrollListas() {

		JPanel panelListas = new JPanel();

		panelListas.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		// A�adimos los dos scroll con las jlist incorporadas y el panel de botones

		c.gridwidth = 1;
		c.insets = new Insets(2, 2, 2, 2);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		// c.weighty = 1;
		jspIzq.setPreferredSize(new Dimension(220, 250));
		c.anchor = GridBagConstraints.WEST;
		panelListas.add(jspIzq, c);

		c.gridwidth = 1;
		c.insets = new Insets(2, 2, 2, 2);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		// c.weighty = 1;
		c.anchor = GridBagConstraints.CENTER;
		panelListas.add(getjpBotones(), c);

		c.gridwidth = 1;
		c.insets = new Insets(2, 2, 2, 2);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 0;
		// c.weighty = 1;
		jspDch.setPreferredSize(new Dimension(220, 250));
		c.anchor = GridBagConstraints.EAST;
		panelListas.add(jspDch, c);

		return panelListas;

	}

	/**
	 * 
	 * @return M�todo para el campo fecha
	 */
	private JFormattedTextField getJFormattedTextFieldDatePersonalizado() {
		JFormattedTextField jftf = new JFormattedTextField(new JFormattedTextField.AbstractFormatter() {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			@Override
			public String valueToString(Object value) throws ParseException {
				if (value != null && value instanceof Date) {
					return sdf.format(((Date) value));
				}
				return "";
			}

			@Override
			public Object stringToValue(String text) throws ParseException {
				try {
					return sdf.parse(text);
				} catch (Exception e) {
					return null;
				}
			}
		});
		jftf.setColumns(20);
		jftf.setValue(new Date());
		return jftf;
	}

}
