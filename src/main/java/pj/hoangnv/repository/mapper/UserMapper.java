package pj.hoangnv.repository.mapper;

import org.apache.ibatis.annotations.Select;
import pj.hoangnv.repository.entity.UserDocument;

public interface UserMapper {

    @Select("select * from users where username = #{username})")
    UserDocument findUserByUsername(String username);

    @Select("select * from users where gmail = #{gmail})")
    UserDocument findUserByGmail(String gmail);
}
