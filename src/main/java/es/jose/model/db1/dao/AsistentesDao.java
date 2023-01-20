package es.jose.model.db1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.jose.model.db1.vo.AsistenteVO;

/**
 * @author javazqueza
 */
public interface AsistentesDao extends JpaRepository<AsistenteVO, Integer>, AsistentesDaoCustom {

	@Query("select a from AsistenteVO a order by nombre")
	public List<AsistenteVO> getListaAsistentes();
	
}
