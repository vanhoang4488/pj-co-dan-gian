package vanhoang.repository.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import vanhoang.entity.UserEntity;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {

    @Query ("{'loginName':  ?0}")
    UserEntity findUserByLoginName (String loginName);

    @Query ("{'email' : ?0}")
    UserEntity findUserByEmail (String email);
}
