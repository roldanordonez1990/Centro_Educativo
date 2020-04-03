package ventana;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import model.Estudiante;
import model.Materia;
import model.Profesor;
import model.Valoracionmateria;
import model.controladores.EstudianteControlador;
import model.controladores.MateriaControlador;
import model.controladores.ProfesorControlador;
import model.controladores.ValoracionMateriaControlador;

public class PanelGestionValoracionNotas extends JPanel {

	// creamos el objeto para poder utilizarlo como puntero
	Valoracionmateria actual = null;

	JComboBox<Profesor> jcbProfesores = new JComboBox<Profesor>();

	JComboBox<Estudiante> jcbEstudiantes = new JComboBox<Estudiante>();

	JComboBox<Materia> jcbMateria = new JComboBox<Materia>();

	JButton jbtRefrescar = new JButton("Refrescar");

	JScrollPane jspValoracion = new JScrollPane();

	List<EstudianteJSpinner> spinners = new ArrayList<EstudianteJSpinner>();

	JButton jbtGuardarNota = new JButton("Guardar nota");
	
	/**
	 * 
	 */

	public PanelGestionValoracionNotas() {
		this.setLayout(new BorderLayout());
		this.add(construir(), BorderLayout.CENTER);
	}

	/**
	 * 
	 * @return
	 */
	
	public JPanel construir() {

		JPanel panelGestion = new JPanel();

		panelGestion.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		// Inclusión del campo "JCBprofesores"

		List<Profesor> listProfesores = ProfesorControlador.getInstancia().findAllProfesores();

		for (Profesor prof : listProfesores) {
			jcbProfesores.addItem(prof);
		}

		c.fill = GridBagConstraints.NONE;
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

		// Inclusión del campo "JCBestudiantes"

		List<Estudiante> listEstudiantes = EstudianteControlador.getInstancia().findAllEstudiantes();

		for (Estudiante estu : listEstudiantes) {
			jcbEstudiantes.addItem(estu);
		}

		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(2, 2, 2, 2);
		panelGestion.add(new JLabel("Estudiantes: "), c);

		c.gridx = 1;
		c.gridy = 2;
		jcbProfesores.setEnabled(true);
		c.anchor = GridBagConstraints.WEST;
		panelGestion.add(jcbEstudiantes, c);

		// Inclusión del campo "JCBmateria"

		List<Materia> listMaterias = MateriaControlador.getInstancia().findAllMaterias();

		for (Materia ma : listMaterias) {
			jcbMateria.addItem(ma);
		}

		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(2, 2, 2, 2);
		panelGestion.add(new JLabel("Materia: "), c);

		c.gridx = 1;
		c.gridy = 3;
		jcbProfesores.setEnabled(true);
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(2, 2, 2, 2);
		panelGestion.add(jcbMateria, c);

		// Inclusión del botón de refresco

		jbtRefrescar.setPreferredSize(new Dimension(150, 20));
		c.gridx = 1;
		c.gridy = 4;
		c.anchor = GridBagConstraints.WEST;
		panelGestion.add(jbtRefrescar, c);

		jbtRefrescar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Cuando le damos al botón de refrescar, nos aparecerá el getPanelAlumno,
				// que es un JPanel dentro del ScrollPane
				jspValoracion.setViewportView(getPanelAlumno());
			}
		});

		// Añadimos el ScrollPane
		c.gridx = 1;
		c.gridy = 5;
		jspValoracion.setEnabled(true);
		c.anchor = GridBagConstraints.WEST;
		// Le damos unas dimensiones al scroll
		jspValoracion.setPreferredSize(new Dimension(300, 300));
		panelGestion.add(jspValoracion, c);

		// Inclusión del botón de guardar
		jbtGuardarNota.setPreferredSize(new Dimension(150, 20));
		c.gridx = 1;
		c.gridy = 6;
		c.anchor = GridBagConstraints.WEST;
		panelGestion.add(jbtGuardarNota, c);

		jbtGuardarNota.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (spinners != null) {
					Profesor p = (Profesor) jcbProfesores.getSelectedItem();
					Materia m = (Materia) jcbMateria.getSelectedItem();
					for (EstudianteJSpinner spinner : spinners) {
						Valoracionmateria valoracion = ValoracionMateriaControlador.getInstancia()
								.findByEstudianteAndProfesorAndMateria(p, m, spinner.estudiante);
						if (valoracion != null) {
							valoracion.setValoracion(((Integer) spinner.getValue()).floatValue());
							ValoracionMateriaControlador.getInstancia().merge(valoracion);
						} else {
							Valoracionmateria v = new Valoracionmateria();
							v.setEstudiante(spinner.estudiante);
							v.setMateria(m);
							v.setProfesor(p);
							ValoracionMateriaControlador.getInstancia().persist(v);
						}

					}
				}

			}
		});

		return panelGestion;

	}

	/**
	 * Aquí están los alumnos que irán dentro del ScrollPane
	 */

	public JPanel getPanelAlumno() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		int i = 1;
		this.spinners.clear();
		List<Estudiante> estudiantes = EstudianteControlador.getInstancia().findAllEstudiantes();
		for (Estudiante est : estudiantes) {

			c.fill = GridBagConstraints.BOTH;
			c.gridx = 0;
			c.gridy = 0 + i;
			c.gridwidth = 1;
			c.anchor = GridBagConstraints.EAST;
			c.insets = new Insets(2, 2, 2, 2);
			panel.add(new JLabel(est.toString()), c);

			c.gridx = 1;
			c.gridy = 0 + i;
			c.anchor = GridBagConstraints.WEST;
			EstudianteJSpinner spinner = new EstudianteJSpinner(est);
			this.spinners.add(spinner);

			// Usamos la valoración para cargar la nota del alumno en el Spinner
			Valoracionmateria valoracion = ValoracionMateriaControlador.getInstancia()
					.findByEstudianteAndProfesorAndMateria((Profesor) this.jcbProfesores.getSelectedItem(),
							(Materia) this.jcbMateria.getSelectedItem(), est);
			if (valoracion != null) {
				spinner.setValue(new Integer((int) valoracion.getValoracion()));
				// en el spinner aparece la valoración casteada porque es float y el spinner
				// solo admite enteros
			}
			panel.add(spinner, c);
			i++;
		}

		return panel;
	}

	/**
	 * Se crea esta clase interna para poder usarla en las notas. La clase extiende
	 * de JSpinner
	 */

	private class EstudianteJSpinner extends JSpinner {

		Estudiante estudiante = null;

		public EstudianteJSpinner(Estudiante estudiante) {
			super();
			this.estudiante = estudiante;

		}

	}

}
