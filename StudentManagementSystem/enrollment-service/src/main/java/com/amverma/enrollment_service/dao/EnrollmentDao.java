package com.amverma.enrollment_service.dao;

import com.amverma.enrollment_service.model.Enrollment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentDao extends JpaRepository<Enrollment, Integer> {
    @Query("SELECT e FROM Enrollment e WHERE e.studentId = :studentId AND e.courseId = :courseId")
    public Enrollment get(int studentId, int courseId);

    @Query("SELECT e.courseId FROM Enrollment e WHERE e.studentId = :id")
    public List<Integer> findByStudentId(int id);

    @Query("SELECT e.studentId FROM Enrollment e WHERE e.courseId = :id")
    public List<Integer> findByCourseId(int id);

    @Transactional
    @Modifying
    @Query("Delete FROM Enrollment e where e.courseId = :id")
    public void deleteByCourseId(int id);

    @Transactional
    @Modifying
    @Query("Delete FROM Enrollment e WHERE e.studentId = :id")
    public void deleteByStudentId(int id);
}
