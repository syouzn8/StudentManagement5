package raisetech5.StudentManagement5.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import raisetech5.StudentManagement5.data.ApplicationStatus;
import raisetech5.StudentManagement5.service.StudentService;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest２ {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private StudentService service;

  @Test
  void testGetApplicationStatus() throws Exception {
    Long courseInfoId = 1L;
    ApplicationStatus mockStatus = new ApplicationStatus();
    mockStatus.setCourseInfoId(courseInfoId);
    mockStatus.setStatus("tentative application");

    when(service.searchApplicationStatus(courseInfoId)).thenReturn(mockStatus);

    mockMvc.perform(get("/application-status/{courseInfoId}", courseInfoId))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("tentative application"));
  }

  @Test
  void testCreateApplicationStatus() throws Exception {
    ApplicationStatus newStatus = new ApplicationStatus();
    newStatus.setCourseInfoId(2L);
    newStatus.setStatus("confirmed application");

    when(service.registerApplicationStatus(any(ApplicationStatus.class))).thenReturn(newStatus);

    mockMvc.perform(post("/application-status")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"courseInfoId\": 2, \"status\": \"confirmed application\"}"))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.status").value("confirmed application"));
  }

  @Test
  void testUpdateApplicationStatus() throws Exception {
    ApplicationStatus updatedStatus = new ApplicationStatus();
    updatedStatus.setId(3L);
    updatedStatus.setCourseInfoId(3L);
    updatedStatus.setStatus("in progress");

    doNothing().when(service).updateApplicationStatus(any(ApplicationStatus.class));

    mockMvc.perform(put("/application-status/{id}", 3L)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"courseInfoId\": 3, \"status\": \"in progress\"}"))
        .andExpect(status().isOk())
        .andExpect(content().string("申込状況の更新が成功しました。"));
  }
}