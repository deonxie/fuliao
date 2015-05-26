package fuliao.fuliaozhijia.core.authc;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import com.google.common.collect.Maps;

public class CustomLogoutFilter extends LogoutFilter {
	private Map<String,String> modelPath = Maps.newHashMap();
	private final String LOGIN = "_login";
	private final Pattern pattern = Pattern.compile("^/[a-zA-z0-9_]{0,}/*");

	@Override
	protected String getRedirectUrl(ServletRequest request,
			ServletResponse response, Subject subject) {
		String path = null;
		if(request instanceof HttpServletRequest){
			HttpServletRequest httpreq = (HttpServletRequest)request;
			String reqUrl = httpreq.getRequestURI().replace(httpreq.getContextPath(), "");
			Matcher m = pattern.matcher(reqUrl);
			if(m.find()){
				String model = StringUtils.replace(m.group(), "/", "", 2);
				System.out.println("logout model:="+model);
				path = modelPath.get(model+LOGIN);
			}
		}
		if(StringUtils.isNotBlank(path))
			return path;
		return super.getRedirectUrl(request, response, subject);
	}
	
	public void setModelPath(Map<String,String> modelPath) {
		if(null == modelPath || modelPath.size()<1)
			return;
		for(String model : modelPath.keySet()){
			String path = modelPath.get(model);
			if(StringUtils.isNotBlank(path)){
				this.modelPath.put(StringUtils.trim(model)+LOGIN, StringUtils.trim(path));
			}
		}
	}
}
