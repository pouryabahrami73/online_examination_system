package ir.pb.online_examination_system.meta_model;

import ir.pb.online_examination_system.domains.User;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {
    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, String> userName;
    public static volatile SingularAttribute<User, String> roles;
    public static volatile SingularAttribute<User, String> passWord;
    public static volatile SingularAttribute<User, Boolean> active;
    public static volatile SingularAttribute<User, Long> nationalCode;
    public static final String FIRSTNAME = "firstName";
    public static final String LASTNAME = "lastName";
    public static final String USERNAME = "userName";
    public static final String ROLES = "userName";
    public static final String PASSWORD = "userName";
    public static final String ACTIVE = "active";
    public static final String NATIONAL_CODE = "nationalCode";
}
