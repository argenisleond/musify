/**
*
*
*/
package com.musify.app.repositories;

import com.musify.app.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long>
{
    @Query("SELECT a FROM Artist a WHERE LOWER(a.name) = LOWER(:name)")
    public List<Artist> findByName(@Param("name") String name);

    @Query("SELECT a FROM Artist a WHERE a.year = :year")
    public List<Artist> findByYear(@Param("year") Integer year);

    @Query(value = "SELECT a FROM Artist a WHERE a.year = :year and LOWER(a.name) = LOWER(:name)")
    public List<Artist> findByNameAndYear(@Param("name") String name, @Param("year") Integer year);
}
