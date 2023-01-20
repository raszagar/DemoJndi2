package es.jose.controllers;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.jose.exception.MiException;
import es.jose.model.db2.dao.PersonasDao;
import es.jose.model.db2.vo.PersonaVO;

//@CrossOrigin()
@RestController
@RequestMapping("/ws2")
public class RestControladorBd2 {
	
	private static final Logger log = LogManager.getLogger(RestControladorBd2.class);
	
	@Autowired
	private PersonasDao personasDao; 
	
	/**
	 * @return
	 */
	@GetMapping(path = "/test", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String hola() {
		log.debug("prueba log");
		
		return "\"Proyecto demo jndi bd2 funciona\"";
	}
	
	@GetMapping(path = "/listaPersonas", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<PersonaVO> getListaPersonas() throws MiException {
		log.debug("getListaPersonas");
		try {
			return personasDao.findAll(Sort.by(Sort.Order.asc("nombre")));
		} catch (Exception e) {
			throw new MiException("Error al obtener la lista de personas", e);
		}
	}
	
	@GetMapping(path = "/persona/{id}")
	public List<PersonaVO> getPersona(@PathVariable(name = "id", required = true) Integer id) throws MiException {
		log.debug("getPersona");
		try {
			return personasDao.findAllById(Arrays.asList(id));
		} catch (Exception e) {
			throw new MiException("Error al obtener persona", e);
		}
	}
	
	@PutMapping(path = "/persona")
	public String insertarPersona(@RequestBody PersonaVO persona) throws MiException {
		log.debug("insertarPersona");
		try {
			if(persona.getId() != null)  {
				throw new MiException("No se puede insertar con un id definido");
			}
				
			personasDao.save(persona);
			return "";
		} catch (Exception e) {
			throw new MiException("Error al insertar persona", e);
		}
	}
	
	@PostMapping(path = "/persona")
	public String updatePersona(@RequestBody PersonaVO persona) throws MiException {
		log.debug("updatePersona");
		try {
			if(persona.getId() == null) {
				throw new MiException("El id es obligatorio");
			}
			
			if(!personasDao.existsById(persona.getId())) {
				throw new MiException("El persona no existe");
			}
			
			personasDao.save(persona);
			return "";
		} catch (MiException e) {
			throw e;
		} catch (Exception e) {
			throw new MiException("Error al actualizar persona", e);
		}
	}
	
	@DeleteMapping("/persona/{id}")
	public String deletePersona(@PathVariable(name = "id", required = true) Integer id) throws MiException {
		log.debug("deletePersona");
		try {
			if(!personasDao.existsById(id)) {
				throw new MiException("La persona no existe");
			}
			
			personasDao.deleteById(id);
			return "";
		} catch (MiException e) {
			throw e;
		} catch (Exception e) {
			throw new MiException("Error al borrar persona", e);
		}
	}
	
}
