package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipologiasexo database table.
 * 
 */
@Entity
@NamedQuery(name="Tipologiasexo.findAll", query="SELECT t FROM Tipologiasexo t")
public class Tipologiasexo extends Entidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idTipologia;

	private String descripcion;

	//bi-directional many-to-one association to Estudiante
	@OneToMany(mappedBy="tipologiasexo")
	private List<Estudiante> estudiantes;

	//bi-directional many-to-one association to Profesor
	@OneToMany(mappedBy="tipologiasexo")
	private List<Profesor> profesors;

	public Tipologiasexo() {
	}

	public int getIdTipologia() {
		return this.idTipologia;
	}

	public void setIdTipologia(int idTipologia) {
		this.idTipologia = idTipologia;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Estudiante> getEstudiantes() {
		return this.estudiantes;
	}

	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}

	public Estudiante addEstudiante(Estudiante estudiante) {
		getEstudiantes().add(estudiante);
		estudiante.setTipologiasexo(this);

		return estudiante;
	}

	public Estudiante removeEstudiante(Estudiante estudiante) {
		getEstudiantes().remove(estudiante);
		estudiante.setTipologiasexo(null);

		return estudiante;
	}

	public List<Profesor> getProfesors() {
		return this.profesors;
	}

	public void setProfesors(List<Profesor> profesors) {
		this.profesors = profesors;
	}

	public Profesor addProfesor(Profesor profesor) {
		getProfesors().add(profesor);
		profesor.setTipologiasexo(this);

		return profesor;
	}

	public Profesor removeProfesor(Profesor profesor) {
		getProfesors().remove(profesor);
		profesor.setTipologiasexo(null);

		return profesor;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String toString() {
		return descripcion;
	}

	public boolean equals(Object obj) {
		Tipologiasexo tipo = (Tipologiasexo) obj;
		if (this.idTipologia == tipo.idTipologia) {
			return true;
		}
		return false;
	}

}