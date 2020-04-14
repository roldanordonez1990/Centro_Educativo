package ventana;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Materia;
import model.Profesor;
import model.Valoracionmateria;
import model.controladores.MateriaControlador;
import model.controladores.ProfesorControlador;

public class PanelValoracionMasiva extends JPanel {

	// creamos el objeto para poder utilizarlo como puntero

	Valoracionmateria actual = null;

	JComboBox<Profesor> jcbProfesores = new JComboBox<Profesor>();

	JComboBox<Materia> jcbMateria = new JComboBox<Materia>();

	JSlider slider = null;
	
	JLabel label = new JLabel();

	/**
	 * 
	 */

	public PanelValoracionMasiva() {
		this.setLayout(new BorderLayout());
		this.add(construir(), BorderLayout.CENTER);
		this.slider.setValue(5);

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

		// Inclusión del campo "JCBprofesores"

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

		// Inclusión del campo "JCBmateria"

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

		slider = new JSlider(0, 10, 5);
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(10);
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
		
		//Incluimos el JLabel de nota
		
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(2, 2, 2, 2);
		panelGestion.add(new JLabel("Nota: "), c);
		
		
		c.gridx = 3;
		c.gridy = 3;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(2, 2, 2, 2);
		panelGestion.add(label, c);
	

		
		return panelGestion;
	}

}
