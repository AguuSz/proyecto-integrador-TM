# Proyecto integrador TM

![](https://raw.githubusercontent.com/AguuSz/proyecto-integrador-TM/a5802022f4ef077370cda1f9262a45e971679097/app/src/debug/res/mipmap-hdpi/ic_launcher_foreground.png)

Este es un proyecto realizado para la materia de Tecnologias moviles del **IUA** (Instituto Universitario Aeronautico). Dicha app consiste en un E-Commerce de toda la vida incluyendo los temas vistos a lo largo de la cursada. Algunos de los temas vistos son:

- Networking
- Uso de base de datos mediante ROOM
- Corutinas
- Uso de fragments
- Notificaciones
- Autenticacion biometrica, en este caso por huella dactilar

## Consideraciones

Algunas cosas a considerar son:

- La base de datos se popula cada vez que se realiza el login, forzando a tener conexion y es por eso que no deja entrar si no tenemos conexion a internet.
- Al no tener internet, se muestra un Dialog en LoginActivity, el cual no nos deja avanzar hasta que hayamos conectado ya sea el WiFi o el 4G.
- La parte de backend en un proyecto real deberia estar externo, teniendo solamente en la app que pegarle a los distintos endpoints. Se hizo asi por especificacion de la materia.
- Cuando cambiamos la imagen de perfil, esta no se sube a ningun repositorio, sino que queda guardada en el celular. Si intentamos instalar la app en otro dispositivo veremos que la foto sigue sin cambiar practicamente.

## Librerias utilizadas

- ![Glide](https://github.com/bumptech/glide)
- ![ImagePicker](https://github.com/Dhaval2404/ImagePicker)
- ![Retrofit](https://github.com/square/retrofit)
- ![Oops! No Internet!](https://github.com/ImaginativeShohag/Oops-No-Internet)
- ![Biometric-Authentication-Android](https://github.com/BharathVishal/Biometric-Authentication-Android)

## Requerimientos
- Android 8.0 (Oreo)

## Demo
![Link de youtube](https://youtu.be/XfHxxqk8BhY)

## Herramientas extras utilizadas

### Trello
![Trello](https://user-images.githubusercontent.com/63722332/198418062-d6e42769-61f9-4515-b3de-ec8c0d852cb9.png)

### Excalidraw
![Excalidraw](https://user-images.githubusercontent.com/63722332/198418113-17e8a44b-8e94-4f03-bb37-bce4e394c591.png)

## Integrantes:
- ![Agustín Sepúlveda](https://github.com/AguuSz)
- ![Agustín Sangüesa](https://github.com/agussanguesa32)
