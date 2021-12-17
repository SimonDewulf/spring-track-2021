package be.inetumrealdolmen.springtrack.repository;

import be.inetumrealdolmen.springtrack.dao.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, String> {
}
