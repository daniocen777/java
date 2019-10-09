package com.danicode;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.danicode.model.Categoria;
import com.danicode.model.Vacante;
import com.danicode.repositorio.CategoriasRepositorio;
import com.danicode.repositorio.PerfilesRepository;
import com.danicode.repositorio.UsuariosRepository;
import com.danicode.repositorio.VacantesRepository;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	// Categorias
	@Autowired
	private CategoriasRepositorio repoCategorias;

	// Vancates
	@Autowired
	private VacantesRepository repoVacantes;
	
	@Autowired
	private UsuariosRepository repoUsuarios;
	
	@Autowired
	private PerfilesRepository repoPerfiles;
	

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		guardarVacante();
	}

	/**
	 * @author DANIEL Método guardarVacante
	 */
	private void guardarVacante() {
		Vacante vacante = new Vacante();
		vacante.setNombre("Profesor de Matematicas");
		vacante.setDescripcion("Escuela primaria solicita profesor para curso de Matematicas");
		vacante.setFecha(new Date());
		vacante.setSalario(8500.0);
		vacante.setEstatus("Aprobada");
		vacante.setDestacado(0);
		vacante.setImagen("escuela.png");
		vacante.setDetalles("<h1>Los requisitos para profesor de Matematicas</h1>");
		Categoria cat = new Categoria();
		cat.setId(15);
		vacante.setCategoria(cat);
		repoVacantes.save(vacante);
	}

	private void buscarVacantes() {
		List<Vacante> lista = repoVacantes.findAll();
		for (Vacante vacante : lista) {
			System.out.println(vacante.getId() + " " + vacante.getNombre() + " - Categoría: "
					+ vacante.getCategoria().getNombre());
		}
	}

	/**
	 * @author DANIEL Método findAll(Sort || paginación)
	 */
	public void buscarTodosPaginacionOrdenados() {
		// Segunda página => 1 || de 5 en 5
		Page<Categoria> categorias = repoCategorias.findAll(PageRequest.of(1, 5, Sort.by("nombre")));
		// Total de registros
		System.out.println("Total de registros: " + categorias.getTotalElements());
		// Total de páginas
		System.out.println("Total de páginas: " + categorias.getTotalPages());
		for (Categoria cat : categorias.getContent()) {
			System.out.println(cat.getId() + " " + cat.getNombre());
		}
	}

	public void buscarTodosPaginacion() {
		// Primera página => 0 de 5 en 5
		Page<Categoria> categorias = repoCategorias.findAll(PageRequest.of(0, 5));
		// Total de registros
		System.out.println("Total de registros: " + categorias.getTotalElements());
		// Total de páginas
		System.out.println("Total de páginas: " + categorias.getTotalPages());
		for (Categoria cat : categorias.getContent()) {
			System.out.println(cat.getId() + " " + cat.getNombre());
		}
	}

	public void buscarTodosOrdenados() {
		List<Categoria> categorias = repoCategorias.findAll(Sort.by("nombre").descending());
		for (Categoria cat : categorias) {
			System.out.println(cat.getId() + " " + cat.getNombre());
		}
	}

	/**
	 * @author DANIEL Método deleteAllInBatch
	 */
	public void borrarTodoEnBloque() {
		repoCategorias.deleteAllInBatch();
	}

	/**
	 * @author DANIEL Método findAll JPA => return List<T>
	 */
	public void buscarTodasJpa() {
		List<Categoria> categorias = repoCategorias.findAll();
		for (Categoria cat : categorias) {
			System.out.println(cat.getId() + " " + cat.getNombre());
		}
	}

	/**
	 * @author DANIEL Método saveAll input: Iterable<T> => List<Categoria> return
	 *         Iterable<T>
	 */
	public void guardarTodas() {
		repoCategorias.saveAll(getListaCategorias());
	}

	/**
	 * @author DANIEL Método para traer una lista de categorías
	 */

	List<Categoria> getListaCategorias() {
		List<Categoria> lista = new LinkedList<Categoria>();
		// Categoría 1
		Categoria categoria1 = new Categoria();
		categoria1.setNombre("Categoría 1");
		categoria1.setDescripcion("Descripción de Categoría 1");
		// Categoría 2
		Categoria categoria2 = new Categoria();
		categoria2.setNombre("Categoría 2");
		categoria2.setDescripcion("Descripción de Categoría 2");
		// Categoría 3
		Categoria categoria3 = new Categoria();
		categoria3.setNombre("Categoría 3");
		categoria3.setDescripcion("Descripción de Categoría 3");

		lista.add(categoria1);
		lista.add(categoria2);
		lista.add(categoria3);

		return lista;
	}

	/**
	 * @author DANIEL Método existById input: Integer id return true | false
	 */
	public void existeId() {
		boolean existe = repoCategorias.existsById(55);
		System.out.println("¿Categoria existe? " + existe);
	}

	/**
	 * @author DANIEL Método findAll
	 */
	public void buscarTodos() {
		Iterable<Categoria> categorias = repoCategorias.findAll();
		for (Categoria cat : categorias) {
			System.out.println(cat);
		}
	}

	/**
	 * @author DANIEL Método findAllByIdAll => input Iterable<Integer>
	 */
	public void encontrarPorIds() {
		List<Integer> ids = new LinkedList<Integer>();
		ids.add(1);
		ids.add(10);
		Iterable<Categoria> categorias = repoCategorias.findAllById(ids);
		for (Categoria categoria : categorias) {
			System.out.println(categoria);
		}
	}

	/**
	 * @author DANIEL Método deleteAll
	 */
	public void eliminarTodo() {
		repoCategorias.deleteAll();
	}

	/**
	 * @author DANIEL Método count
	 */
	public void conteo() {
		long cont = repoCategorias.count();
		System.out.println("Total de categorías: " + cont);
	}

	/**
	 * @author DANIEL Método DeleteById input => Integer id
	 */
	public void eliminar() {
		int idCategoria = 1;
		repoCategorias.deleteById(idCategoria); // void
	}

	/**
	 * @author DANIEL Método findById input => Integer id
	 */
	public void buscarPorId() {
		Optional<Categoria> optional = repoCategorias.findById(1);
		if (optional.isPresent()) {
			System.out.println(optional.get());
		} else {
			System.out.println("Categoría no encontrada");
		}

	}

	/**
	 * @author DANIEL Método save(T) input => T modelo
	 */
	public void modificar() {
		Optional<Categoria> optional = repoCategorias.findById(1);
		if (optional.isPresent()) {
			Categoria categoriaTemp = optional.get();
			categoriaTemp.setNombre("Ingenierpia de Software");
			categoriaTemp.setDescripcion("Descripción de Ingenierpia de Software");
			repoCategorias.save(categoriaTemp);
			System.out.println(optional.get());
		} else {
			System.out.println("Categoría no encontrada");
		}

	}

	public void guardar() {
		Categoria categoria = new Categoria();
		categoria.setNombre("Finanzas");
		categoria.setDescripcion("Descripción de finanzas");
		repoCategorias.save(categoria);
		System.out.println(categoria);
	}

}
