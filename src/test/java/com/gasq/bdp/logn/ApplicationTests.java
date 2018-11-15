package com.gasq.bdp.logn;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.gasq.bdp.logn.component.ImpalaOptions;
import com.gasq.bdp.logn.mapper.TCompanyMapper;
import com.gasq.bdp.logn.service.CommonService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired
	private RestTemplate restTemplate;
    @Autowired TCompanyMapper companyMapper;
    @Autowired CommonService commonService;
    
    @Autowired
    ImpalaOptions impalaOptions;
    
	@Test
	public void readES() throws Exception {
		List<Map<String, Object>> queryForList = impalaOptions.queryImpalaList("SELECT * from gabdp_user.t_city");
		queryForList.stream().forEach(f->System.out.println(f.toString()));
	}
}
