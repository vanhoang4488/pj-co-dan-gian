package vanhoang.repository.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import vanhoang.entity.User;

@Repository
public interface UserRespository extends MongoRepository<User, String> {

    @Query ("{'loginName':  ?0}")
    User findUserByLoginName (String loginName);

    @Query ("{'email' : ?0}")
    User findUserByEmail (String email);
}
