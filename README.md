# Proyecto - USUARIOS API
El siguiente documento define como funciona la api de usuarios creada para el proyecto final del curso Java Fundamentals.

# Pasos previos

### 1.- Creacion de la Base de Datos
Para la creación de la Base de datos se uso MySQL y Docker, por tanto se requiere tener instalado docker desktop en el
equipo (caso de windows). Una vez instalado Docker abrimos una terminal o consola y ubicamos la carpeta "scripts" que
se encuentra en la raiz del proyecto.

* Ejecutamos el siguiente comando:
```
> docker-compose up -d
Creating network "docker-mysql_default" with the default driver
Creating volume "docker-mysql_db" with local driver
Creating docker-mysql_db_1 ... done
```
* Esperamos un momento y revisamos ejecutando el siguiente comando:
```
> docker ps
CONTAINER ID   IMAGE       COMMAND                  CREATED          STATUS          PORTS                               NAMES
0d85fef96a07   mysql:8.0   "docker-entrypoint.s…"   1 minute ago   Up 1 minute   0.0.0.0:3306->3306/tcp, 33060/tcp   docker-mysql_db_1
```
### 2.- Ejecutar Api Usuarios

Para la ejecución de la api se debe contar con el software IntelliJ Idea 2023.1. Una vez instalado se debe abrir el
proyecto desde el software y una vez se cargue por completo, se procede a compilar y luego ejecutar presionando el boton
"Run" ó "Mayús+F10"

* En la consola se mostrará algo similar a la siguientes lineas, lo cual nos indica que ya esta corriendo nuestra api.

```
2023-06-21T00:19:25.560-04:00  INFO 19364 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2023-06-21T00:19:25.582-04:00  INFO 19364 --- [           main] SQL dialect                              : HHH000400: Using dialect: org.hibernate.dialect.MySQL8Dialect
2023-06-21T00:19:25.584-04:00  WARN 19364 --- [           main] org.hibernate.orm.deprecation            : HHH90000026: MySQL8Dialect has been deprecated; use org.hibernate.dialect.MySQLDialect instead
2023-06-21T00:19:26.597-04:00  INFO 19364 --- [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
2023-06-21T00:19:26.608-04:00  INFO 19364 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2023-06-21T00:19:27.214-04:00  INFO 19364 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2023-06-21T00:19:27.225-04:00  INFO 19364 --- [           main] t.e.usuariosapi.UsuariosApiApplication   : Started UsuariosApiApplication in 4.771 seconds (process running for 5.109)
```

# Pruebas de la API

### 1.- Cargar collección Postman
Para comenzar con las pruebas se requiere tener instalado el software Postman de consumo de api rest. Luego de instalarlo
se debe bajar el siguiente archivo que contiene los request que se utilizan con la api del proyecto.

