//package vanhoang.repository.ldap;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.ldap.core.AttributesMapper;
//import org.springframework.ldap.core.DirContextAdapter;
//import org.springframework.ldap.core.DirContextOperations;
//import org.springframework.ldap.core.LdapTemplate;
//import org.springframework.stereotype.Repository;
//
//import javax.naming.ldap.LdapName;
//import java.util.List;
//
//@Repository
//@RequiredArgsConstructor
//public class LdapRepository {
//
//    public final LdapTemplate ldapTemplate;
//
//    public void addUser (DirContextAdapter context) {
//        ldapTemplate.bind(context);
//    }
//
//    public void updateUser (DirContextOperations context) {
//        ldapTemplate.modifyAttributes(context);
//    }
//
//    public DirContextOperations findOne (LdapName rdn) {
//        return ldapTemplate.lookupContext(rdn);
//    }
//
//    public List<String> findList (String username) {
//        return ldapTemplate.search("ou=people", "uid=" + username, (AttributesMapper<String>) attrs -> (String) attrs.get("uid").get());
//    }
//}
