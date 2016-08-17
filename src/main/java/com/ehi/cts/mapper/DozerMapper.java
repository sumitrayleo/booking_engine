package com.ehi.cts.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

@Component
public class DozerMapper extends DozerBeanMapper {

	@PostConstruct
	public void afterPropertiesSet() {
		final List<String> mappingFileUrls = new ArrayList<String>();
		mappingFileUrls.add("customBeanMapping.xml");
		this.setMappingFiles(mappingFileUrls);
	}
}