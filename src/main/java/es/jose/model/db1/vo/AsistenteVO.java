package es.jose.model.db1.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "asistentes")
public class AsistenteVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nombre", length = 45)
	private String nombre;
	@Column(name = "dni", length = 45)
	private String dni;
	@Column(name = "fecha")
	private Date fecha;

	/**
	 * @return the id
	 */
	public final Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public final String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public final void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the dni
	 */
	public final String getDni() {
		return dni;
	}

	/**
	 * @param dni the dni to set
	 */
	public final void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * @return the fecha
	 */
	public final Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public final void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AsistenteVO [id=" + id + ", nombre=" + nombre + ", dni=" + dni + ", fecha=" + fecha + "]";
	}

}
