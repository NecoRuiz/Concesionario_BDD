import controller.Concesionario;
import database.DBconnection;
import model.Empleado;
import model.Tipo;

import java.sql.Connection;
import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) {

   /* DBconnection dBconnection = new DBconnection();
    Connection connection = dBconnection.getConnection();
    dBconnection.closeConnection(); */
        Concesionario concesionario = new Concesionario();
        //concesionario.insertarTrabajador(new Empleado("Juan","Ruiz","juan86@correo.com",748974,Tipo.INDEFINIDO));
        //concesionario.agregarCoche();


        Scanner scanner = new Scanner(System.in);


        //mostrar el menu
        System.out.println("Selecciona una opcion:");
        System.out.println("1. Insertar tarbajador");
        System.out.println("2. Borrar trabajdor");
        System.out.println("3. Leer trabajdor");
        System.out.println("4. Agregar coche");
        System.out.println("5. Filtrar precio");
        System.out.println("6. Realizar venta");
        System.out.println("7. Mostrar empleado del mes");
        System.out.println("8. Salir");

        int opcion = scanner.nextInt();

        switch (opcion){

            case 1:
                concesionario.insertarTrabajador(new Empleado("Juan","Ruiz","juan86@correo.com",748974,Tipo.INDEFINIDO));

                break;

            case 2:
                concesionario.borrarTrabajador(1);

                break;

            case 3:
                concesionario.leerTrabajadores();

                break;
            case 4:
                concesionario.agregarCoche();

                break;

            case 5:
                concesionario.filtrarPrecio();

                break;

            case 6:
                concesionario.realizarVenta();

                break;

            case 7:
                concesionario.mostrarEmpleadosMes(1);

                break;

            case 8:
                break;
            default:
                System.out.println("Opcion no valida, por favor escoja otra opcion.");

        }
            scanner.close();
    }
}
