package es.jose.model.db2.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.jose.model.db2.vo.PersonaVO;

/**
 * @author javazqueza
 */
public interface PersonasDao extends JpaRepository<PersonaVO, Integer> {

	@Query("select a from PersonaVO a order by nombre")
	public List<PersonaVO> getListaPersonas();
	
}
