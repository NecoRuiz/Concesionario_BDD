package database;

public interface SchemaDB {

    //AQUI SE DEFINIRAN LAS ESTRUCTURAS DE CADA TABLA
    //ALMACEN DE CONSTANTES
     //CADA BD/TABLA/COLUMNA SE TIENE QUE LLAMAR IGUAL QUE EN LA BD 

     String DB_NAME = "concesionario";

     String TAB_EMP = "empleados";

     String COL_EMP_NAME = "nombre";

     String COL_EMP_SALE = "ventas";

     String COL_EMP_SURNAME = "apellido";

     String COL_EMP_MAIL = "correo";

     String COL_EMP_PHONE = "telefono";

     String COL_EMP_TIP = "tipo";

     String TAB_TIPO = "tipos";

     String COL_TIP_DES = "descripcion";

     String COL_TIP_SIG = "siglas";

     String COL_ID = "id";

     String TAB_CAR = "coches";

     String COL_CAR_MARCA = "marca";

     String COL_CAR_MODEL = "modelo";

     String COL_CAR_PRECIO = "precio";

     String COL_CAR_CV = "cv";
}
