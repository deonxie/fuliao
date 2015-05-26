package fuliao.fuliaozhijia.core.authc;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;

import com.google.common.collect.Maps;

public class CustomUserFilter extends UserFilter{
	private Map<String,String> modelPath = Maps.newHashMap();
	private final String LOGIN = "_login";
	private final Pattern pattern = Pattern.compile("^/[a-zA-z0-9_]{0,}/*");

	@Override
	protected void redirectToLogin(ServletRequest request,
			ServletResponse response) throws IOException {
		   String login_Url = getModelName(request);
	       WebUtils.issueRedirect(request, response, login_Url);
	}
	
	private String getModelName(ServletRequest request){
		String path = null;
		if(request instanceof HttpServletRequest){
			HttpServletRequest httpreq = (HttpServletRequest)request;
			String reqUrl = httpreq.getRequestURI().replace(httpreq.getContextPath(), "");
			Matcher m = pattern.matcher(reqUrl);
			if(m.find()){
				String model = StringUtils.replace(m.group(), "/", "", 2);
				path = modelPath.get(model+LOGIN);
				System.out.println("user model:="+model+" path="+path);
			}
		}
		if(StringUtils.isNotBlank(path))
			return path;
		return super.getLoginUrl();
		
	}

	public void setModelPath(Map<String,String> modelPath) {
		if(null == modelPath || modelPath.size()<1)
			return;
		for(String model : modelPath.keySet()){
			String path = modelPath.get(model);
			if(StringUtils.isNoneBlank(path)){
				this.modelPath.put(StringUtils.trim(model)+LOGIN, StringUtils.trim(path));
			}
		}
	}
	
}