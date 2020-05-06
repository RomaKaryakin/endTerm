package kz.karyakin.iitu.library.repository;

import kz.karyakin.iitu.library.entity.Weather;
import kz.karyakin.iitu.library.entity.ReleasedWeather;
import kz.karyakin.iitu.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReleasedWeatherRepository extends JpaRepository<ReleasedWeather, Long> {

    public ReleasedWeather findByUserAndWeatherAndActualReturnDate(User user, Weather weather, LocalDate actualReturnDate);

    public List<ReleasedWeather> findByExpectedReturnDateBeforeAndActualReturnDate(LocalDate nowDate, LocalDate actualReturnDate);

}
