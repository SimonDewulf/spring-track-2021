package be.inetumrealdolmen.springtrack.service.impl;

import be.inetumrealdolmen.springtrack.dao.Game;
import be.inetumrealdolmen.springtrack.data.GameData;
import be.inetumrealdolmen.springtrack.data.StatisticsData;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Profile("softStatistics")
public class SoftStatisticsGameServiceImpl extends GameServiceImpl {

    public SoftStatisticsGameServiceImpl(GameData gameDataAccess, StatisticsData statisticsData) {
        super(gameDataAccess, statisticsData);
    }

    @Override
    @Transactional(Transactional.TxType.NEVER)
    public Game move(String gameId) {
        return super.move(gameId);
    }
}
