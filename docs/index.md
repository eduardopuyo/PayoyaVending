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

> [!IMPORTANT]
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
> **Análisis de la estructura de clases, herencia y patrones de diseño utilizados.**

> ### 🏗️ Documentación de Diseño y Arquitectura (Técnico)
> **Proyecto:** Sistema de Gestión de Vending "La Payoya"  
> **Arquitectura:** Orientada a Objetos (POO) con Desacoplamiento de Servicios.

---

### 1. Organización del Código (Paquetes)
El sistema se ha dividido en tres paquetes lógicos para cumplir con el principio de **Separación de Responsabilidades**:

* **`model`**: Contiene el núcleo de la lógica de negocio y las entidades físicas (Productos, Monedero).
* **`excepciones`**: Define el contrato de comunicación y alertas mediante interfaces, asegurando que el sistema sea extensible.
* **`main`**: Orquestador del flujo de ejecución y gestión de la interfaz de consola.

---

### 2. Jerarquía de Clases y Polimorfismo
Se ha aplicado un diseño basado en la **Herencia** para maximizar la reutilización de código:

#### **Clase Base: Producto (Abstracta)**
Funciona como la plantilla maestra. Al ser `abstract`, impide que se creen "productos genéricos", obligando a definir comportamientos específicos.
* **Atributos Protegidos (`protected`):** Permiten que las clases hijas accedan al precio y al notificador sin romper el encapsulamiento hacia el exterior.
* **Métodos Abstractos:** `hayStock()` y `dispensar()`. Cada producto "sabe" cómo se descuenta su inventario (por mililitros o por unidades).

#### **Especializaciones:**
* **Leche y Nata:** Implementan una lógica de precios basada en **Atributos de Volumen**. El método `getPrecio(int ml)` actúa como un motor de reglas que varía el coste base según el formato seleccionado.
* **Queso:** Simplifica la gestión al trabajar con unidades discretas, ideal para productos pre-empaquetados.



---

### 3. Patrones de Diseño Aplicados

#### **A. Patrón Estrategia (Strategy) / Inyección de Dependencias**
A través de la interfaz **`Notificador`**, el sistema no depende de una implementación de alertas específica.
* **Beneficio:** Si en el futuro se desea enviar un SMS o un correo en lugar de un mensaje por consola, solo hay que crear una nueva clase que implemente la interfaz, sin tocar el código de los productos.

#### **B. Encapsulamiento Financiero (Monedero)**
La clase `Monedero` centraliza la seguridad del dinero:
* **Estado Interno:** Gestiona de forma privada el `saldoAcumulado` y el `depositoInterno`.
* **Lógica de Cambio:** Utiliza un algoritmo de resta sucesiva para calcular la devolución, protegiendo al sistema de entregar cambio si no hay fondos suficientes.

---

### 4. Flujo de una Transacción
El ciclo de vida sigue esta secuencia lógica:
1.  **Entrada:** El controlador (`main`) captura la intención del usuario.
2.  **Validación:** Se consulta al objeto `Producto` si su estado permite la venta.
3.  **Cálculo:** El `Producto` devuelve el precio final al controlador.
4.  **Cobro:** El controlador delega en el `Monedero` la validación del pago.
5.  **Ejecución:** Si el pago es exitoso, se ordena la dispensa y el `Notificador` emite alertas si el stock es bajo.

---

### 5. Decisiones de Diseño (Justificación)

| Decisión | Justificación Técnica |
| :--- | :--- |
| **Interfaz Notificador** | Cumplir el principio **Open/Closed** de SOLID (abierto a extensión, cerrado a modificación). |
| **Uso de `int` para mililitros** | Evitar errores de precisión de punto flotante (`float`/`double`) en inventarios críticos. |
| **System.out centralizado** | Mantener la lógica de cálculo "pura" para facilitar futuros **Tests Unitarios**. |

---

### 📖 Documentación Técnica (Javadoc)
La documentación completa de cada método (parámetros, retornos y excepciones) se encuentra disponible en formato HTML dentro de la carpeta `/doc`. Consulte el archivo `index.html` para detalles de la API interna.

---

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
