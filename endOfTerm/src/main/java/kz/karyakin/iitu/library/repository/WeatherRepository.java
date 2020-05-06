package kz.karyakin.iitu.library.repository;

import kz.karyakin.iitu.library.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {

    @Query("SELECT b FROM Weather b WHERE lower(b.broadcasters.name) LIKE %:search% OR lower(b.title) LIKE %:search% OR lower(b.description) LIKE %:search%")
    List<Weather> findAllByTitleIsContainingOrDescriptionIsContainingOrBroadcastersNameIsContainingIgnoreCase(@Param("search") String search);

}
