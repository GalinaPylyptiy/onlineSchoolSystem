package util;
import dao.impl.AccessDAOImpl;
import java.util.Map;

public class AccessChecker {
    private static AccessDAOImpl accessService = new AccessDAOImpl();
    private static Map<String, String> loginAndPassword;

    public static boolean isTeacherRegistered(String login, String password) {
        loginAndPassword = accessService.getTeachersLoginAndPasswords();
        return checkAccess(login,password, loginAndPassword);
    }
    public  static boolean isStudentRegistered (String login, String password){
        loginAndPassword= accessService.getStudentsLoginAndPasswords();
        return checkAccess(login,password, loginAndPassword);
    }
    private static boolean checkAccess(String login, String password, Map <String, String>loginAndPassword) {
        for (Map.Entry<String, String> pair : loginAndPassword.entrySet()) {
            if (pair.getKey().equals(login) && pair.getValue().equals(password)){
                return true;
            }
        }
        return false;
    }



}
