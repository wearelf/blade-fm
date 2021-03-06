package blade.fm.service;

import java.util.Map;

import blade.fm.model.Setting;

/**
 * 配置接口
 * @author:rex
 * @date:2014年8月20日
 * @version:1.0
 */
public interface SettingService {

	/**
	 * 根据key获取配置
	 * @param key
	 * @return
	 */
	Setting get(String key);
	
	/**
	 * 保存配置
	 * @param key
	 * @param value
	 * @return
	 */
	int save(String key, String value);
	
	/**
	 * 更新配置
	 * @param key
	 * @param value
	 * @return
	 */
	int update(String key, String value);
	
	/**
	 * 删除配置
	 * @param key
	 * @return
	 */
	int delete(String key);
	
	/**
	 * 获取系统配置
	 * @return
	 */
	Map<String, String> getAllSetting();
}
