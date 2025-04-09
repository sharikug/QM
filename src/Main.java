import Controller.DocumentController;
import view.DocumentRegistrationView;

public class Main {
    public static void main(String[] args) {
        DocumentRegistrationView view = new DocumentRegistrationView();
        new DocumentController(view);
    }
}
