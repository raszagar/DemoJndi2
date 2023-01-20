package es.jose.model.db1.dao;

import java.util.List;

import es.jose.exception.MiException;
import es.jose.model.db1.vo.AsistenteVO;

public interface AsistentesDaoCustom {
	
	List<AsistenteVO> getAsistente(Integer id) throws MiException;

}
