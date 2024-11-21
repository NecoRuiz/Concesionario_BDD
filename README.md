# Concesionario de Coches

## Descripción

Este proyecto implementa un sistema de gestión para un concesionario de coches. El sistema está diseñado para gestionar coches, empleados y ventas a través de un menú interactivo. Está conectado a una base de datos SQL usando un servidor **XAMPP** y utiliza el patrón de diseño **Singleton** para la gestión de la conexión a la base de datos.

## Funcionalidades

El sistema permite realizar las siguientes operaciones:

### **Gestión de Coches**:
- Insertar un nuevo coche con los siguientes atributos:
  - **Marca**: Marca del coche.
  - **Modelo**: Modelo del coche.
  - **CV**: Potencia del coche en caballos de vapor.
  - **ID**: Identificador único del coche.
- Eliminar un coche por su **ID**.

### **Gestión de Empleados**:
- Insertar nuevos empleados con los siguientes atributos:
  - **Nombre**: Nombre del empleado.
  - **Apellido**: Apellido del empleado.
  - **Correo**: Dirección de correo electrónico.
  - **Teléfono**: Número de teléfono.
  - **Tipo**: Tipo de empleado (Indefinido, Becario, Externo).
- Consultar la lista de empleados.
- Eliminar un empleado por su **ID**.

### **Ventas**:
- Registrar una venta de un coche a un cliente.

### **Empleado del Mes**:
- Mostrar al **empleado del mes** basado en criterios específicos, como desempeño o ventas.

## Estructura del Proyecto

El proyecto está organizado en varios paquetes para mantener la lógica separada y facilitar el mantenimiento:

- **model**: Contiene las clases que representan los objetos del sistema, tales como **Coche** y **Empleado**.
- **database**: Gestiona la conexión con la base de datos MySQL usando **XAMPP**. Implementa el patrón **Singleton** para asegurar que solo haya una instancia de la conexión a la base de datos.
- **interface**: Se encarga de interactuar con el esquema de la base de datos y gestionar las operaciones de inserción, eliminación y consulta.
- **DAO (Data Access Object)**: Cada entidad (Coche y Empleado) tiene su propia clase DAO para realizar las operaciones CRUD de manera desacoplada.

## Requisitos

Para ejecutar este proyecto, necesitas tener los siguientes requisitos:

- **XAMPP** (con MySQL) para la base de datos.
- **Java** (preferiblemente versión 8 o superior).
- Un cliente **SQL** como **DBeaver** para gestionar la base de datos.
- **Conexión a Internet** (si es necesario para dependencias adicionales).
