package pj.hoangnv.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface IRepository <T>{

    T findUserByUsername(String username);
    T findUserByGmail(String gmail);
}
