package com.example.englishclass.Repository;

import com.example.englishclass.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CourseRepository extends JpaRepository<Course, String> {

    @Transactional
    @Query("SELECT u FROM Course u WHERE u.userEmail = :userEmail")
    public Iterable<Course> getAllByUserEmail(@Param("userEmail") String userEmail);

    @Modifying
    @Transactional
    @Query("delete from Course where userEmail = :userEmail")
    void deleteUsersByUserEmail(@Param("userEmail") String userEmail);
}
