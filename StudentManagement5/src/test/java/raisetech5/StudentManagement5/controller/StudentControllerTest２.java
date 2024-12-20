package raisetech5.StudentManagement5.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import raisetech5.StudentManagement5.data.ApplicationStatus;
import raisetech5.StudentManagement5.data.Student;
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

  @Test
  void 検索条件なしで全ての受講生を取得できること() throws Exception {

    Student student1 = new Student();
    student1.setId(String.valueOf(1L));
    student1.setName("John Doe");
    student1.setTown("Tokyo");
    student1.setAge(25);

    Student student2 = new Student();
    student2.setId(String.valueOf(2L));
    student2.setName("Jane Doe");
    student2.setTown("Osaka");
    student2.setAge(22);

    when(service.searchStudents(null, null, null))
        .thenReturn(Arrays.asList(student1, student2));

    mockMvc.perform(get("/students/search"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("John Doe"))
        .andExpect(jsonPath("$[1].name").value("Jane Doe"));
  }


}