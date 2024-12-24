package raisetech5.StudentManagement5.controller;

import static org.hamcrest.Matchers.hasSize;
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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import raisetech5.StudentManagement5.data.ApplicationStatus;
import raisetech5.StudentManagement5.data.StatusType;
import raisetech5.StudentManagement5.data.Student;
import raisetech5.StudentManagement5.service.StudentService;

@WebMvcTest(StudentController.class)
public class StudentControllerTest２ {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private StudentService service;

  @Test
  void コース情報IDで申込状況を取得できること() throws Exception {
    Long courseInfoId = 1L;
    ApplicationStatus mockStatus = new ApplicationStatus(courseInfoId, StatusType.TENTATIVE_APPLICATION);

    when(service.searchApplicationStatus(courseInfoId)).thenReturn(mockStatus);

    mockMvc.perform(get("/application-status/{courseInfoId}", courseInfoId))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("TENTATIVE_APPLICATION"));
  }

  @Test
  void 新規の申込状況を作成できること() throws Exception {
    ApplicationStatus newStatus = new ApplicationStatus(2L, StatusType.CONFIRMED_APPLICATION);

    when(service.registerApplicationStatus(any(ApplicationStatus.class))).thenReturn(newStatus);

    mockMvc.perform(post("/application-status")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"courseInfoId\": 2, \"status\": \"CONFIRMED_APPLICATION\"}"))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.status").value("CONFIRMED_APPLICATION"));
  }

  @Test
  void 申込状況を更新できること() throws Exception {
    doNothing().when(service).updateApplicationStatus(any(ApplicationStatus.class));

    mockMvc.perform(put("/application-status/{id}", 3L)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"courseInfoId\": 3, \"status\": \"IN_PROGRESS\"}"))
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

    mockMvc.perform(get("/students"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("John Doe"))
        .andExpect(jsonPath("$[1].name").value("Jane Doe"));
  }

  @Test
  void 名前で検索できること() throws Exception {
    Student student1 = new Student();
    student1.setId(String.valueOf(1L));
    student1.setName("MisakiTakahashi");
    student1.setTown("Fukuoka");
    student1.setAge(21);

    Student student2 = new Student();
    student2.setId(String.valueOf(2L));
    student2.setName("KojiSato");
    student2.setTown("Kagawa");
    student2.setAge(27);

    when(service.searchStudents("MisakiTakahashi", null, null))
        .thenReturn(Arrays.asList(student1));

    mockMvc.perform(get("/students")
            .param("name", "MisakiTakahashi"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("MisakiTakahashi"))
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$", hasSize(1)));
  }

}