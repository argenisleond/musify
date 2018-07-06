/**
*
*
*/
package com.musify.app.repositories;

import com.musify.app.entities.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PeopleRepository extends JpaRepository<People, Long>
{
    @Query("SELECT p FROM People p WHERE LOWER(p.name) = LOWER(:name)")
    public List<People> findByName(@Param("name") String name);

    @Query("SELECT p FROM People p WHERE p.years = :years")
    public List<People> findByYear(@Param("years") Integer years);

    @Query(value = "SELECT p FROM People p WHERE p.years = :years and LOWER(p.name) = LOWER(:name)")
    public List<People> findByNameAndYear(@Param("name") String name, @Param("years") Integer years);
}
