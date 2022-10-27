#   Icomment Spring Rest Api

La idea es generar una api, con la cual se pueda recuperar imagenes , guardar imagenes, guardar comentarios asociados a esas imagenes, me falta la parte de las imagenes, y el frontend, el cual me gustaria hacerlo en react.

Tecnologias usadas
- java
- Spring boot
- Jpa
- MySql
- Lombok
- jwt
- jackson-databind

Que puede hacer?

Como usuario podemos:
 - Crear un usuario.
 - Intentar autenticarme 
    - caso exitoso : el backend enviara 2 tokens, accessToken y refeshToken.
    - caso no exitoso : el backend enviara un mensaje de error
 - Deslogearte : al deslogearte se debera enviar al backend el accessToken y refreshToken , el backend las guardara en una tabla de la BDD y las marcara como invalidas, si alguien intenta hacer una peticion con esas tokens, no podra.
 - Crear comentarios
 - Borrar comentarios
 - Guardar imagenes (aun no implementado)

Como admin podemos:
   - Todo lo que puede hacer el usuario normal
   - Listar todos los usuarios


## AccessToken y RefreshToken
Ambos tokens tendran tiempo de vida, cuando el accessToken venza , se podra enviar el refreshToken al backend, si es valido el backend devolvera una nuevo accessToken junto con el refeshToken.

Como enviar el token de acceso para cada request y para el refreshToken?

   - Como un header:
      - key = Authorization
      - value = Bearer+espacio+token
   ![applicationProperties](/readmeFiles/sendToken.png) 


Para el logout es distinto, se debe enviar ambos tokens en el cuerpo de la peticion
![applicationProperties](/readmeFiles/logout.png) 

## Como arrancar la api?

- Primero importar los archivos SQL para tener el Schema icomment completo , estos se encuentran en la carpeta sql.
- Se debera completar los dataSource en el application.properties.
   ![applicationProperties](/readmeFiles/applicationProperties.png) 
   - En mi caso estoy usando un env, sino usas ninguno, recomiendo borrar del pom.xml esta parte del codigo->
      ![applicationProperties](/readmeFiles/filterPom.png) 
- Descargar todas las dependencias del pom.xml.