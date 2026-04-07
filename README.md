# Prácticas de Programación Orientada a Objetos (Java)

Este repositorio contiene dos prácticas desarrolladas en Java aplicando principios de Programación Orientada a Objetos, Clean Code y SOLID.

---

## 📌 Práctica 1: Gestor de Productos

### Descripción

Aplicación por consola que modela distintos tipos de productos, aplicando herencia, interfaces y polimorfismo.

---

### 🧩 Estructura del modelo

#### Clase base

* `Producto`

    * Atributos comunes (marca, precio)
    * Métodos compartidos

#### Interfaces

* `Alimento`

    * Define comportamiento relacionado con calorías
* `Descuento`

    * Define cálculo de descuentos
* `Liquido`

    * Define propiedades de volumen y envase

#### Clases concretas

* `Cereales` → implementa `Alimento`
* `Vino` → implementa `Alimento` y `Liquido`
* `Detergente` → implementa `Descuento` y `Liquido`

---

### ⚙️ Funcionalidad

* Creación de distintos tipos de productos
* Uso de interfaces para comportamiento común
* Ejecución desde consola mediante `main`

---

### 🧠 Ejemplo de polimorfismo

Se utiliza una colección de alimentos para calcular calorías totales:

👉 Se trata a todos los objetos como `Alimento`, independientemente de su tipo concreto.

---

### 🧱 Conceptos aplicados

* Herencia
* Interfaces
* Polimorfismo
* Encapsulación

---

## 🚗 Práctica 2: Sistema de Gestión de Parking

### Descripción

Aplicación con interfaz gráfica (Swing) para gestionar un aparcamiento de pago con distintos tipos de vehículos.

---

### 🚘 Tipos de vehículos

* **Oficial**

    * No paga
    * Se registran estancias

* **Residente**

    * Acumula tiempo
    * Paga al final de mes (0.002 €/min)

* **No residente**

    * Paga al salir (0.02 €/min)

---

### ⚙️ Funcionalidades

* Registrar entrada de vehículo
* Registrar salida de vehículo
* Alta de vehículos oficiales y residentes
* Cálculo automático de importes
* Reinicio mensual:

    * Borra estancias de oficiales
    * Resetea acumulado de residentes
* Generación de informe de residentes en fichero
* Visualización de vehículos
* Interfaz gráfica con Swing

---

### 🧩 Arquitectura

Separación clara por capas:

* **Dominio**

    * `Vehiculo` (abstracta)
    * Subclases específicas
    * `Estancia`, `ResultadoSalida`

* **Repositorio**

    * Gestión en memoria

* **Servicio**

    * `ServicioParking` (lógica de negocio)

* **Infraestructura**

    * Generación de informes
    * Proveedor de tiempo

* **Interfaz gráfica**

    * `VentanaParking`

---

### 🧠 Diseño aplicado

* Principios **SOLID**
* Clean Code
* Separación UI / lógica

---

### 📄 Informe de residentes

Formato alineado en columnas:

```
Matrícula    Tiempo estacionado (min.)    Cantidad a pagar
12345ABC      20134                        40.27
67890DEF    4896                         9.79
```

---

## 🛠️ Compilación y ejecución

### Ejecutar

```powershell
java -cp bin aparcamiento.aplicacion.AplicacionParking
```

---

## 📚 Conclusión

Estas prácticas demuestran:

* Modelado correcto con POO
* Uso real de interfaces y polimorfismo
* Separación de responsabilidades
* Diseño extensible y mantenible

---

## 🔧 Posibles mejoras

* Persistencia en base de datos (Uso de MySQL para tener datos de manera persistente)
* Tests unitarios (JUnit)
* Validación avanzada de datos (Utils con validaciones de datos para importar)
* Mejora de la interfaz gráfica - (Actualmente es muy básica por poca experiencia con JFrame)

---
