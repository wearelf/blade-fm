
package blade.fm;

import blade.Blade;
import blade.kit.FileKit;
import blade.kit.StringKit;


/**
 * 常量类
 *
 * @author	<a href="mailto:biezhi.me@gmail.com" target="_blank">biezhi</a>
 * @since	1.0
 */
public class Constant {
	
	/**
	 * 用户登录session标识
	 */
	public static final String LOGIN_SESSION = "login_session";
	
	/**
	 * 验证码session标识
	 */
	public static final String CAPTCHA_TOKEN = "captcha_token";
	
	/**
	 * 是否是debug模式
	 */
	public static final boolean IS_DEBUG = true;
	
	/**
	 * 上传文件目录
	 */
	public static final String UPLOAD_FOLDER = "userfiles";
	
	/**
	 * 个人信息存储变量
	 */
	public static final String USER_PROFILE_INFO = "profile";
	
	public static final String ADMIN_LOGIN = "/login";
	
	public static boolean isFile(String filePath){
		if(StringKit.isNotBlank(filePath)){
			return FileKit.isFile(Blade.webRoot() + "/" + filePath);
		}
		return false;
	}
	
 }
