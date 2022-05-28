package pj.hoangnv.repository.impl;

import org.springframework.data.mongodb.repository.MongoRepository;
import pj.hoangnv.entity.UserDocument;

public interface UserRepository extends MongoRepository<UserDocument, String> {
}
