package instigator.rest_api.QA;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {
	
	@Autowired
	private MainController mainController;
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testMainController() throws Exception {
		assertThat(mainController).isNotNull();
	}

	@Test
	public void testGetQuestion() throws Exception {
		
		this.mockMvc.perform(get("/test")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("--- Done!!!")));
		
		this.mockMvc.perform(get("/UUID/aa")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("--- Not found UUID: aa")));
		
	}

}
