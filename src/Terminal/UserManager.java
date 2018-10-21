package Terminal;

public class UserManager {
    public static void moveUser(Planet planet, User user) {
        if (planet != null)
            user.planet = planet;
    }
}
