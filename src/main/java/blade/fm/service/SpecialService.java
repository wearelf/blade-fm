package blade.fm.service;

import java.util.List;
import java.util.Map;

import blade.fm.model.Special;
import blade.plugin.sql2o.Page;



/**
 * 专辑接口
 * @author:rex
 * @date:2014年9月24日
 * @version:1.0
 */
public interface SpecialService {

	Special get(Integer sid);
	
	Map<String, Object> getMap(Special special, Integer sid);
	
	boolean save(Integer uid, String title, String introduce, String cover, Integer is_fine, Integer status);
	
	int update(Integer sid, Integer uid, String title, String introduce, String cover, Integer is_fine, Integer status);
	
	List<Map<String, Object>> getList(Integer uid, Integer type, Integer is_fine, String title, Integer status, String order);
	
	Page<Map<String, Object>> getPageMapList(Integer uid, Integer type, Integer is_fine, String title, Integer status, Integer page, Integer pageSize, String order);
	
	int delete(Integer sid);
	
	int hit(Integer sid);

	boolean enable(Integer sid, Integer status);

}
