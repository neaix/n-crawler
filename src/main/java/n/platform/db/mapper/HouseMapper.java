package n.platform.db.mapper;

import org.apache.ibatis.annotations.Delete;

public interface HouseMapper {

    @Delete("delete from t_house where id=#{id}")
    int rmove(long id);
}
