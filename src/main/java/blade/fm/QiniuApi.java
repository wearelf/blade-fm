package blade.fm;

import java.io.File;

import blade.Blade;
import blade.kit.log.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

/**
 * 各种客户端获取工厂
 * @author:rex
 * @date:2014年8月19日
 * @version:1.0
 */
public class QiniuApi {
	
	private static String ACCESSKEY = Blade.config().get("QINIU.ACCESSKEY");
	private static String SECRETKEY = Blade.config().get("QINIU.SECRETKEY");
	
	private final static Auth auth = Auth.create(ACCESSKEY, SECRETKEY);
	
	private final static String BUCKET_NAME = Blade.config().get("QINIU.BUCKET_NAME");
	
	private final static Logger log = Logger.getLogger(QiniuApi.class);
	
	private static UploadManager uploadManager = new UploadManager();
	private static BucketManager bucketManager = new BucketManager(auth);
	
	public static String getUploadToken(){
		StringMap policy = new StringMap();
		policy.putNotEmpty(
				"returnBody",
				"{\"key\":$(key),\"hash\":$(etag),\"w\":$(imageInfo.width),\"h\":$(imageInfo.height),\"format\":$(imageInfo.format)}");
		
		return auth.uploadToken(BUCKET_NAME, null, 3600, policy);
	}
	
	public static String getUploadToken(String key){
		StringMap policy = new StringMap();
		policy.putNotEmpty(
				"returnBody",
				"{\"key\":$(key),\"hash\":$(etag),\"w\":$(imageInfo.width),\"h\":$(imageInfo.height),\"format\":$(imageInfo.format)}");
		
		return auth.uploadToken(BUCKET_NAME, key, 3600, policy);
	}
	
	public static String getUrlByKey(String key){
		return Blade.config().get("QINIU.CDN_PREFIX") + "/" + key; 
	}
	
	public static String delete(String key){
		try {
			bucketManager.delete(BUCKET_NAME, key);
		} catch (QiniuException e) {
			Response r = e.response;
	        // 请求失败时简单状态信息
	        log.error(r.toString());
	        try {
	            // 响应的文本信息
	            log.error(r.bodyString());
	        } catch (QiniuException e1) {
	            //ignore
	        }
		}
		return null;
	}
	
	public static String uploadFile(File file){
		try {
			
			String fileName = file.getName();
			
			Response res = uploadManager.put(file, fileName, getUploadToken());
			String body = res.bodyString();
			
			log.info("body = " + body);
			
			JSONObject jsonObject = JSON.parseObject(body);
			if(null != jsonObject){
				return jsonObject.getString("key");
			}
		} catch (QiniuException e) {
			Response r = e.response;
	        // 请求失败时简单状态信息
	        log.error(r.toString());
	        try {
	            // 响应的文本信息
	            log.error(r.bodyString());
	        } catch (QiniuException e1) {
	            //ignore
	        }
		}
		return null;
	}
	
	public static String uploadFile(String key, File file){
		try {
			
			String fileName = file.getName();
			
			Response res = uploadManager.put(file, fileName, getUploadToken());
			String body = res.bodyString();
			
			log.info("body = " + body);
			
			JSONObject jsonObject = JSON.parseObject(body);
			if(null != jsonObject){
				return jsonObject.getString("key");
			}
		} catch (QiniuException e) {
			Response r = e.response;
	        // 请求失败时简单状态信息
	        log.error(r.toString());
	        try {
	            // 响应的文本信息
	            log.error(r.bodyString());
	        } catch (QiniuException e1) {
	            //ignore
	        }
		}
		return null;
	}
}
