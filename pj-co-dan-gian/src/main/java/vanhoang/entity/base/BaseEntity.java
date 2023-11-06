package vanhoang.entity.base;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {

    @MongoId(targetType = FieldType.OBJECT_ID)
    private String id;

    private Date createTime;
    private Date updateTime;

    public BaseEntity() {
        this.updateTime = new Date();
    }
}
