package es.jose.controllers;

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
import es.jose.model.db1.dao.AsistentesDao;
import es.jose.model.db1.vo.AsistenteVO;

//@CrossOrigin()
@RestController
@RequestMapping("/ws")
public class RestControlador {
	
	private static final Logger log = LogManager.getLogger(RestControlador.class);
	
	@Autowired
	private AsistentesDao asistentesDao; 
	
	/**
	 * @return
	 */
	@GetMapping(path = "/test", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String hola() {
		log.debug("prueba log");
		
		return "\"Proyecto demo jndi funciona\"";
	}
	
	@GetMapping(path = "/listaAsis", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<AsistenteVO> getListAsistentes() throws MiException {
		log.debug("getListAsistentes");
		try {
			return asistentesDao.getListaAsistentes();
		} catch (Exception e) {
			throw new MiException("Error al obtener la lista de asistentes", e);
		}
	}
	
	@GetMapping(path = "/listaAsis2", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<AsistenteVO> getListAsistentes2() throws MiException {
		log.debug("getListAsistentes");
		try {
			return asistentesDao.findAll(Sort.by(Sort.Order.asc("nombre"), Sort.Order.desc("id")));
		} catch (Exception e) {
			throw new MiException("Error al obtener la lista de asistentes", e);
		}
	}
	
	@GetMapping(path = "/asistente/{id}")
	public List<AsistenteVO> getAsistente(@PathVariable(name = "id", required = true) Integer id) throws MiException {
		log.debug("getAsistente");
		try {
			return asistentesDao.getAsistente(id);
		} catch (Exception e) {
			throw new MiException("Error al obtener asistente", e);
		}
	}
	
	@PutMapping(path = "/asistente")
	public String insertarAsistente(@RequestBody AsistenteVO asistente) throws MiException {
		log.debug("insertarAsistente");
		try {
			log.debug(asistente);
			if(asistente.getId() != null)  {
				throw new MiException("No se puede insertar con un id definido");
			}
				
			asistentesDao.save(asistente);
			return "";
		} catch (Exception e) {
			throw new MiException("Error al insertar asistente", e);
		}
	}
	
	@PostMapping(path = "/asistente")
	public String updateAsistente(@RequestBody AsistenteVO asistente) throws MiException {
		log.debug("updateAsistente");
		try {
			if(asistente.getId() == null) {
				throw new MiException("El id es obligatorio");
			}
			
			if(!asistentesDao.existsById(asistente.getId())) {
				throw new MiException("El asistente no existe");
			}
			
			asistentesDao.save(asistente);
			return "";
		} catch (MiException e) {
			throw e;
		} catch (Exception e) {
			throw new MiException("Error al actualizar asistente", e);
		}
	}
	
	@DeleteMapping("/asistente/{id}")
	public String deleteAsistente(@PathVariable(name = "id", required = true) Integer id) throws MiException {
		log.debug("deleteAsistente");
		try {
			if(!asistentesDao.existsById(id)) {
				throw new MiException("El asistente no existe");
			}
			
			asistentesDao.deleteById(id);
			return "";
		} catch (MiException e) {
			throw e;
		} catch (Exception e) {
			throw new MiException("Error al borrar asistente", e);
		}
	}
	
}
