package dao;

import entity.Level;
import java.util.List;

public interface LevelDAO {

    Level getLevelByLevelName(String levelName);
    List<Level> getAllLevels();

}
