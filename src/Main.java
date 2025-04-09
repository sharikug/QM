import Controller.DocumentController;
import View.DocumentRegistrationView;

public class Main {
    public static void main(String[] args) {
//        DocumentRegistrationView view = new DocumentRegistrationView();
//        new DocumentController(view);

        DocumentRegistrationView view = new DocumentRegistrationView();
        new DocumentController(view);
    }
}
