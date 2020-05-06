package kz.karyakin.iitu.library.serivce;

import kz.karyakin.iitu.library.entity.Weather;
import kz.karyakin.iitu.library.entity.ReleasedWeather;
import kz.karyakin.iitu.library.entity.User;
import kz.karyakin.iitu.library.enums.WeatherStatus;
import kz.karyakin.iitu.library.enums.Status;
import kz.karyakin.iitu.library.repository.WeatherRepository;
import kz.karyakin.iitu.library.repository.ReleasedWeatherRepository;
import kz.karyakin.iitu.library.repository.RoleRepository;
import kz.karyakin.iitu.library.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final WeatherRepository weatherRepository;
    private final ReleasedWeatherRepository releasedWeatherRepository;
    private final RoleRepository roleRepository;


    public UserService(UserRepository userRepository,
                       WeatherRepository weatherRepository,
                       ReleasedWeatherRepository releasedWeatherRepository,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.weatherRepository = weatherRepository;
        this.releasedWeatherRepository = releasedWeatherRepository;
        this.roleRepository = roleRepository;
    }

    public Optional<User> findUserById(Long id){
        return userRepository.findById(id);
    }

   /* public ReleasedWeather issueBook(Weather weather, User user){
        if (weather.getWeatherStatus() == WeatherStatus.RETURNED) {
            weather.setBookStatus(WeatherStatus.ISSUED);
            weatherRepository.save(weather);
            ReleasedWeather releasedWeather = new ReleasedWeather(user, weather);
            return releasedWeatherRepository.save(releasedWeather);
        }
        else{
            System.out.println("The weather is not available");
            return null;
        }
    }
*/
/*
    public ReleasedWeather returnBook(Weather weather, User user){
        if (weather.getBookStatus() == WeatherStatus.ISSUED || weather.getBookStatus() == WeatherStatus.OVER_DUE_DATE) {
            ReleasedWeather releasedWeather = releasedWeatherRepository.findByUserAndBookAndActualReturnDate(user, weather, null);
            if (releasedWeather != null) {
                releasedWeather.setActualReturnDate(LocalDate.now());
                if (releasedWeather.getActualReturnDate().isAfter(releasedWeather.getExpectedReturnDate())) {
                    releasedWeather.getUser().setStatus(Status.UNSCRUPULOUS);
                    userRepository.save(releasedWeather.getUser());
                }
                releasedWeather.getBook().setBookStatus(WeatherStatus.RETURNED);
                weatherRepository.save(releasedWeather.getBook());
                return releasedWeatherRepository.save(releasedWeather);
            } else {
                System.out.println("It's not possible to return the weather. Please check user id and weather id");
            }
        }else{
            System.out.println("It's not possible to return the weather. Please check user id and weather id");
        }
        return null;
    }
*/


    public List<User> showAllUsers(){
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException("User with username: " + username + " is not found");

        return user;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
