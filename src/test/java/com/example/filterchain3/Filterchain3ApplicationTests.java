package com.example.filterchain3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class Filterchain3ApplicationTests {

	@Autowired
	MockMvc mvc;

	@Test
	@WithMockUser(authorities="user")
	void whenUserHasUserRoleThenSuccess() throws Exception {
		this.mvc.perform(get("/user"))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	void whenUserRoleIsMissingThenDenied() throws Exception {
		this.mvc.perform(get("/user"))
				.andExpect(status().isForbidden());
	}

	@Test
	@WithMockUser(authorities="admin")
	void whenUserHasAdminRoleThenSuccess() throws Exception {
		this.mvc.perform(get("/user"))
				.andExpect(status().isOk());
	}

}
