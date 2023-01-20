## DemoJndi2

[![en](https://img.shields.io/badge/lang-en-red.svg)](/README.en.md)
[![es](https://img.shields.io/badge/lang-es-yellow.svg)](/README.md)

Ejemplo de Spring boot con varios jndi con JPA

Los datos del jndi del tomcat embebido están en [DemoJndi2Application.java](/src/main/resources/application.properties)


Base datos 1 (db1):

~~~
-- bdlocal.asistentes definition
CREATE TABLE `asistentes` (
  `id` bigint NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `dni` varchar(45) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE bdlocal.asistentes MODIFY COLUMN id bigint auto_increment NOT NULL;
~~~

Base datos 2 (db2):

~~~
-- bdlocal2.personas definition
CREATE TABLE `personas` (
  `id` bigint NOT NULL,
  `nombre` varchar(145) DEFAULT NULL,
  `apellidos` varchar(145) DEFAULT NULL,
  `dni` varchar(45) DEFAULT NULL,
  `fechanac` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE bdlocal2.personas MODIFY COLUMN id bigint auto_increment NOT NULL;
~~~


Más info:  
<https://www.baeldung.com/spring-boot-configure-multiple-datasources>  
<https://stackoverflow.com/questions/45663025/spring-data-jpa-multiple-enablejparepositories>  
<https://stackoverflow.com/questions/41941147/configuring-two-datasources-by-jndi-lookup-with-springboot>

