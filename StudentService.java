package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }


    public void addNewStudent(Student student) {
        Optional<Student> optionalStudent = studentRepository
                .findStudentByEmail(student.getEmail());
        if (optionalStudent.isPresent()){
            throw new IllegalStateException("email is taken");
        }else {
            studentRepository.save(student);
        }

    }

    public void deleteStudentById(long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists){
            throw new IllegalStateException(
                    "Student with id:"+studentId+"does not exist");
        }else {
            studentRepository.deleteById(studentId);
        }
    }

    @Transactional
    public void updateStudentById(long studentId,
                                  String name,
                                  String email) {
        //validation by id
        Student studentToUpdate = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "Student with id: "+studentId+" does not exists"));
        //name validation
        if (name != null && name.length()>0
                && !Objects.equals(studentToUpdate.getName(),name)){
            studentToUpdate.setName(name);
        }
        //email validation
        if (email != null && email.length()>0 &&
                !Objects.equals(studentToUpdate.getEmail(),email)){
            Optional<Student> studentOptional =
                    studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }else {
                studentToUpdate.setEmail(email);
            }
        }//end of email validation


    }//end of method update


}//end of services class
