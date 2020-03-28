package model.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import model.Controlador;
import model.Entidad;
import model.Materia;

public class MateriaControlador extends Controlador {

	// instancia del singleton
	private static MateriaControlador instancia = null;

	/**
	 * 
	 */
	public MateriaControlador() {
		super(Materia.class, "EvaluacionCentroEducativo");
	}

	/**
	 * 
	 * @return
	 */
	public static MateriaControlador getInstancia() {
		if (instancia == null) {
			instancia = new MateriaControlador();
		}
		return instancia;
	}

	@Override
	public Materia findFirst() {
		return (Materia) super.findFirst();
	}

	@Override
	public Materia findLast() {
		return (Materia) super.findLast();
	}

	@Override
	public Materia findNext(Entidad e) {
		return (Materia) super.findNext(e);
	}

	@Override
	public Materia findPrevious(Entidad e) {
		return (Materia) super.findPrevious(e);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Materia> findAllMaterias () {
		List<Materia> entities = new ArrayList<Materia>();
		EntityManager em = getEntityManagerFactory().createEntityManager();
		try {			
			Query q = em.createNativeQuery("SELECT * FROM materia", Materia.class);
			entities = (List<Materia>) q.getResultList();
		}
		catch (NoResultException nrEx) {
		}
		em.close();
		return entities;
	}
		
}
