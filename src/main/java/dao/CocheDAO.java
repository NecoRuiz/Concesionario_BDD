package dao;

import database.DBconnection;
import database.SchemaDB;
import model.Coche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//AQUI VAN TODAS LAS ACCIONES CONTRA LA BD
public class CocheDAO {

    private Connection connection;

    private PreparedStatement preparedStatement;

    private ResultSet resultSet;

    ArrayList<Coche> listaResultado = new ArrayList<>();

    public CocheDAO(){
        //llamo a la conexion creada
        connection = new DBconnection().getConnection();
    }
//metodos necesarios(los que queramos)
    //añadir un coche a la BD
    public void addCoche(Coche coche) throws SQLException {
        //ME CREO LA QUERY
        String query = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES (?,?,?,?)",
                SchemaDB.TAB_CAR, SchemaDB.COL_CAR_MARCA, SchemaDB.COL_CAR_MODEL, SchemaDB.COL_CAR_CV, SchemaDB.COL_CAR_PRECIO);
        //INTRODUZCO LA QUERY EN EL PREPARESTATEMENTE PREVIAMENTE CREADO
        preparedStatement = connection.prepareStatement(query);

        //introduzco valores
        preparedStatement.setString(1, coche.getMarca());
        preparedStatement.setString(2, coche.getModelo());
        preparedStatement.setInt(3, coche.getCv());
        preparedStatement.setInt(4, coche.getPrecio());
        //ejecuto la introduccion de valores
        preparedStatement.execute();
        //atraves de la conexion me creo el PrepareStatement



    }

    public ArrayList<Coche> getCochesMarca(String marcaParam) throws SQLException {

        String query = String.format("SELECT * FROM %s WHERE %s=?",SchemaDB.TAB_CAR,SchemaDB.COL_CAR_MARCA);

        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1,marcaParam);
        resultSet = preparedStatement.executeQuery();
        return getResultados(resultSet);
    }

    public ArrayList<Coche> getCochePrecio(int precioParam) throws SQLException {

        ArrayList<Coche> listaResultados = new ArrayList<>();

        String query = String.format("SELECT * FROM %s WHERE %s <= ?", SchemaDB.TAB_CAR, SchemaDB.COL_CAR_PRECIO);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,precioParam);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            String marca = resultSet.getNString(SchemaDB.COL_CAR_MARCA);
            String modelo = resultSet.getNString(SchemaDB.COL_CAR_MODEL);
            int cv = resultSet.getInt(SchemaDB.COL_CAR_CV);
            int precio = resultSet.getInt(SchemaDB.COL_CAR_PRECIO);
            listaResultado.add(mapearCoche(marca, modelo, cv, precio));
        }

        return null;
    }


    private ArrayList<Coche> getResultados(ResultSet datosResultantes) throws SQLException {

        ArrayList<Coche> listaResultado = new ArrayList<>();

        while (datosResultantes.next()){
            String marca = resultSet.getNString(SchemaDB.COL_CAR_MARCA);
            String modelo = resultSet.getNString(SchemaDB.COL_CAR_MODEL);
            int cv = resultSet.getInt(SchemaDB.COL_CAR_CV);
            int precio = resultSet.getInt(SchemaDB.COL_CAR_PRECIO);
            listaResultado.add(mapearCoche(marca, modelo, cv, precio));
        }

        return listaResultado;

    }


    private Coche mapearCoche(String marca, String modelo, int cv, int precio){
        return new Coche(marca, modelo, cv,precio);
    }

    public void realizarVenta(int id){
        //DELETE -> WHERE id= id


    }

}
