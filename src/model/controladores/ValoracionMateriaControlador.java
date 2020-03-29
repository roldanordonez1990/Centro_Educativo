package model.controladores;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Controlador;
import model.Entidad;
import model.Estudiante;
import model.Materia;
import model.Profesor;
import model.Valoracionmateria;

public class ValoracionMateriaControlador extends Controlador {

	// instancia del singleton
	private static ValoracionMateriaControlador instancia = null;

	/**
	 * 
	 */
	public ValoracionMateriaControlador() {
		super(Valoracionmateria.class, "Centro_Educativo");
	}

	/**
	 * 
	 * @return
	 */
	public static ValoracionMateriaControlador getInstancia() {
		if (instancia == null) {
			instancia = new ValoracionMateriaControlador();
		}
		return instancia;
	}

	@Override
	public Valoracionmateria findFirst() {
		return (Valoracionmateria) super.findFirst();
	}

	@Override
	public Valoracionmateria findLast() {
		return (Valoracionmateria) super.findLast();
	}

	@Override
	public Valoracionmateria findNext(Entidad e) {
		return (Valoracionmateria) super.findNext(e);
	}

	@Override
	public Valoracionmateria findPrevious(Entidad e) {
		return (Valoracionmateria) super.findPrevious(e);
	}
	
	
	/**
	 * 
	 * @param profesor
	 * @param materia
	 * @param estudiante
	 * @return
	 */
	public Valoracionmateria findByEstudianteAndProfesorAndMateria (Profesor profesor, Materia materia, Estudiante estudiante) {
		Valoracionmateria resultado = null;
		EntityManager em = this.getEntityManagerFactory().createEntityManager();
		try {
			Query q = em.createNativeQuery("Select * from valoracionmateria where idProfesor = ? and "
					+ "idMateria = ? and idEstudiante = ?", Valoracionmateria.class);
			q.setParameter(1, profesor.getId());
			q.setParameter(2, materia.getId());
			q.setParameter(3, estudiante.getId());
			resultado = (Valoracionmateria) q.getSingleResult();
		}
		catch (Exception ex) {
		}
		em.close();
		return resultado;
	}
	
}
