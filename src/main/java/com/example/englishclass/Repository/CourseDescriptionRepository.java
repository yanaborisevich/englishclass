package com.example.englishclass.Repository;

import com.example.englishclass.Entity.CourseDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseDescriptionRepository extends JpaRepository<CourseDescription, String> {
    @Query("SELECT u FROM CourseDescription u WHERE u.name = :name")
    public CourseDescription getCourseDescriptionByName(@Param("name") String name);
}
