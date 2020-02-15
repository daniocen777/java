package com.danicode.springboot.backend.apirest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	// Serial => cuando s implmente Serializable
	private static final long serialVersionUID = 1L;

	// Nota: No es necesario colocar @Column
	// a los atributos si estos tendrán el mismo nombre en la BD

	// IDENTITY => Mysql, SEQUENCE => Oracle, postgress
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "no puede estar vacío")
	@Size(min = 4, max = 20, message = "el tamaño tiene que tener entre 4 y 20 caracteres")
	@Column(nullable = false)
	private String nombre;

	@NotEmpty(message = "no puede estar vacío")
	@Column(nullable = false)
	private String apellido;

	@NotEmpty(message = "no puede estar vacío")
	@Email(message = "no es una dirección de correo electrónico correcto")
	@Column(nullable = false, unique = true)
	private String email;

	@NotNull(message = "no puede estar vacío")
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;

	private String foto;

	// Relación con el objeto Region (tabla regiones)
	// Muchos clientes en una sola región
	// LAZY => Carga perezosa, se carga cuando se le llama
	// => LAZY transforma a json el objeto + otras propiedades
	// ==> @JsonIgnoreProperties => Quita esas propiedades
	@NotNull(message = "La región no puede estar vacía")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "region_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Region region;

	// Colocar la fecha de hoy (antes de persistir)
	// Se colocará la fecha desde el formulario
//	@PrePersist
//	public void prePersist() {
//		createAt = new Date();
//	}

//	public Cliente() {
//		super();
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

}
