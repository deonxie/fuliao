package fuliao.fuliaozhijia.core.util;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.google.common.collect.Maps;


public final class SystemProperties {
	private static Properties systemPro = new Properties();
	private static final Integer lock =1;
	private static SystemProperties instance;
	private SystemProperties(){};
	
	public static SystemProperties getInstance(){
		synchronized (lock) {
			if(null == instance){
				instance = new SystemProperties();
				instance.reload();
			}
		}
		return instance;
	}
	
	public synchronized void reload(){
		try {
			Resource resource = new ClassPathResource("/system.properties");
			systemPro = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			System.out.println("SystemProperties >>getInstance 加载system.properties文件失败");
		}
	}
	
	public Map<String, String> getLike(String regex){
		Pattern p = Pattern.compile(regex);
		Matcher m;
		Map<String, String> map = Maps.newHashMap();
		for(Object key:systemPro.keySet()){
			m = p.matcher(key.toString());
			if(m.find()){
				map.put(key.toString(), null==systemPro.get(key)?"":systemPro.get(key).toString());
			}
		}
		return map;
	}
	public String getValue(String key){
		Object obj = systemPro.get(key);
		if(null ==obj)
			return null;
		return obj.toString().trim();
	}
}
