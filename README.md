# 🛒 Sistema de Inventario con  Roles

Este proyecto es una aplicación web desarrollada con Spring Boot, Thymeleaf, que permite gestionar un sistema de inventario con autenticación de usuarios y control de acceso por roles.

---

## ✅ Características

- Registro de usuarios
- Inicio de sesión con segurididad
- Roles: `ROLE_USER` y `ROLE_ADMIN`
- Listado de usuarios (solo para admin)
- Búsqueda por nombre o email
- Gestión de entidades: Usuario, Producto, Categoría, Marca, Carrito de compras (en progreso)
- Seguridad con Spring Security y BCrypt
- Persistencia con JPA y base de datos H2/MySQL

---

## 🚀 Tecnologías usadas

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Thymeleaf
- HTML + CSS
- Bootstrap (opcional)
- PostgreSQL
- Maven

📂 Estructura del proyecto
arduino
Copiar
Editar
├── entidad/
│   ├── Usuario.java
│   ├── Rol.java
│   ├── Producto.java
│   └── ...
├── controlador/
│   ├── RegistroUsuarioControlador.java
│   └── ...
├── repositorio/
│   └── UsuarioRepository.java
├── servicio/
│   ├── UsuarioServicio.java
│   └── UsuarioServicioImpl.java
├── config/
│   └── SecurityConfiguration.java
└── templates/
    ├── login.html
    ├── registro.html
    └── usuarios.html
---

## 🚀 Cómo ejecutar

1. Clona el repositorio:

```bash
git clone git remote add origin https://github.com/ChristianHuarcaya/Sistema-inventario-productos-springboot.git


## 👨‍💻 Autor

**Cristian Huarcaya Pumahualcca**  
Desarrollador Backend en Java  
[LinkedIn](https://www.linkedin.com/in/christian-huarcaya-pumahualcca) | [GitHub](https://github.com/ChristianHuarcaya)







