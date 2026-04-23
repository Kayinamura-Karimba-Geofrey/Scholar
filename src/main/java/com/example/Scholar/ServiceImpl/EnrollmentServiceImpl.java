package com.example.Scholar.ServiceImpl;

import com.example.Scholar.DTO.EnrollmentRequestDTO;
import com.example.Scholar.DTO.EnrollmentResponseDTO;
import com.example.Scholar.Model.Course;
import com.example.Scholar.Model.Enrollment;
import com.example.Scholar.Model.Student;
import com.example.Scholar.Repository.CourseRepository;
import com.example.Scholar.Repository.EnrollmentRepository;
import com.example.Scholar.Repository.StudentRepository;
import com.example.Scholar.Service.EnrollmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImpl  implements EnrollmentService {
    private  final EnrollmentRepository repo;
    private  final StudentRepository studentRepo;
    private   final CourseRepository courseRepo;

    public  EnrollmentServiceImpl(EnrollmentRepository repo , StudentRepository studentRepo, CourseRepository courseRepo){
        this.repo = repo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    @Override
    public EnrollmentResponseDTO enroll(EnrollmentRequestDTO dto){
        repo.findByStudentIdAndCourseId(dto.studentId(),dto.courseId())
                .ifPresent(e->{
                    throw new RuntimeException("Student already enrolled  in this course");
                });

        Student student = studentRepo.findById(dto.studentId())
                .orElseThrow(()-> new RuntimeException("Student not found"));

        Course course = courseRepo.findById(dto.courseId())
                .orElseThrow(()-> new RuntimeException("Course not found"));

        Enrollment e = new Enrollment();
        e.setStudent(student);
        e.setCourse(course);

        return map(repo.save(e));
    }
    @Override
    public List<EnrollmentResponseDTO> getAll() {
        return repo.findAll().stream().map(this::map).toList();
    }
    @Override
    public List<EnrollmentResponseDTO> getByStudent(Long studentId) {
        return repo.findByStudentId(studentId)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public List<EnrollmentResponseDTO> getByCourse(Long courseId) {
        return repo.findByCourseId(courseId)
                .stream()
                .map(this::map)
                .toList();
    }
    @Override
    public  void  delete(Long id) {
        repo. deleteById(id);
    }

    @Override
    public EnrollmentResponseDTO assignGrade(Long enrollmentId, Double grade) {

        Enrollment e = repo.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        e.setGrade(grade);

        return map(repo.save(e));
    }

    private EnrollmentResponseDTO  map(Enrollment e) {
        return new EnrollmentResponseDTO(
                e.getId(),
                e.getStudent().getId(),
                e.getStudent().getName(),
                e.getCourse().getId(),
                e.getCourse().getTitle(),
                e.getGrade()
        );
    }

}
