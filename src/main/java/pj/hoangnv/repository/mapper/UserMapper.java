package pj.hoangnv.repository.mapper;

import org.apache.ibatis.annotations.Select;
import pj.hoangnv.repository.entity.UserDocument;

public interface UserMapper {

    @Select("db.users.findOne({username:#{username}})")
    UserDocument findUserByUsername(String username);

    @Select("db.users.findOne({gmail:#{gmail}})")
    UserDocument findUserByGmail(String gmail);
}
