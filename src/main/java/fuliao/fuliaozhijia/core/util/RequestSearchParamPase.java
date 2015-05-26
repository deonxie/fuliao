package fuliao.fuliaozhijia.core.util;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;



public class RequestSearchParamPase {
	
	public static Map<String,String> parseRequest(ServletRequest request,String prefix){
		Map<String,String> filters = Maps.newHashMap();
		Enumeration<String> paramNames = request.getParameterNames();
		if (prefix == null) {
			prefix = "";
		}
		String paramName;
		if(paramNames != null){
			String[] values;
			while (paramNames.hasMoreElements()) {
				paramName = paramNames.nextElement();
				if(paramName.startsWith(prefix)){
					values = request.getParameterValues(paramName);
					if(values !=null){
						if(values.length==1 && StringUtils.isNoneBlank(values[0]))
							filters.put(paramName.substring(prefix.length()), values[0]);
						else{
							StringBuffer sb = new StringBuffer();
							for(int i=0;i<values.length;i++){
								if(i== values.length-1)
									sb.append(values[i]);
								else
									sb.append(values[i]).append(",");
							}
							filters.put(paramName.substring(prefix.length()), sb.toString());
						}
					}
				}
			}
		}
		return filters;
	}
}
