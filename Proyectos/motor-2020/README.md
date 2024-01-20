## Motor 2020
### Desarrollar una aplicación en Eclipse con Java 8 e interfaz gráfica (Swing).
Diseñar y programar un sistema software de mantenimiento de una base de datos de vehículos a motor.

### Librerías utilizadas:
- jxl-2.6.jar.
- mysql-connector-java-5.1.38-bin.jar.
- swing.

### Requisitos del sistema
- El modelo de datos está formado por las tablas “marcas”, “modelos” y “eficiencias”.
- La tabla “marcas” contiene la relación de marcas (identificador y nombre).
- La tabla “modelos” contiene la relación de modelos (identificador, identificador de la marca -referencia a la tabla “marcas”-, nombre del modelo, consumo -expresadas en l/100km-, emisiones -expresadas en gCO2/km- y el identificador de la clasificación energética -referencia a la tabla “eficiencias”-.
- La tabla “eficiencias” contiene los tipos de clasificación energética disponibles. (identificador, descripción y nombre del fichero gráfico con la imagen correspondiente a la clasificación energética).
- El sistema software debe proporcionar la siguiente funcionalidad:
  - Mantenimiento integral (creación, modificación y borrado) de la entidad Modelo.
  - Sistema de consulta de modelos:
    - Ha de incluir los siguientes filtros exclusivos (solo se puede filtrar por uno de los filtros):
      - Marca (mostrará todos los modelos de dicha marca).
      - Consumo máximo (mostrará los consumos menores o iguales).
      - Emisiones máximas (mostrará las emisiones menores o iguales).
      - Clasificación energética (mostrará todos los modelos con dicha clasificación energética.
    - Ha de mostrar en una tabla los siguientes datos:
      - Nombre del modelo.
      - Consumo.
      - Emisiones.
      - Imagen de la clasificación energética.
    - Se podrán exportar los datos de la tabla anterior a los siguientes formatos:
      - Formato xml.
      - Formato sql.
      - Formato xls.

### Requisitos técnicos
1. La aplicación únicamente contendrá un JFrame.
2. La aplicación debe utilizar el layout CardLayout como mecanismo de navegación entre pantallas.
3. La aplicación debe utilizar el componente JMenuBar.
4. La aplicación debe utilizar el componente JToolBar.
5. Cada acción realizada por el usuario sobre la base de datos (tanto si tiene éxito como si no) deberá tener una respuesta del sistema a través de un diálogo.
6. La selección del consumo en el sistema de consultas deberá realizarse con un deslizador (JSlider).
