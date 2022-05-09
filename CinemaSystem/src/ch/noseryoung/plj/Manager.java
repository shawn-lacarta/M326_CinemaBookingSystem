package ch.noseryoung.plj;


import ch.noseryoung.plj.cinema.Cinema;
import ch.noseryoung.plj.person.admin.Admin;
import ch.noseryoung.plj.person.user.User;

/**
 * Logical procedure is captured here
 */
public class Manager {

    IO io = new IO();
    Admin admin = new Admin();
    User user = new User();

    /**
     * Starter menu that is here to start everything up
     */
    public void starter() {
        int answer = 1;

        while (answer <= 4 && answer >= 1) {
            answer = io.startProgram();

            switch (answer) {
                case 1 -> user.loginUser();
                case 2 -> admin.loginAdmin();
                case 3 -> user.createUser();
            }
        }
    }

}
