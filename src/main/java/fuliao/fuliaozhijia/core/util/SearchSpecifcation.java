package fuliao.fuliaozhijia.core.util;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import fuliao.fuliaozhijia.core.entity.IEntity;

public class SearchSpecifcation<E extends IEntity> implements Specification<E>{

	private Map<String,String> filters = Maps.newHashMap();
	
	public SearchSpecifcation(){
		
	}
	
	public SearchSpecifcation(Map<String, String> param){
		filters = param;
	}
	
	@Override
	public Predicate toPredicate(Root<E> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if(null == filters || filters.size()==0)
			return cb.conjunction();
		
		List<Predicate> predicates = Lists.newArrayList();
		List<Predicate> or_predicates = Lists.newArrayList();
		for (String opt_field : filters.keySet()){
			if(!StringUtils.isBlank(filters.get(opt_field)) ){
				String[] opt_fieldName = opt_field.split("_");
				if(opt_fieldName.length ==2){
					String[] names = StringUtils.split(opt_fieldName[1], ".");
					Path expression = root.get(names[0]);
					for (int i = 1; i < names.length; i++) {
						expression = expression.get(names[i]);
					}
					if("EQ".equals(opt_fieldName[0])){
						predicates.add(cb.equal(expression, StringUtils.trim(filters.get(opt_field))));
					}else if("LIKE".equals(opt_fieldName[0])){
						predicates.add(cb.like(expression, "%" +StringUtils.trim(filters.get(opt_field))+ "%"));
					}else if("GT".equals(opt_fieldName[0])){
						predicates.add(cb.greaterThan(expression, StringUtils.trim(filters.get(opt_field))));
					}else if("LT".equals(opt_fieldName[0])){
						predicates.add(cb.lessThan(expression, StringUtils.trim(filters.get(opt_field))));
					}else if("GTE".equals(opt_fieldName[0])){
						predicates.add(cb.greaterThanOrEqualTo(expression, StringUtils.trim(filters.get(opt_field))));
					}else if("LTE".equals(opt_fieldName[0])){
						predicates.add(cb.lessThanOrEqualTo(expression, StringUtils.trim(filters.get(opt_field))));
					}else if("NEQ".equals(opt_fieldName[0])){
						predicates.add(cb.notEqual(expression, StringUtils.trim(filters.get(opt_field))));
					}else if("IN".equals(opt_fieldName[0])){
						predicates.add(expression.in(StringUtils.split(StringUtils.trim(filters.get(opt_field)),",")));
					}//加入or类型查询
					else if("OREQ".equals(opt_fieldName[0])){
						or_predicates.add(cb.equal(expression, StringUtils.trim(filters.get(opt_field))));
					}else if("ORLIKE".equals(opt_fieldName[0])){
						or_predicates.add(cb.like(expression, "%" +StringUtils.trim(filters.get(opt_field))+ "%"));
					}
				}
			}
		}
		if (!predicates.isEmpty()) {
			if(!or_predicates.isEmpty()){
				Predicate and = cb.and(predicates.toArray(new Predicate[predicates.size()]));
				Predicate or = cb.or(or_predicates.toArray(new Predicate[or_predicates.size()]));
				return cb.or(and,or);
			}
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		}
		return cb.conjunction();
	}
	
}
