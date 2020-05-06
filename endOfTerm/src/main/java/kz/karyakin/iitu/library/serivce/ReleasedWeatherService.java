package kz.karyakin.iitu.library.serivce;

import kz.karyakin.iitu.library.entity.ReleasedWeather;
import kz.karyakin.iitu.library.repository.ReleasedWeatherRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReleasedWeatherService {

    private final ReleasedWeatherRepository releasedWeatherRepository;

    public ReleasedWeatherService(ReleasedWeatherRepository releasedWeatherRepository) {
        this.releasedWeatherRepository = releasedWeatherRepository;
    }

    public List<ReleasedWeather> findAll(){
        return releasedWeatherRepository.findAll();
    }

    public ReleasedWeather findById(Long id) {
        return releasedWeatherRepository.findById(id).orElse(null);
    }
}
