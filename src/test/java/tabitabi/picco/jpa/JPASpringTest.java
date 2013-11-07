package tabitabi.picco.jpa;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tabitabi.picco.util.UTF8ResourceBundle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/beans.xml"})
public class JPASpringTest {

	@Resource
	private UTF8ResourceBundle properties;
	
	@Test
	public void test() {
		Map<String, String> asMap = properties.asMap();
		asMap.size();
		System.out.print(asMap);
	}

}
