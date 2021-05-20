# SuCRUD
Frávega IT Software engineer Challenge 1 - Sucursal CRUD










Para este proyecto se utilizó Docker para la persistencia de la BD de MYsql.

- Para levantarlo se utilizan los archivos: docker-compose y Dockerfile, y se colocan en una carpeta diferente a donde tengamos al proyecto de Java

- En esa misma carpeta se coloca la carpeta de schemas para utilizar la misma base de datos con la que se trabajó al crear la API

- Abrimos una terminal y vamos a la carpeta con los archivos docker-compose, Dockerfile y la carpeta shchemas y colocamos el siguiente comando de docker:
  
  'docker-compose up -d'
  
con este comando se levanta el container, la base datos y queda presistente la DB.



Para usar el proyecto se debe importar como MAVEN, ya que está basado en SpringBoot.

Ya que se importa el proyecto, en el IDE seleccionas el proyecto y lo corres como 'Spring Boot App'

La API ahora esta corriendo y escuchando en 'http://localhost:8080/Sucursal'

Para ver como funciona la API utilicé PostMan versión de escritorio, ahí creas un nuevo workspace (el nombre no importa) y en la barra de 'Enter request URL' se coloca:

  'http://localhost:8080/Sucursal'


- API
 
 La API se utiliza para 3 cosas
  - Insertar una nueva Sucursal con su dirección, latitud y longitud.
  - Llamar a Sucursal ya creada por su ID
  - Indicarte cual es la Sucursal mas cercana a la ubicación dada por latitud y longitud

descripción de endpoint:

definitions:
  Paths:
    description: API para Insertar una sucursal, llamar a una sucursal o buscar la sucursal mas cercana.
      basePath:
        /Sucursal:
          get:
            description: returns the closest location of a store
            parameters:
              - name: latitud
                in: query
                type: float
                description: It's the latitude of the current location
              - name: longitude
                in: query
                type: float
                description: It's the longitude of the current location
                
                     
      responses:
              200:
                description: Successfully returned a near location of a bussiness
                  type: string

                        
         400:
                description: Invalid request 
                schema:
                  type: object
                  properties:   
                    message:
                      type: string
                  
         post:
            description: Lets post a new instance of a store in the DB
            parameters:
              - name: direccion
                in: query
                description: the address of the new location
                type: string
                - name: latitud
                description: the latitude of the new location
                type: float
                - name: longitude
                description: the longitude of the new location
                type: float
                      
         responses:
              200:
                description: Successfully returned the new ID of the newly created record
                  type: string

                        
         400:
                description: Invalid request 
                schema:
                  type: object
                  properties:   
                    message:
                      type: string
                description: Unsuccessfully created a new record
                
      path:
      /id:
       get:
            description: returns the id of the newly created location
            parameters:
              - name: id
                in: query
                type: int
                description: It's the latitude of the current location
              - name: longitude
                in: query
                type: float
                description: returns the specified location by ID
                
                     
      responses:
              200:
                description: Successfully returned the locations of the bussiness by ID
                  type: string

                        
         400:
                description: Invalid request 
                schema:
                  type: object
                  properties:   
                    message:
                      type: string
                
                
              400:
                description: Invalid request 
                schema:
                  type: object
                  properties:   
                    message:
                      type: string


  - Ejemplo de llamada a la API en postman
      - Usando la URL: 'http://localhost:8080/Sucursal'
      - se ponen los paramatros de 
        - KEY: latitud
        - VALUE: [Latitud que se va a usar]

        - KEY: longitu
        - VALUE: [Longitud que se va a usar]
        
       - esto transformara la url en: 'http://localhost:8080/Sucursal?latitud={LATITUD}&longitud={LONGITUD}'

       - La respuesta a esto sería: La sucursal mas cerncana se encuentra a {Distancia} KMs, en la Direccion: {Dirección}.

  - para recibir la Sucursal por ID en el Path: '/Sucursal/id' solo se pone una 'KEY id' con el 'Value {id}'
