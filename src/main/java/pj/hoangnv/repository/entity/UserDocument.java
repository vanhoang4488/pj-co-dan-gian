package pj.hoangnv.repository.entity;

import lombok.Data;
import pj.hoangnv.repository.entity.common.BaseDocument;

@Data
public class UserDocument extends BaseDocument {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String gmail;
    private String userId;
}
