# EY
Ey technical exercise

v1:
- Todos los endpoints aceptan y retornan solamente Json, inclusive para los mensajes de error.
- Endpoints:
   - local/users (agrega un usuario) (post)
   - local/users (obtiene lista de usuarios) (get)
   - local/user/{id} (obtiene un usuario) (get)
   - local/user/{id} (actualiza un usuario) (put)
   - local/user/{id} (elimina un usuario) (delete)
- Endpoint local/users (agrega un usuario) (post) recibe un usuario con los campos "nombre", "correo", "contraseña" más un listado de objetos "teléfono".
- Responden al código de status HTTP adecuado
- En caso que el correo conste en la base de datos, devuelve "El correo ya está registrado".
- El correo sigue una expresión regular para validar su formato (aaaaa@dominio.cl)
- La clave sigue una expresión regular para validar su formato (Una mayúscula, letras minúsculas y dos números)-

Pendiente (no logrado)
- El token debe persistirse junto con el usuario.
- JWT como tóken
- Pruebas de unidad
- Diagrama de la solución
