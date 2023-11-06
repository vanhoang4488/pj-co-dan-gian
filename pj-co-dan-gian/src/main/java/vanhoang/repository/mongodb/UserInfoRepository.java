package vanhoang.repository.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vanhoang.entity.UserInfoEntity;

@Repository
public interface UserInfoRepository extends MongoRepository<UserInfoEntity, String> {
}
