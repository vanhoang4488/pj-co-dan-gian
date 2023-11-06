//package vanhoang.service;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.ldap.core.DirContextAdapter;
//import org.springframework.ldap.core.DirContextOperations;
//import org.springframework.ldap.support.LdapEncoder;
//import org.springframework.ldap.support.LdapNameBuilder;
//import org.springframework.stereotype.Service;
//import vanhoang.repository.ldap.LdapRepository;
//
//import javax.naming.ldap.LdapName;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class LdapService {
//
//    public final LdapRepository ldapRepository;
//
//    private LdapName buildRDN (String username) {
//        return LdapNameBuilder
//                .newInstance()
//                .add("ou", "people")
//                .add("uid", username)
//                .build();
//    }
//
//    public void createUser (String username, String password) {
//        LdapName rdn = buildRDN(username);
//        DirContextAdapter context = new DirContextAdapter(rdn);
//
//        context.setAttributeValues("objectclass", new String[] {"top", "person", "organizationalPerson", "inetOrgPerson"});
//        context.setAttributeValue("cn", username);
//        context.setAttributeValue("sn", username);
//        context.setAttributeValue("userPassword", LdapEncoder.printBase64Binary(LdapEncoder.nameEncode(password).getBytes()));
//
//        ldapRepository.addUser(context);
//    }
//
//    public void updateUser (String username, String password) {
//        LdapName rdn = buildRDN(username);
//        DirContextOperations context = ldapRepository.findOne(rdn);
//
//        context.setAttributeValues("objectclass", new String[] {"top", "person", "organizationalPerson", "inetOrgPerson"});
//        context.setAttributeValue("cn", username);
//        context.setAttributeValue("sn", username);
//        context.setAttributeValue("userPassword", LdapEncoder.printBase64Binary(LdapEncoder.nameEncode(password).getBytes()));
//
//        ldapRepository.updateUser(context);
//    }
//
//    public List<String> findUsers (String username) {
//        return ldapRepository.findList(username);
//    }
//}
