package pj.hoangnv.repository.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import pj.hoangnv.repository.AbstractRepository;
import pj.hoangnv.repository.IRepository;
import pj.hoangnv.repository.RepositoryBuilder;
import pj.hoangnv.repository.entity.UserDocument;
import pj.hoangnv.repository.mapper.UserMapper;

import java.io.IOException;

@Repository
public class UserRepository extends AbstractRepository implements IRepository<UserDocument>{


    @Override
    public UserDocument findUserByUsername(String username) {
        try(SqlSession session = RepositoryBuilder.getInstance().openSession()){
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.findUserByUsername(username);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserDocument findUserByGmail(String gmail) {
        try(SqlSession session = RepositoryBuilder.getInstance().openSession()){
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.findUserByUsername(gmail);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
