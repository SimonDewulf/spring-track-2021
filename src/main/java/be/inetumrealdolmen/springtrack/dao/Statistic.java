package be.inetumrealdolmen.springtrack.dao;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Statistic {

    @Id
    @Enumerated(EnumType.STRING)
    private StatisticProperty property;
    private BigDecimal value;
}
