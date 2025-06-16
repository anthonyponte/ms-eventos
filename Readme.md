# ms-eventos
Microservicio de eventos para el proyecto final del curso de Docker y Kubernetes Intermedio

## Requerimientos
- Oracle Database XE

## Instalacion
Ejecutar los siguientes comandos para desplegar Oracle Database XE:
```
kubectl apply -f oracle-db-configmap.yml
kubectl apply -f oracle-db-secret.yml
kubectl apply -f oracle-db-pv.yml
kubectl apply -f oracle-db-pvc.yml
kubectl apply -f oracle-db-deployment.yml
kubectl apply -f oracle-db-service.yml
```
Ejecutar los siguientes comandos para desplegar el microservicio:
```
kubectl apply -f ms-eventos-configmap.yml
kubectl apply -f ms-eventos-deployment.yml
kubectl apply -f ms-eventos-service.yml
```
Ejecutar los siguientes comandos para la creación de los roles:
```
kubectl apply -f cluster-role.yml
kubectl apply -f cluster-role-binding.yml
```

## Uso
| Metodo | Url                  | Descripción                        |
|--------|----------------------|------------------------------------|
| GET    | /api/v1/eventos      | Listar todos los eventos           |
| GET    | /api/v1/eventos/{id} | Obtener evento por ```id```        |
| POST   | /api/v1/eventos      | Crear evento                       |
| PUT    | /api/v1/eventos/{id} | Actualizar evento por ```id```     |
| DELETE | /api/v1/eventos/{id} | Eliminar participante por ```id``` |
