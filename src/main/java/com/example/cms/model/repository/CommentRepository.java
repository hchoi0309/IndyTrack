package com.example.cms.model.repository;

import com.example.cms.model.entity.Comment;
import com.example.cms.model.entity.CommentKey;
import com.example.cms.model.entity.Course;
import com.example.cms.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, CommentKey> {
    @Query(value = "select * from courses c " +
            "where lower(c.id) like lower(concat('%', :searchTerm, '%')) ", nativeQuery = true)
    List<Comment> findCommentById(@Param("searchTerm") String searchTerm);


}
