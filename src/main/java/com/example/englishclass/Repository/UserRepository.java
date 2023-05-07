package com.example.englishclass.Repository;

import com.example.englishclass.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    public User getUserByEmail(@Param("email") String email);

    Iterable<User> findByRolesName(String name);

    @Query(value = "select * from _user s where s.lastname like %:keyword% or s.email like %:keyword%", nativeQuery = true)
    List<User> findByKeyword(@Param("keyword") String keyword);

    boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query("delete from User where email = :UserEmail")
    void deleteUsersByUserEmail(@Param("UserEmail") String UserEmail);
}
