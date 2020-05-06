package kz.karyakin.iitu.library.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class ReleasedWeather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID is generated by database")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NonNull
    @ApiModelProperty(value = "Which user got weather")
    private User user;

    @ManyToOne
    @JoinColumn(name = "weather_id")
    @NonNull
    @ApiModelProperty(value = "Which weather got user")
    private Weather weather;

    @Column(name = "issue_date")
    private LocalDate issueDate = LocalDate.now();

    @Column(name = "expected_return_date")
    private LocalDate expectedReturnDate = LocalDate.now().plusWeeks(1);

    @Column(name = "actual_return_date")
    private LocalDate actualReturnDate = null;

}
