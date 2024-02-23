package com.energy.solution.mvp;

import com.energy.solution.mvp.api.DeviceConfigurationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MvpApplicationIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testCreateAndGetConfiguration() throws Exception {
		DeviceConfigurationDto configurationDto = new DeviceConfigurationDto("TEST00123", "exampleConfiguration");
		String jsonRequest = "{\n" +
				"  \"identifier\": \"TEST00123\",\n" +
				"  \"configuration\": \"exampleConfiguration\"\n" +
				"}";

		mockMvc.perform(post("/configurations")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonRequest))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.configuration").value(configurationDto.getConfiguration()))
				.andExpect(jsonPath("$.createdAt").isNotEmpty());

		Long configurationId =  1L;
		mockMvc.perform(get("/configurations/{configurationId}", configurationId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.konfiguracja").value("exampleConfiguration"))
				.andExpect(jsonPath("$.['data utworzenia']").isNotEmpty());
	}

}
