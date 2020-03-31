package ventana;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import model.Tipologiasexo;
import model.controladores.TipologiaSexoControlador;

public class PanelDatosPersonales extends JPanel {

	JTextField jtfId = new JTextField(10);
	JTextField jtfNombre = new JTextField(20);
	JTextField jtfApellido1 = new JTextField(20);
	JTextField jtfApellido2 = new JTextField(20);
	JTextField jtfDni = new JTextField(9);
	JTextField jtfDireccion = new JTextField(20);
	JTextField jtfEmail = new JTextField(20);
	JTextField jtfTelefono = new JTextField(9);
	JComboBox<Tipologiasexo> jcbSexo = new JComboBox<Tipologiasexo>();
	JScrollPane jsp = new JScrollPane(new JLabel());
	byte[] imagen;
	JButton jbtCambiarImg;
	// JLabel jlblColorElegido = new JLabel();
	JTextField jtfColorPreferido = new JTextField(20);
	JButton jbtElegirColor = new JButton("Elegir el color");
	JColorChooser jColorChooser;
	JFileChooser jfileChooser = new JFileChooser();
	// JPanel jpParaColorear = new JPanel();

	/**
	 * 
	 * @return
	 */

	public PanelDatosPersonales() {

		// this.add(panelDeGestionDatosPersonales(), BorderLayout.CENTER);

		// JPanel panelGestion = new JPanel();

		this.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		// Inclusión del campo "Id"
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(2, 2, 2, 2); // Son los márgenes
		this.add(new JLabel("Id: "), c);

		c.gridx = 1;
		c.gridy = 1;
		jtfId.setEnabled(false);
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfId, c);

		// Inclusión del campo "Nombre"
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(2, 2, 2, 2);
		this.add(new JLabel("Nombre: "), c);

