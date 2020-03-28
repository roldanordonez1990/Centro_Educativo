package model.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import model.Controlador;
import model.Entidad;
import model.Profesor;

public class ProfesorControlador extends Controlador {

	// instancia del singleton
	private static ProfesorControlador instancia = null;

	/**
	 * 
	 */
	public ProfesorControlador() {
		super(Profesor.class, "EvaluacionCentroEducativo");
	}

	/**
	 * 
	 * @return
	 */
	public static ProfesorControlador getInstancia() {
		if (instancia == null) {
			instancia = new ProfesorControlador();
		}
		return instancia;
	}

	@Override
	public Profesor findFirst() {
		return (Profesor) super.findFirst();
	}

	@Override
	public Profesor findLast() {
		return (Profesor) super.findLast();
	}

	@Override
	public Profesor findNext(Entidad e) {
		return (Profesor) super.findNext(e);
	}

	@Override
	public Profesor findPrevious(Entidad e) {
		return (Profesor) super.findPrevious(e);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Profesor> findAllProfesores () {
		List<Profesor> entities = new ArrayList<Profesor>();
		EntityManager em = getEntityManagerFactory().createEntityManager();
		try {			
			Query q = em.createNativeQuery("SELECT * FROM profesor", Profesor.class);
			entities = (List<Profesor>) q.getResultList();
		}
		catch (NoResultException nrEx) {
		}
		em.close();
		return entities;
	}
		
}
