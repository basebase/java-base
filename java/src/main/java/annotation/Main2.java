package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author xiaomoyu
 * @Date: 2022/6/7 15:40:59
 * @Description:
 */
public class Main2 {

    @UserCase(id = 47, desc = "password must contain at least one numeric")
    public boolean validatePwd(String passwd) {
        return passwd.matches("\\w*\\d\\w*");
    }


    @UserCase(id = 48)
    public String entryptPwd(String passwd) {
        return new StringBuilder(passwd)
                .reverse().toString();
    }

    @UserCase(id = 49, desc = "New passwords can't equal previously used ones")
    public boolean checkForNewPwd(List<String> prevPasswords, String passwd) {
        return !prevPasswords.contains(passwd);
    }

    public static void main(String[] args) {
        Main2 m2 = new Main2();
        System.out.println(m2.validatePwd("dsadasd"));
        System.out.println(m2.entryptPwd("dasdsads"));
        System.out.println(m2.checkForNewPwd(Arrays.asList("dsadasdas", "dsadadsa"), "dsadasdas"));
    }
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface UserCase {
    int id();
    String desc() default "no desc";
}