		c.gridx = 1;
		c.gridy = 2;
		jtfNombre.setEnabled(true);
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfNombre, c);

		// Inclusión del campo "Primer apelldo"
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Primer Apellido: "), c);

		c.gridx = 1;
		c.gridy = 3;
		jtfApellido1.setEnabled(true);
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfApellido1, c);

		// Inclusión del campo "Segundo apellido"
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Segundo Apellido: "), c);

		c.gridx = 1;
		c.gridy = 4;
		jtfApellido2.setEnabled(true);
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfApellido2, c);

		// Inclusión del campo "Dni"
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Dni: "), c);

		c.gridx = 1;
		c.gridy = 5;
		jtfDni.setEnabled(true);
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfDni, c);

		// Inclusión del campo "Dirección"
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Dirección: "), c);

		c.gridx = 1;
		c.gridy = 6;
		jtfDireccion.setEnabled(true);
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfDireccion, c);

		// Inclusión del campo "Teléfono"
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Teléfono: "), c);

		c.gridx = 1;
		c.gridy = 7;
		jtfTelefono.setEnabled(true);
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfTelefono, c);

		// Inclusión del campo "Email"
		c.gridx = 0;
		c.gridy = 8;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Email: "), c);

		c.gridx = 1;
		c.gridy = 8;
		jtfEmail.setEnabled(true);
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfEmail, c);

		// incluimos JComboBox para el Sexo
		List<Tipologiasexo> tipo = TipologiaSexoControlador.getInstancia().findAllTipologiaSexo();
		for (Tipologiasexo ti : tipo) {
			jcbSexo.addItem(ti);
		}
		c.gridx = 0;
		c.gridy = 9;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Sexo: "), c);

		c.gridx = 1;
		c.gridy = 9;
		c.anchor = GridBagConstraints.WEST;
		this.add(jcbSexo, c);

		// Inclusión del color
		c.gridx = 0;
		c.gridy = 10;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Color preferido: "), c);

		c.gridx = 1;
		c.gridy = 10;
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfColorPreferido, c);

		c.gridx = 2;
		c.gridy = 10;
		c.anchor = GridBagConstraints.WEST;
		this.add(jbtElegirColor, c);

		jbtElegirColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelColorChooser();

			}

		});

		c.gridx = 1;
		c.gridy = 11;
		jtfDireccion.setEnabled(true);
		c.anchor = GridBagConstraints.WEST;
		// Le damos unas dimensiones al scroll
		jsp.setPreferredSize(new Dimension(150, 150));
		this.add(jsp, c);

		jbtCambiarImg = new JButton("Elegir imagen");
		jbtCambiarImg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				seleccionaFichero(getImagen());
			}
		});

		// Le damos las dimensiones al botón
		jbtCambiarImg.setPreferredSize(new Dimension(150, 20));
		c.gridx = 1;
		c.gridy = 12;
		c.anchor = GridBagConstraints.WEST;
		this.add(jbtCambiarImg, c);

	}

	/**
	 * 
	 * @return
	 */

	public String getId() {
		return jtfId.getText();
	}

	public void setId(String id) {
		this.jtfId.setText(id);
	}

	public String getNombre() {
		return this.jtfNombre.getText();
	}

	public void setNombre(String nombre) {
		this.jtfNombre.setText(nombre);
	}

	public String getApellido1() {
		return this.jtfApellido1.getText();
	}

	public void setApellido1(String apellido1) {
		this.jtfApellido1.setText(apellido1);
	}

	public String getApellido2() {
		return this.jtfApellido2.getText();
	}

	public void setApellido2(String apellido2) {
		this.jtfApellido2.setText(apellido2);
	}

	public String getDireccion() {
		return this.jtfDireccion.getText();
	}

	public void setDireccion(String direccion) {
		this.jtfDireccion.setText(direccion);
	}

	public String getDni() {
		return this.jtfDni.getText();
	}

	public void setDni(String dni) {
		this.jtfDni.setText(dni);
	}

	public String getEmail() {
		return this.jtfEmail.getText();
	}

	public void setEmail(String email) {
		this.jtfEmail.setText(email);
	}

	public String getTelefono() {
		return this.jtfTelefono.getText();
	}

	public void setTelefono(String telefono) {
		this.jtfTelefono.setText(telefono);
	}

	public Tipologiasexo getTipologiaSexo() {
		return (Tipologiasexo) this.jcbSexo.getSelectedItem();
	}

	public void setTipologiaSexo(Tipologiasexo sexoEscogido) {
		if (sexoEscogido == null && this.jcbSexo.getItemCount() > 0) {
			this.jcbSexo.setSelectedIndex(0);
		} else {
			for (int i = 0; i < this.jcbSexo.getItemCount(); i++) {
				Tipologiasexo sexoEnJCombo = this.jcbSexo.getItemAt(i);
				if (sexoEscogido.getId() == sexoEnJCombo.getId()) {
					this.jcbSexo.setSelectedIndex(i);
				}
			}
		}
	}

	public String getColorElegido() {
		return jtfColorPreferido.getText();
	}

	public void setColorElegido(String colorElegido) {
		this.jtfColorPreferido.setText(colorElegido);
		try {
			this.setBackground(Color.decode(colorElegido));
		} catch (Exception e) {
			System.out.println("No cambio");
			this.setBackground(Color.GRAY);
		}
	}

	public byte[] getImagen() {
		return imagen;
	}

	/**
	 * 
	 */

	public void seleccionarImagen() {
		this.jfileChooser = new JFileChooser();

	}

	/**
	 * 
	 */

	public void panelColorChooser() {

		Color color = jColorChooser.showDialog(null, "Selecciona el color que deseas", Color.GRAY);

		if (color != null) {
			String strColor = "#" + Integer.toHexString(color.getRGB()).substring(2);
			this.jtfColorPreferido.setText(strColor);

			this.setBackground(color);

		}

	}

	/**
	 * 
	 */

	protected JScrollPane scrollPane(String imagen) {

		JLabel jlb = new JLabel(CacheImagenes.getCacheImagenes().getIcono(imagen));

		JScrollPane jsp = new JScrollPane(jlb);
		return jsp;

	}

	/**
	 * 
	 * @return
	 */

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
		if (imagen != null && imagen.length > 0) {
			ImageIcon icono = new ImageIcon(this.imagen);
			JLabel jlblIcono = new JLabel(icono);
			jlblIcono.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseClicked(e);
					this.mostrarMenu(e);
				}

				@Override
				public void mouseDragged(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseDragged(e);
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseEntered(e);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseExited(e);
				}

				@Override
				public void mouseMoved(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseMoved(e);
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mousePressed(e);
					this.mostrarMenu(e);
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseReleased(e);
					this.mostrarMenu(e);
				}

				@Override
				public void mouseWheelMoved(MouseWheelEvent e) {
					// TODO Auto-generated method stub
					super.mouseWheelMoved(e);
				}

				private void mostrarMenu(MouseEvent e) {
					if (e.isPopupTrigger()) {
						JPopupMenu menu = new JPopupMenu();
						menu.add(new JMenuItem(icono.getIconWidth() + "x" + icono.getIconHeight() + "pixeles"));
						menu.addSeparator();
						JMenuItem miImagenSeleccionada = new JMenuItem("Seleccionar una imagen");
						miImagenSeleccionada.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								// seleccionarImagen();
								seleccionaFichero(getImagen());

							}
						});
						menu.add(miImagenSeleccionada);
						menu.show(e.getComponent(), e.getX(), e.getY());
					}
				}

			});
			this.jsp.setViewportView(jlblIcono);
		} else {
			JLabel jlblIcono = new JLabel("No hay imagen");
			this.jsp.setViewportView(jlblIcono);
		}
	}

	/**
	 * @return
	 * 
	 */

	private byte[] seleccionaFichero(byte[] imagenActual) {
		this.jfileChooser = new JFileChooser();
		byte[] imagenSeleccionada = null;

		// Configurando el componente

		// Establecimiento de la carpeta de inicio
		this.jfileChooser.setCurrentDirectory(new File("C:\\"));

		// Tipo de selección que se hace en el diálogo
		this.jfileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // Sólo selecciona ficheros
		// this.jfileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); //
		// Sólo selecciona ficheros
		// this.jfileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		// // Selecciona ficheros y carpetas

		// Filtro del tipo de ficheros que puede abrir
		this.jfileChooser.setFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				return "Archivos de imagen *.jpg , .png , .jpeg o .gif";
			}

			@Override
			public boolean accept(File f) {
				if (f.isFile() && f.getAbsolutePath().endsWith(".jpg") || f.getAbsolutePath().endsWith(".png")
						|| f.getAbsolutePath().endsWith(".jpeg") || f.getAbsolutePath().endsWith(".gif"))
					;

				return true;

			}
		});

		// Abro el diálogo para la elección del usuario
		int seleccionUsuario = jfileChooser.showOpenDialog(null);

		if (seleccionUsuario == JFileChooser.APPROVE_OPTION) {
			File fichero = this.jfileChooser.getSelectedFile();

			if (fichero.isFile()) {
				try {
					imagenSeleccionada = Files.readAllBytes(fichero.toPath());
					ImageIcon imagenProvisional = new ImageIcon(imagenSeleccionada);
					if (imagenProvisional.getIconWidth() > 800 || imagenProvisional.getIconHeight() > 800) {
						JOptionPane.showMessageDialog(null, "La imagen es demasiado grande");
						return imagenActual;
					}
					setImagen(imagenSeleccionada);
					return imagenSeleccionada;

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		imagenSeleccionada = imagenActual;

		return imagenActual;
	}

	/**
	 * 
	 */
	public void limpiarPantalla() {

		this.jtfNombre.setText("");
		this.jtfNombre.setText("");
		this.jtfApellido1.setText("");
		this.jtfApellido2.setText("");
		this.jtfDni.setText("");
		this.jtfDireccion.setText("");
		this.jtfTelefono.setText("");
		this.jtfEmail.setText("");
		this.jcbSexo.setSelectedIndex(0);
		// reininciar de nuevo el color a peporque no sabía cómo hacer que saliera por
		// defecto de nuevo el colo gris
		this.setBackground(Color.gray);
		//ponemos el sroll de la imagen vacío
		this.jsp.setViewportView(null);
		
	}

}
