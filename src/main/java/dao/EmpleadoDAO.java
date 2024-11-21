package dao;

import database.DBconnection;
import database.SchemaDB;
import model.Empleado;
import model.Tipo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//AQUI VAN TODAS LAS ACCIONES CONTRA LA BD

public class EmpleadoDAO {

    private Connection connection;

    private PreparedStatement preparedStatement;

    private ResultSet resultSet;

    public EmpleadoDAO(){
        //llamo a la conexion creada en la linea 12 y la llamo
        connection = new DBconnection().getConnection();

    }

    public void insertarEmpleado(Empleado empleado) throws SQLException {

        preparedStatement = connection.prepareStatement(String.format("INSERT INTO %s (%s,%s,%s,%s,%s) VALUES (?,?,?,?,?)",
                SchemaDB.TAB_EMP,
                SchemaDB.COL_EMP_NAME,SchemaDB.COL_EMP_SURNAME,SchemaDB.COL_EMP_PHONE,SchemaDB.COL_EMP_MAIL,SchemaDB.COL_EMP_TIP));

        preparedStatement.setString(1,empleado.getNombre());
        preparedStatement.setString(2,empleado.getApellido());
        preparedStatement.setInt(3,empleado.getTelefono());
        preparedStatement.setString(4,empleado.getCorreo());
        preparedStatement.setInt(5,empleado.getTipo().getId());
        preparedStatement.execute();

    }

    public void obtenerEmpleadoMes(int numero) throws SQLException {

        String query = "SELECT * FROM %s ORDER BY %s DESC LIMIT ?";
        preparedStatement = connection.prepareStatement(String.format(query,SchemaDB.COL_EMP_SALE));
        preparedStatement.setInt(1, numero);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){

            String nombre = resultSet.getNString(SchemaDB.COL_EMP_NAME);
            String apellid = resultSet.getNString(SchemaDB.COL_EMP_SURNAME);
            Empleado empleado = new Empleado();
            empleado.mostrarDatos();

        }

    }

    public void realizarVenta(int id) throws SQLException {
        String query = "UPDATE %s SET %s = %s+1 WHERE %s = ?";
        preparedStatement = connection.prepareStatement(String.format(query,SchemaDB.TAB_EMP,
                SchemaDB.COL_EMP_SALE, SchemaDB.COL_EMP_SALE, SchemaDB.COL_ID));
        preparedStatement.setInt(1,id);
        preparedStatement.execute();
    }

    public Empleado getEmpleado(int id) throws SQLException {
        preparedStatement = connection.prepareStatement(String.format("SELECT * FROM %s WHERE %s=?",SchemaDB.TAB_EMP,SchemaDB.COL_ID));
        preparedStatement.setInt(1,id);
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){

            String nombre = resultSet.getNString(SchemaDB.COL_EMP_NAME);
            String apellido = resultSet.getNString(SchemaDB.COL_EMP_SURNAME);
            String correo = resultSet.getNString(SchemaDB.COL_EMP_MAIL);
            int telefono = resultSet.getInt(SchemaDB.COL_EMP_PHONE);
            int tipo = resultSet.getInt(SchemaDB.COL_EMP_TIP);

            return getEmpleado(nombre,apellido,correo,telefono,tipo);
        }
        return null;
    }

    public Empleado getEmpleado(String nombre, String apellido, String correo, int telefono, int tipo){

        Tipo tipo1 = null;

        switch (tipo){
            case 1:
                tipo1 = Tipo.EXTERNO;
                break;
            case 2:
                tipo1 = Tipo.INDEFINIDO;
                break;
            case 3:
            tipo1 = Tipo.BECARIO;
            break;



        }

        return new Empleado(nombre, apellido, correo, telefono,tipo1);

    }
}
