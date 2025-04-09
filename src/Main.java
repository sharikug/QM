import controllers.DocumentoControlador;
import views.InterfazPrincipal;

//import controllers.ReporteControlador;
//import views.ReporteVista;

public class Main {
    public static void main(String[] args) {
        InterfazPrincipal vista = new InterfazPrincipal();
        new DocumentoControlador(vista);

//        ReporteVista vista = new ReporteVista();
//        new ReporteControlador(vista);
    }
}