package model.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Controlador;
import model.Entidad;
import model.Estudiante;

public class EstudianteControlador extends Controlador {

	// instancia del singleton
	private static EstudianteControlador instancia = null;

	/**
	 * 
	 */
	public EstudianteControlador() {
		super(Estudiante.class, "EvaluacionCentroEducativo");
	}

	/**
	 * 
	 * @return
	 */
	public static EstudianteControlador getInstancia() {
		if (instancia == null) {
			instancia = new EstudianteControlador();
		}
		return instancia;
	}

	@Override
	public Estudiante findFirst() {
		return (Estudiante) super.findFirst();
	}

	@Override
	public Estudiante findLast() {
		return (Estudiante) super.findLast();
	}

	@Override
	public Estudiante findNext(Entidad e) {
		return (Estudiante) super.findNext(e);
	}

	@Override
	public Estudiante findPrevious(Entidad e) {
		return (Estudiante) super.findPrevious(e);
	}
	
	
	/**
	 * 
	 */
	public List<Estudiante> findAllEstudiantes() {
		List<Estudiante> resultado = new ArrayList<Estudiante>();
		EntityManager em = getEntityManagerFactory().createEntityManager();
		
		try {
			Query q = em.createNativeQuery("Select * from estudiante", Estudiante.class);
			resultado = (List<Estudiante>) q.getResultList();
		} catch (Exception e) {
		}
		em.close();
		return resultado;
	}

	
}
