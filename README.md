# Infraestructura de software: Sensorización de la zona del estanque del Jardín Botánico de Barcelona
## Hermes IoT
Proyecto de sensorización IoT para la asignatura IoTm Internet de las Cosas del CFGS de DAM
## Uso
El proyecto está dividido en dos directorios principales:
1. docker-iot-api-server --> Contiene todos los archivos necesarios para simular el servidor de backend del proyecto.
2. docker-iot-broker --> Contiene los servicios de broker MQTT y la simulación de flujo de métricas de sensores.
3. hermes-iot-app --> Contiene el código fuente de la aplicación móvil Android, puede ejecutarse a través del emulador Android de IntelliJ Idea o Android Studio.

## Montaje del contenedor Docker
Acceder al directorio /docker-iot-api-server y ejecutar --> docker compose up
Acceder al directorio /docker-iot-broker y ejecutr --> docker compose up
Es muy importante seguir este orden ya que los servicios de broker necesitan que este arriba el backend para ejecutarse corractamente.

Para probar la app cliente, hay que ejecutarla en un emulador de Android.

## Equipo
Iker López Iribas [![Gmail](https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white)](ilopezir@uoc.edu) </br>
Damià Belles Sampera [![Gmail](https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white)](dbelles@uoc.edu) </br>
Daniel Boj Cobos [![Gmail](https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white)](dboj@uoc.edu) </br>              
