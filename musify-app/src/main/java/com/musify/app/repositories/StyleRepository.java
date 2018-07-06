/**
*
*
*/
package com.musify.app.repositories;

import com.musify.app.entities.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StyleRepository extends JpaRepository<Style, Long>
{
    @Query("SELECT s FROM Style s WHERE LOWER(s.name) = LOWER(:name)")
    public List<Style> findByName(@Param("name") String name);
}
