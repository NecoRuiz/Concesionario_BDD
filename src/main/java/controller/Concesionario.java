package controller;

import dao.CocheDAO;
import dao.EmpleadoDAO;
import database.DBconnection;
import database.SchemaDB;
import model.Coche;
import model.Empleado;

import java.sql.*;
import java.util.Scanner;

public class Concesionario {
//LLAMADA AL DAO DE EMPLEADO
    private EmpleadoDAO empleadoDAO;
//LLAMADA AL DAO DE COCHE
    private CocheDAO cocheDAO;

    Scanner scanner = new Scanner(System.in);

    public Concesionario(){
        empleadoDAO = new EmpleadoDAO();
//LO INICIALIZO
        cocheDAO = new CocheDAO();

    }


//Statement-->QUERY DIRECTA
//PrepareStatetment-->ES UN MODELO DE QUERY QUE SIMPLEMENTE SE "RELLENA"
    public void insertarTrabajdorDAO(Empleado empleado){
    //logica del negocio
        try {
            empleadoDAO.insertarEmpleado(empleado);
        } catch (SQLException e) {
            System.out.println("Error en insercion edl empleado.");
        }

    }

    public void insertarTrabajador(Empleado empleado){
//PARA HACER UNA QUERY SERA NECESARIO= Connection-->PrepareStatement(query)-->execute
        Connection connection = new DBconnection().getConnection();
        //ya puedo acceder a la BD
        try {
            Statement statement = connection.createStatement();
            String PSquery = String.format("INSERT INTO %s (%s,%s,%s,%s,%s) VALUES (?,?,?,?,?)",
                    SchemaDB.TAB_EMP,
                    SchemaDB.COL_EMP_NAME,SchemaDB.COL_EMP_SURNAME,SchemaDB.COL_EMP_MAIL,SchemaDB.COL_EMP_PHONE,SchemaDB.COL_EMP_TIP);
            PreparedStatement preparedStatement = connection.prepareStatement(PSquery);
            preparedStatement.setString(1,empleado.getNombre());
            preparedStatement.setString(2,empleado.getApellido());
            preparedStatement.setString(3,empleado.getCorreo());
            preparedStatement.setInt(4,empleado.getTelefono());
            preparedStatement.setInt(5,empleado.getTipo().getId());
            preparedStatement.execute();
            //PREPARESTATEMENT
            //%s ->String
            //%d ->int
            //%f ->float
            //%b ->Boolean
            //%c ->char
            //AQUI SE ESCRIBE LA QUERY Y SE ALMACENA EN UN STRING QUE LUEGO VA A SER EJECUTADO

            //statement.execute(query);

        } catch (SQLException e) {
            System.out.println("Error.");
        }





    }

    public void borrarTrabajador(int id){
        Connection connection = new DBconnection().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM "+SchemaDB.TAB_EMP+" WHERE id=?");
            preparedStatement.setInt(1,id);
            int row = preparedStatement.executeUpdate();
            System.out.println("El numero de registros borrados es de "+row);
        } catch (SQLException e) {
            System.out.println("Error en la creacion de la query");
        }
    }

    public void leerTrabajadores(){
//Connection -> PrepareStatement -> excuteQuery -> Resulset
        Connection connection = new DBconnection().getConnection();
        String query = "SELECT * FROM "+SchemaDB.TAB_EMP;
        PreparedStatement preparedStatement;
        try {
             preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            //V
            //R,R,R,R,R

            while (resultSet.next()){//mientras que haya cosas que analizar
                String nombre = resultSet.getNString(SchemaDB.COL_EMP_NAME);
                String tipo = resultSet.getString(SchemaDB.COL_EMP_TIP);
                System.out.println("Nombre del empledo "+nombre+" Y el tipo es "+tipo);


            }
        } catch (SQLException e) {
            System.out.println("Error en la query.");
        }
    }

    public void agregarCoche(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce marca");
        String marca = scanner.next();

        System.out.println("Introduce modelo");
        String modelo = scanner.next();

        System.out.println("Introduce los caballos");
        int caballos = scanner.nextInt();

        System.out.println("Introduce precio");
        int precio = scanner.nextInt();



        //LLAMO AL METODO addCoche INSTANCIANDO UN OBJ DE TIPO COCHE
        try {
           if (cocheDAO.getCochesMarca(marca).size() <2){
                //CREO LA INSTACIA + METODO
                cocheDAO.addCoche(new Coche(marca,modelo,caballos,precio));
                System.out.println("Coche comprado correctamente.");
            } else {
                System.out.println("No interesa comprar el coche al concesionario.");
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void filtrarPrecio(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Indica el precio para filtar la busqueda: ");
        int precio = scanner.nextInt();

        try {
            for (Coche coche : cocheDAO.getCochePrecio(precio)){
                coche.mostrarDatos();
            }

        } catch (SQLException e) {
            System.out.println("No se pudo realizar la busqueda.");
        }


    }

    public void realizarVenta(){

        System.out.println("Dime el coche que vas a vender.");
        int idCoche =0;

        System.out.println("Dime el que hace la venta.");
        int idEmpleado =0;
        try {
            cocheDAO.realizarVenta(1);
            empleadoDAO.realizarVenta(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void mostrarEmpleadosMes(int numero){

        System.out.println("Dime cuantos empleados quieres sacar: ");
        int numeroEmpleados = 0;
        try {
            empleadoDAO.obtenerEmpleadoMes(3);
        } catch (SQLException e) {
            System.out.println("Error a la hora de obtenerlos.");
        }

    }
}