* [Coleccion Postman](https://drive.google.com/file/d/1mnSo7SUwJA9Feti-rvtvIIDOTaVkucdS/view?usp=sharing)
* Si no puede descargarlo, tambien se encuentra en la carpeta "postman" de la raiz del proyecto.

### 2.- Ejecutando los servicios 
Para comenzar a visualizar datos, se debe primero realizar una creacion de usuario por tanto consumiremos el servicio
"crear usuario" 

* URL: localhost:8080/usuarios/
* Metodo: POST
* Body:
```
    {
        "nombre": "maria",
        "correo": "maria@gmail.com",
        "clave": "micasa123",
        "telefonos": [
            {
                "numero": 52634189,
                "codigoCiudad": 9,
                "codigoPais": 56
            },
            {
                "numero": 74851263,
                "codigoCiudad": 9,
                "codigoPais": 56
            }
        ]
    }
```
* Response:
```
    {
        "nombre": "maria",
        "correo": "maria@gmail.com",
        "clave": "micasa123",
        "telefonos": [
            {
                "telefonoId": 4,
                "numero": 52634189,
                "codigoCiudad": 9,
                "codigoPais": 56
            },
            {
                "telefonoId": 5,
                "numero": 74851263,
                "codigoCiudad": 9,
                "codigoPais": 56
            }
        ],
        "creado": "2023-06-21T23:23:24.324013",
        "modificado": "2023-06-21T23:23:24.324013",
        "isActive": 1,
        "id": 2
    }
```
Agregamos un usuario mas y ejecutamos el metodos "obtener usuarios" 

* URL: localhost:8080/usuarios/
* Metodo: GET
* Body:
* Response:
```
[
    {
        "nombre": "maria",
        "correo": "maria@gmail.com",
        "clave": "micasa123",
        "telefonos": [
            {
                "telefonoId": 4,
                "numero": 52634189,
                "codigoCiudad": 9,
                "codigoPais": 56
            },
            {
                "telefonoId": 5,
                "numero": 74851263,
                "codigoCiudad": 9,
                "codigoPais": 56
            }
        ],
        "creado": "2023-06-21T23:23:24",
        "modificado": "2023-06-21T23:23:24",
        "isActive": 1,
        "id": 2
    },
    {
        "nombre": "juan",
        "correo": "juan@gmail.com",
        "clave": "micasa123",
        "telefonos": [
            {
                "telefonoId": 52,
                "numero": 85263417,
                "codigoCiudad": 9,
                "codigoPais": 56
            },
            {
                "telefonoId": 53,
                "numero": 25143698,
                "codigoCiudad": 9,
                "codigoPais": 56
            }
        ],
        "creado": "2023-06-22T00:44:59",
        "modificado": "2023-06-22T00:44:59",
        "isActive": 1,
        "id": 52
    }
]
```
Ahora podemos modificar un usuario usuando su id (en este caso 52)

* URL: localhost:8080/usuarios/
* Metodo: PUT
* Body:
```
    {
        "nombre": "juan",
        "correo": "juanito@gmail.com",
        "clave": "123juan",
        "telefonos": [
            {          
                "numero": 74185263,
                "codigoCiudad": 9,
                "codigoPais": 56
            }
        ],
        "usuarioId": 52
    }
```
* Response:
```
{
    "nombre": "juan",
    "correo": "juanito@gmail.com",
    "clave": "123juan",
    "telefonos": [
        {
            "telefonoId": 54,
            "numero": 74185263,
            "codigoCiudad": 9,
            "codigoPais": 56
        },
        {
            "telefonoId": 52,
            "numero": 85263417,
            "codigoCiudad": 9,
            "codigoPais": 56
        },
        {
            "telefonoId": 53,
            "numero": 25143698,
            "codigoCiudad": 9,
            "codigoPais": 56
        }        
    ],
    "creado": "2023-06-22T00:44:59",
    "modificado": "2023-06-22T00:51:15",
    "isActive": 1,
    "id": 52
}
```

Para obtener el segundo registro de la Base de datos usariamos el siguiente servicio

* URL: localhost:8080/usuarios/2
* Metodo: GET
* Body:
* Response:
```
    {
        "nombre": "maria",
        "correo": "maria@gmail.com",
        "clave": "micasa123",
        "telefonos": [
            {
                "telefonoId": 4,
                "numero": 52634189,
                "codigoCiudad": 9,
                "codigoPais": 56
            },
            {
                "telefonoId": 5,
                "numero": 74851263,
                "codigoCiudad": 9,
                "codigoPais": 56
            }
        ],
        "creado": "2023-06-21T23:23:24",
        "modificado": "2023-06-21T23:23:24",
        "isActive": 1,
        "id": 2
    }
```



Si queremos el eliminar el primer registro de la Base de datos usariamos el siguiente servicio

* URL: localhost:8080/usuarios/1
* Metodo: DELETE
* Body:
* Response:
```
{
    "nombre": "marco",
    "correo": "marco@gmail.com",
    "clave": "marco234",
    "telefonos": [
        {
            "telefonoId": 1,
            "numero": 41526378,
            "codigoCiudad": 9,
            "codigoPais": 56
        },
        {
            "telefonoId": 2,
            "numero": 74859632,
            "codigoCiudad": 9,
            "codigoPais": 56
        },
        {
            "telefonoId": 5,
            "numero": 41859623,
            "codigoCiudad": 9,
            "codigoPais": 56
        }
    ],
    "creado": "2023-06-22T02:02:06",
    "modificado": "2023-06-22T02:02:58",
    "isActive": 1,
    "usuarioId": 1
}
```
