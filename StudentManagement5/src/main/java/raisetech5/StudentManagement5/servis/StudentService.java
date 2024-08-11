package raisetech5.StudentManagement5.servis;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech5.StudentManagement5.StudentRepository;
import raisetech5.StudentManagement5.repositoly.Student;
import raisetech5.StudentManagement5.repositoly.StudentsCourses;

@Service
public class StudentService {

  private StudentRepository repository;


  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> searchStudentList() {
    return repository.search();
  }


  public List<StudentsCourses> searchStudentsCourseList() {
    return repository.searchStudentsCourses();
  }



}