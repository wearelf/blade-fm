package blade.fm.interceptor;

import java.util.Map;

import blade.fm.api.Constant;
import blade.fm.cloud.model.User;
import blade.fm.service.SettingService;
import blade.fm.service.impl.SettingServiceImpl;
import blade.annotation.After;
import blade.annotation.Before;
import blade.annotation.Interceptor;
import blade.kit.timw.TimwManager;
import blade.kit.timw.TimwMonitor;
import blade.servlet.Request;
import blade.servlet.Response;

/**
 * 全局拦截器
 * @author Rex
 */
@Interceptor
public class BaseInterceptor {

	private SettingService settingService = new SettingServiceImpl();
	
	TimwMonitor monitor = TimwManager.getTimerMonitor();
	
	@Before("/*")
	public void before(Request request, Response response){
		
		System.out.println("before...");
		monitor.start();
		
		String assets = request.contextPath() + "/assets";
		
		String theme = assets + "/" + "theme";
		
		Map<String, String> setting =  settingService.getAllSetting();
		
		request.attribute("assets", assets);
		request.attribute("theme", theme);
		request.attribute("base", request.contextPath());
		request.attribute("static_v", "1.0");
		request.attribute("setting", setting);
		request.attribute("cdn", request.contextPath());
		
		User login_user = request.session().attribute(Constant.LOGIN_SESSION);
		
		// 未登录
//		if(null == login_user && request.uri().indexOf("/admin") != -1){
//			response.redirect(request.contextPath() + WebConst.ADMIN_LOGIN);
//			return;
//		}
	}
	
	@After("/*")
	public void after(){
		System.out.println("after...");
		monitor.end();
		monitor.render();
		monitor.renderAvg();
	}
	
}