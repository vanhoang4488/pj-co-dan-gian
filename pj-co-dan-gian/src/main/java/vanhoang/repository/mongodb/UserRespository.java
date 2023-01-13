package vanhoang.repository.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vanhoang.entity.User;

@Repository
public interface UserRespository extends MongoRepository<User, String> {
}
