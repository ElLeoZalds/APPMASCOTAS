# AppMascotas

Aplicación Android para gestionar mascotas, con funcionalidades para buscar, listar y registrar mascotas.

## Estructura del Proyecto

- **manifests**  
  Contiene el archivo `AndroidManifest.xml`.

- **java**  
  Código fuente dividido en paquetes:
    - `com.example.appmascotas`  
      Contiene las clases principales:
        - `MainActivity`
        - `Buscar`
        - `Listar`
        - `Registrar`

- **res**  
  Recursos de la aplicación:
    - **drawable**: Recursos gráficos.
    - **layout**: Archivos XML para las interfaces de usuario:
        - `activity_buscar.xml`
        - `activity_listar.xml`
        - `activity_main.xml`
        - `activity_registrar.xml`
    - **mipmap**: Iconos de la app.
    - **values**: Valores como strings, colores y estilos.
    - **xml**: Otros archivos de configuración XML.

- **Gradle Scripts**  
  Archivos de configuración para la construcción del proyecto.

## Funcionalidades

- Buscar mascotas
- Listar mascotas
- Registrar nuevas mascotas

## Cómo ejecutar

1. Importar el proyecto en Android Studio.
2. Construir y ejecutar la app en un emulador o dispositivo físico.

