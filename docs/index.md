# 📚 Documentación Técnica: La Payoya Vending

Bienvenido al centro de documentación del proyecto. Aquí encontrarás todos los detalles sobre el funcionamiento, diseño y validación del sistema.

---

## 📋 Índice de Contenidos
1. [Manual de Usuario](#usuario)
2. [Manual de Instalación](#instalacion)
3. [Documentación de Diseño (Arquitectura)](#arquitectura)
4. [Casos de Prueba y Complejidad](#pruebas)

---

<a name="usuario"></a>
## 🥛 1. Manual de Usuario
> **Guía operativa para el cliente final y el personal de mantenimiento.**

> ### 🥛 Manual de Operación: Expendedora "La Payoya"
> Bienvenido al manual de operación de la expendedora de productos artesanales **La Payoya**. Siga estas instrucciones para una experiencia de compra satisfactoria.

---

### 1. Interfaz de Bienvenida
Al iniciar la máquina, el terminal mostrará el catálogo actual de productos disponibles.

> **Nota:** Si un producto aparece con la etiqueta `[AGOTADO]`, el sistema bloqueará su selección automáticamente para evitar transacciones fallidas.

---

### 2. Proceso de Compra Paso a Paso

1.  **Selección de Producto:** Introduzca el número correspondiente en el teclado numérico de la máquina.
2.  **Configuración de Formato (Líquidos):**
    * Si elige **Leche** o **Nata**, el sistema desplegará las opciones de volumen disponibles.
    * Seleccione el formato deseado. El precio en pantalla se actualizará instantáneamente aplicando las tarifas vigentes. 
    * *Ejemplo: +1.50€ por el formato de 500ml de Nata.*
3.  **Gestión de Pago:**
    * **Efectivo:** La máquina acepta monedas y billetes pequeños. Tras cada inserción, verá el mensaje: `Saldo restante: X.XX€`.
    * **Tarjeta:** Simule la transacción siguiendo las instrucciones en pantalla. El sistema validará la conectividad con el servidor bancario.
4.  **Dispensado:** Una vez alcanzado el importe, escuchará el aviso del dispensador. Retire el producto por la trampilla inferior.

---

### 3. Gestión de Devoluciones y Cancelación

* **🔴 Botón Cancelar:** Si decide no finalizar la compra tras haber introducido dinero, el sistema le reintegrará el total acumulado y volverá al menú inicial.
* **🪙 Cálculo de Cambio:** El monedero interno calculará la combinación óptima de monedas para devolverle el sobrante exacto tras una compra con efectivo.

---

### 4. Mensajes del Sistema y Alertas

| Mensaje | Significado | Acción |
| :--- | :--- | :--- |
| **"Nivel Crítico"** | Aviso para el personal técnico. | Puede seguir comprando; hay stock para su pedido. |
| **"Saldo Insuficiente"** | No se ha completado el pago total. | Introduzca más saldo para dispensar el producto. |
| **"Error de Selección"** | Código introducido no válido. | Revise el catálogo e introduzca el número correcto. |

---

### 5. Preguntas Frecuentes (FAQ)

**¿Qué pasa si introduzco un billete de 20€?** La máquina está configurada para pagos pequeños; se recomienda usar monedas, billetes de 5€ o tarjeta para evitar errores de cambio insuficiente en el depósito.

**¿Puedo comprar dos productos a la vez?** No. Por seguridad y gestión de stock, debe finalizar una transacción y recoger su producto antes de comenzar la siguiente.

---

<a name="instalacion"></a>
## 🛠️ 2. Manual de Instalación
> **Guía técnica para el despliegue del sistema en entornos de desarrollo.**

> ### ⚙️ Manual de Instalación y Configuración (Técnico)
> Este manual detalla los pasos necesarios para desplegar el entorno de ejecución y desarrollo del sistema **La Payoya Vending**.

---

### 1. Requisitos del Sistema
Para asegurar la compatibilidad de las librerías y la sintaxis de **Java 17+**, el sistema anfitrión debe cumplir:

* **Sistema Operativo:** Windows 10/11, macOS o Linux.
* **Java Development Kit (JDK):** Versión 17 (LTS) o superior.
* **Memoria RAM:** Mínimo 4GB.
* **Espacio en disco:** 100MB (incluyendo el JDK).

---

### 2. Configuración de Variables de Entorno
Es crítico que el compilador sea accesible desde cualquier terminal para evitar errores de ruta:

1.  **Localizar JDK:** Busca la ruta de instalación (ej. `C:\Program Files\Java\jdk-17`).
2.  **Configurar JAVA_HOME:** Crea la variable de sistema `JAVA_HOME` apuntando a esa ruta.
3.  **Actualizar Path:** Edita la variable `Path` y añade `%JAVA_HOME%\bin`.

> [!TIP]
> **Verificación:** Ejecuta `javac -version` en la terminal. Si el sistema responde con el número de versión, la configuración es correcta.

---

### 3. Despliegue del Código Fuente
Descarga el repositorio o descomprime el archivo del proyecto en una ruta sin espacios (ej. `C:\Proyectos\LaPayoyaVending`).

**Estructura de directorios esperada:**
* `/src`: Código fuente organizado por paquetes.
* `/bin`: Directorio de salida para los archivos binarios (`.class`).
* `/doc`: Documentación Javadoc generada automáticamente.

---

### 4. Ciclo de Compilación y Limpieza
Para evitar errores de dependencias entre paquetes, se recomienda realizar una limpieza y compilación total desde la raíz del proyecto:

**Limpiar binarios antiguos (Windows):**
```bash
del /s /q bin\*
javac -d bin src/excepciones/Notificador.java src/model/*.java src/main/LaPayoyaVending.java
```

---

### 5. Resolución de Problemas (Troubleshooting)

| Problema | Causa Probable | Solución |
| :--- | :--- | :--- |
| **Error: Could not find or load main class** | Classpath incorrecto. | Ejecutar con: `java -cp bin main.LaPayoyaVending`. |

---
<a name="arquitectura"></a>
## 🏗️ 3. Documentación de Diseño (Arquitectura)
> **Análisis detallado de la estructura de clases, herencia y lógica de componentes del sistema.**

### 3.1 Diagrama de Clases (UML)
[cite_start]A continuación se presenta la estructura visual de las clases y sus relaciones fundamentales, destacando la jerarquía de productos y el desacoplamiento del sistema de pagos[cite: 49, 55]:

![Diagrama UML La Payoya Vending](data:image/jpeg;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNkYAAAAAYAAjCB0C8AAAAASUVORK5CYII=)
[cite_start]*(Nota: El diagrama ilustra cómo la clase abstracta Producto centraliza los atributos comunes para sus subclases [cite: 50, 77])*

### 3.2 Organización del Proyecto (Paquetes)
[cite_start]El sistema se organiza en tres paquetes lógicos para garantizar la separación de responsabilidades[cite: 61]:

* [cite_start]**`model`**: Contiene el núcleo de la lógica de negocio, incluyendo la clase abstracta `Producto`, las subclases (`Queso`, `Leche`, `Nata`) y la gestión de pagos en `Monedero`[cite: 74].
* [cite_start]**`excepciones`**: Define el contrato de comunicación mediante la interfaz `Notificador` y gestiona las excepciones del sistema[cite: 63, 73].
* [cite_start]**`main`**: Orquestador del flujo de ejecución que contiene la clase principal `LaPayoyaVending`[cite: 72, 75].

### 3.3 Diseño de Clases y Herencia
[cite_start]Se aplica una jerarquía basada en la clase abstracta **`Producto`**, que obliga a cada especialización a definir su propia lógica de inventario[cite: 50, 77].

#### **Detalle de Componentes Principales**

| Clase | Modificador | Responsabilidad Clave |
| :--- | :--- | :--- |
| **`Producto`** | `abstract` | [cite_start]Define atributos protegidos (`nombre`, `precio`, `notificador`) y los métodos `hayStock()` y `dispensar()`[cite: 85, 86]. |
| **`Queso`** | `public` | [cite_start]Gestiona el inventario por unidades discretas (cuñas de 250g)[cite: 21, 88]. |
| **`Leche`** | `public` | [cite_start]Controla el stock en ml y ajusta el precio según el formato (500ml o 1L)[cite: 90, 91]. |
| **`Nata`** | `public` | [cite_start]Controla el stock en ml para formatos de 100ml, 250ml y 500ml[cite: 93]. |
| **`Monedero`** | `public` | [cite_start]Centraliza la lógica financiera: valida efectivo (0.50€ a 5€), simula tarjetas y calcula cambios[cite: 42, 95]. |

### 3.4 Patrones y Lógica de Interacción
* [cite_start]**Patrón Callback / Inyección de Dependencias**: Los productos reciben una referencia a la interfaz **`Notificador`** en su constructor, desacoplando el mecanismo de aviso del modelo de negocio[cite: 79, 80].
* [cite_start]**Uso de Expresiones Lambda**: En la clase `LaPayoyaVending`, la implementación de `Notificador` se resuelve mediante una lambda para simplificar el envío de mensajes a la consola[cite: 31, 81].
* **Gestión de Stock Crítico**:
    * [cite_start]**Productos Líquidos (Leche/Nata)**: El sistema dispara una alerta cuando el volumen es inferior a **2 litros**[cite: 91, 93].
    * [cite_start]**Productos Sólidos (Queso)**: La notificación se emite cuando el stock se agota por completo (**0 unidades**)[cite: 88].

### 3.5 Interfaz de Consola (CLI)
[cite_start]La navegación utiliza un bucle interactivo que presenta avisos de stock bajo mediante el prefijo `[!]` antes de permitir la selección[cite: 40, 120].

| Prefijo de Mensaje | Significado |
| :--- | :--- |
| `[ALERTA SISTEMA]` | [cite_start]Notificación de stock crítico enviada al supervisor[cite: 120]. |
| `[MONEDERO]` | [cite_start]Información sobre validación de monedas y saldo acumulado[cite: 120]. |
| `[DISPENSADOR]` | [cite_start]Confirmación de que el producto ha sido entregado correctamente[cite: 120]. |

<a name="pruebas"></a>
## 📊 4. Casos de Prueba y Complejidad
> **Validación del software y análisis de la lógica de control.**

> ### 📊 Casos de Prueba y Análisis de Complejidad
> **Validación del software y análisis de la lógica de control mediante la métrica de McCabe.**

---

### 1. Modelado del Proceso (Casos de Uso)
El sistema gestiona las interacciones entre el Cliente, el Administrador y los componentes internos (Monedero/Notificador).

| ID | Caso de Uso | Actor | Flujo Principal | Resultado (Postcondición) |
| :--- | :--- | :--- | :--- | :--- |
| **CU-01** | Consultar Catálogo | Cliente | Muestra productos, precios y stock. | Usuario informado de opciones. |
| **CU-02** | Seleccionar Producto | Cliente | Introduce código y volumen. | Producto bloqueado y precio mostrado. |
| **CU-03** | Pagar con Tarjeta | Cliente | Confirmación vía simulación bancaria. | Transacción autorizada y cobrada. |
| **CU-04** | Pagar con Efectivo | Cliente | Inserción progresiva de monedas. | Saldo acumulado $\geq$ Precio. |
| **CU-05** | Dispensar Producto | Sistema | Descuento de stock y entrega física. | Stock actualizado. |
| **CU-08** | Cancelar Compra | Cliente | Pulsar cancelar antes de finalizar. | Dinero devuelto y sistema reseteado. |

---

### 2. Análisis de Complejidad Ciclomática
Para garantizar la robustez del código, se ha calculado la métrica de **McCabe** analizando los nodos de decisión del software.

#### **Cálculo Matemático:**
$$M = P + 1$$
Donde **P** son los puntos de decisión detectados:
1. **P1 (Menú):** Elección entre 3 tipos de productos.
2. **P2 (Stock):** ¿Hay existencias disponibles?
3. **P3 (Tamaño Leche):** Selección de formato.
4. **P4 (Tamaño Nata):** Selección de formato.
5. **P5 (Tipo Pago):** Tarjeta o Efectivo.
6. **P6 (Bucle Efectivo):** Condición de saldo suficiente.
7. **P7 (Cancelar):** Interrupción voluntaria del flujo.
8. **P8 (Cambio):** ¿El saldo es superior al precio?

**Resultado:** $$M = 8 + 1 = 9$$
* **Interpretación:** Riesgo Bajo (1-10). El código es altamente mantenible y modular.

---

### 3. Tabla de Casos de Prueba (QA)
Siguiendo la métrica anterior, se han diseñado **9 casos de prueba** para cubrir el 100% de los caminos lógicos.

| ID Prueba | CU Rel. | Acción / Entrada | Resultado Esperado | ¿Pasa? |
| :--- | :--- | :--- | :--- | :---: |
| **TP-01** | CU-02 | Elegir Leche (500ml) | Precio mostrado: 1.00€ | ✅ |
| **TP-03** | CU-06 | Finalizar (1.50€ - 1.00€) | Devolución de 0.50€ | ✅ |
| **TP-05** | CU-07 | Comprar Nata hasta < 2L | Alerta: "NIVEL CRITICO" | ✅ |
| **TP-06** | CU-08 | Meter 0.50€ y Cancelar | Saldo reseteado a 0.00€ | ✅ |
| **TP-08** | CU-02 | Elegir producto sin stock | Error: "Producto agotado" | ✅ |
| **TP-09** | CU-09 | Admin pulsa "Reset Stock" | Niveles vuelven al 100% | ✅ |

---

### 🔙 Enlaces rápidos
* [Volver a la página principal](../README.md)
* [Ver código fuente en GitHub](https://github.com/eduardopuyo/PayoyaVending)

---
© 2026 - Proyecto La Payoya Vending
