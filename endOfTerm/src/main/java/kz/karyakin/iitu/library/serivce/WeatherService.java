package kz.karyakin.iitu.library.serivce;

import kz.karyakin.iitu.library.entity.Weather;
import kz.karyakin.iitu.library.repository.WeatherRepository;
import kz.karyakin.iitu.library.repository.ReleasedWeatherRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class WeatherService {

    private final WeatherRepository weatherRepository;
    private final ReleasedWeatherRepository releasedWeatherRepository;

    public WeatherService(WeatherRepository weatherRepository, ReleasedWeatherRepository releasedWeatherRepository) {
        this.releasedWeatherRepository = releasedWeatherRepository;
        this.weatherRepository = weatherRepository;
    }

    public List<Weather> showAllWeather(){
        return weatherRepository.findAll();
    }

    public Weather addWeather(Weather weather){
        return weatherRepository.save(weather);
    }

    public List<Weather> findWeather(String search){
        return weatherRepository.findAllByTitleIsContainingOrDescriptionIsContainingOrBroadcastersNameIsContainingIgnoreCase(search);
    }

    public void deleteWeather(Long id){
        weatherRepository.deleteById(id);
    }

    public Optional<Weather> findWeatherById(Long id){
        return weatherRepository.findById(id);
    }

//    @Scheduled(cron = "*/10 * * * * *")
  /*  public void checkIfBookDueToDate() {
        for (ReleasedWeather releasedWeather : issuedBooksRepository.findByExpectedReturnDateBeforeAndActualReturnDate(LocalDate.now(), null)) {
            Weather weather = releasedWeather.getWeather();
            weather.setWeatherStatus(WeatherStatus.OVER_DUE_DATE);
            weatherRepository.save(weather);
        }
    }
*/
}
