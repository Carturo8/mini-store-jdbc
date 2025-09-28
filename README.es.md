# 🛒 Mini Store JDBC

> 📄 Este README también está disponible en [Inglés 🇬🇧](README.md)

Un sencillo **sistema de gestión de inventario** construido con **Java, JDBC y MySQL**.  
Permite agregar, listar, actualizar, eliminar y buscar productos mediante una interfaz de menú basada en Swing.

---

## ✨ Funcionalidades

- ➕ Agregar nuevos productos al inventario
- 📋 Listar todos los productos
- 💲 Actualizar el precio de un producto
- 📦 Actualizar el stock de un producto
- ❌ Eliminar productos
- 🔎 Buscar productos por nombre
- 📊 Resumen de operaciones realizadas en la sesión

---

## 📂 Estructura del Proyecto

```bash
mini-store-jdbc/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org.carturo.ministore/
│   │   │       ├── Main.java
│   │   │       ├── controller/
│   │   │       │   └── ProductController.java
│   │   │       ├── database/
│   │   │       │   └── ConnectionFactory.java
│   │   │       ├── entity/
│   │   │       │   └── Product.java
│   │   │       ├── repository/
│   │   │       │   ├── Repository.java
│   │   │       │   └── ProductRepositoryImpl.java
│   │   │       └── service/
│   │   │           ├── InventoryService.java
│   │   │           └── InventoryServiceImpl.java
│   │   └── resources/
│   │       ├── db.properties.example   # Plantilla de configuración de la base de datos
│   │       └── schema.sql              # Esquema de la base de datos
├── .gitignore
├── LICENSE
├── pom.xml
├── README.md
└── README.es.md
```

---

## ⚙️ Requisitos

- JDK 21+ (probado con JDK 24)
- Maven 3
- MySQL 8

---

## 🗄️ Configuración de la Base de Datos

1. Crear la base de datos y la tabla:

```sql
-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS mini_store_db;
USE mini_store_db;

-- Crear la tabla
CREATE TABLE IF NOT EXISTS products (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(150) NOT NULL UNIQUE,
  price DECIMAL(10,2) NOT NULL,
  stock INT NOT NULL
);
```

2. Opcionalmente, puedes importar el archivo `schema.sql` provisto.

---

## 🔑 Configuración

La conexión a la base de datos se gestiona mediante el archivo `db.properties` en `src/main/resources`.

Ejemplo (`db.properties.example`):

```bash
driver=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/mini_store_db
user=tu_usuario_aqui
password=tu_contraseña_aqui
```

Copia este archivo como `db.properties` y reemplaza `tu_usuario_aqui` y `tu_contraseña_aqui` con tus credenciales reales de MySQL.

---

## ▶️ Ejecutar la Aplicación

1. Compilar el proyecto con Maven:

```bash
mvn clean package
```

2. Ejecutar la aplicación usando el archivo `.jar` generado en el directorio `target/`:

```bash
java -cp target/mini-store-jdbc-1.0-SNAPSHOT.jar org.carturo.ministore.Main
```

La aplicación se iniciará con una interfaz de menú basada en Swing.

---

## 📜 Licencia

Este proyecto está licenciado bajo la [Licencia MIT](LICENSE).