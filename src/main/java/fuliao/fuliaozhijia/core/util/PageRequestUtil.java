package fuliao.fuliaozhijia.core.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class PageRequestUtil {
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	protected int pageNo = 1;
	protected int pageSize = 16;

	protected Map<String,String> orderby;

	public PageRequestUtil() {
	}

	public PageRequestUtil(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return this.pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
		if (pageNo < 1)
			this.pageNo = 1;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		if (pageSize < 1)
			this.pageSize = 1;
	}
	public void addSort(Map<String,String> orderby) {
		this.orderby = orderby;
	}
	public void addSort(String field,String orderDir) {
		if(orderby ==null)
			orderby = Maps.newHashMap();
		if ((StringUtils.equalsIgnoreCase(DESC, orderDir))
				|| (StringUtils.equalsIgnoreCase(ASC, orderDir))) 
		orderby.put(field, orderDir.toLowerCase());
	}

	private Sort getSort() {
		if(orderby !=null && orderby.size()>0){
			List<Sort.Order> orderList = Lists.newArrayList();
			for(String field : orderby.keySet()){
				if (DESC.equals(orderby.get(field)))
					orderList.add(new Sort.Order(Sort.Direction.DESC,field));
				else
					orderList.add(new Sort.Order(Sort.Direction.ASC,field));
			}
			return new Sort(orderList);
		}
		return null;
	}

	public PageRequest springPageRequest() {
		Sort sort = getSort();
		if (null == sort)
			return new PageRequest(this.pageNo - 1, this.pageSize);
		return new PageRequest(this.pageNo - 1, this.pageSize, sort);
	}
	public Map<String, String> getOrderby() {
		return orderby;
	}
}
