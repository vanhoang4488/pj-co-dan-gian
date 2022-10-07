package pj.hoangnv.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pj.hoangnv.entity.model.BaseDocument;

import java.util.Collection;
import java.util.Collections;

@Document(collection = "users")
public class UserDocument extends BaseDocument implements UserDetails {

    private static final long serialVersionUID = 1L;

    @MongoId(targetType = FieldType.STRING)
    private String gmail;
    private String password;

    @Getter
    @Setter
    private String username;

    /*
     * hiện tại do không có nhu cầu, và sự khác biệt:
     * giữa admin và user nên ta có thể bỏ qua phần này
     * và gán mọi người là vai trò user.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority grant = new SimpleGrantedAuthority("ROLE_USER");
        return Collections.singletonList(grant);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return gmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // chưa có nhu cầu khóa tài khoản người dùng
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Tài khoản không cần kích hoạt cứ thế vào chơi luôn.
    @Override
    public boolean isEnabled() {
        return true;
    }
}
