package ventana;

import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

public class Menu extends JMenuBar{
	
	
	
	public Menu() {
		JMenu entidad = new JMenu("Entidades");
//		
//		String titulos[] = new String [] {"Concesionario", "Fabricante", "Cliente", "Coche", "Ventas"};
//		
//		for (int i = 0; i < titulos.length; i++) {
//			final int iFinal = i;
//			JMenuItem item = new JMenuItem(titulos[i]);
//			item.addActionListener(new ActionListener() {
//				
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					
//					VentanaPrincipal.jtabbetMenu.setSelectedIndex(iFinal);
//				}
//			});
//			
//			gestion.add(item);
//		}
		
		this.add(entidad);
	}

}
