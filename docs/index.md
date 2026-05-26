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

## 🏗️ 3. Documentación de Diseño (Arquitectura)
> **Análisis de la estructura de clases, herencia y principios de diseño utilizados en el sistema.**

### 🏗️ Documentación de Diseño y Arquitectura (Técnico)
**Proyecto:** Sistema de Gestión de Vending "La Payoya Vending"  
**Arquitectura:** Orientada a Objetos (POO) estructurada para consola (CLI) aplicando principios SOLID.

---

### 1. Organización del Código (Paquetes)
El proyecto se organiza en tres paquetes bien delimitados en el directorio `src/` para garantizar la separación de responsabilidades:

* **`excepciones`**: Alberga la interfaz funcional `Notificador.java` encargada de desacoplar el sistema de alertas críticas. *Nota: Según la estructura del proyecto, actúa como el contenedor de las herramientas de notificación y control de anomalías.*
* **`model`**: Contiene el núcleo del modelo de negocio. Incluye la clase abstracta base `Producto.java`, las tres subclases de productos lácteos (`Queso.java`, `Leche.java`, `Nata.java`) y la clase independiente de gestión económica `Monedero.java`.
* **`main`**: Contiene la clase controladora principal `LaPayoyaVending.java` que unifica los componentes y dirige el flujo de ejecución y la interfaz de usuario.

---

### 2. Jerarquía de Clases y Polimorfismo
El sistema brinda soporte a la distribución de productos lácteos artesanales de la Sierra de Cádiz y Málaga abstrayendo el comportamiento común en una estructura jerárquica:

* **Clase Base: Producto (Abstracta)**
  Centraliza los atributos compartidos por todos los artículos y define el contrato obligatorio de comportamiento mediante encapsulamiento protegido (`protected`):
  * **Atributos:** `nombre` (String), `precio` base (double) y la referencia de inyección de dependencias `notificador` (Notificador).
  * **Contrato Polimórfico:** Define los métodos abstractos públicos `hayStock(int)` y `dispensar(int)`. Cada subclase computa de forma interna su inventario según su unidad de medida.

* **Especializaciones del Catálogo:**
  * **`Queso`**: Gestiona stock físico de forma discreta mediante un conteo de unidades enteras (`unidades` de cuñas de 250 g). Lanza avisos al supervisor en el momento exacto en que las existencias llegan a 0.
  * **`Leche` y `Nata`**: Representan productos líquidos dispensados por volumen. Utilizan variables enteras en mililitros (`tanqueLecheml` y `tanqueNataml`) para monitorizar los depósitos y evitar pérdidas de precisión numéricas. Ambas implementan el método dinámico `getPrecio(int)` para tarifar según el volumen seleccionado y emiten alertas críticas en cuanto sus respectivos tanques bajan de los 2 Litros (2000 ml).
    * **Formatos de Leche:** 500 ml o 1 L.
    * **Formatos de Nata:** 100 ml, 250 ml o 500 ml.

---

### 3. Principios y Patrones de Diseño Aplicados

#### **A. Patrón Callback mediante Interfaces Funcionales (SOLID - Desacoplamiento)**
En lugar de acoplar de forma rígida los productos al canal de salida, el sistema implementa la interfaz funcional **`Notificador`** con su único método abstracto `notificar(String)`. 
* **Inyección en Constructor:** Cada subclase de `Producto` recibe la referencia del notificador en su construcción y la invoca ante un escenario de stock crítico. 
* **Resolución Lambda:** La clase principal `LaPayoyaVending` resuelve esta interfaz mediante una **expresión lambda**, imprimiendo las alertas directamente en la consola sin necesidad de instanciar clases adicionales pesadas. Esto respeta el principio *Open/Closed* de SOLID.

#### **B. Responsabilidad Única Financiera (Monedero)**
La clase **`Monedero`** encapsula la totalidad de la lógica transaccional monetaria de forma aislada, facilitando cualquier cambio técnico futuro en los sistemas de cobro:
* **Control del Efectivo:** El método `pagarConEfectivo(double)` restringe las monedas y billetes aceptados en el bucle de inserción, validando únicamente denominaciones específicas iguales o superiores a 0,50 EUR e inferiores o iguales a 5 EUR.
* **Simulación de Tarjeta:** El método `pagarConTarjeta(double)` emula una pasarela bancaria externa que se encuentra autorizada por defecto en esta revisión de software.
* **Gestión del Cambio:** El método `darCambio(double)` calcula y procesa la devolución de dinero tras un pago con monedas/billetes y realiza el reinicio automático del saldo acumulado (`saldoAcumulado = 0.0`).

---

### 4. Ciclo de Vida y Flujo de una Transacción (Navegación CLI)
La interacción ocurre íntegramente por consola de texto (E/S estándar) de forma secuencial y cíclica en un bucle principal `do-while` controlado por `LaPayoyaVending`:

1. **Menú Principal:** Muestra el listado de opciones e imprime avisos informativos preventivos (`[!]`) en caso de detectar stock bajo en el sistema al iniciar el bloque.
2. **Suboperación:** El usuario selecciona el artículo deseado (`[1] Queso`, `[2] Leche`, `[3] Nata`). Para los productos líquidos, se despliega una subpantalla para elegir el formato de volumen.
3. **Proceso de Compra:** Se solicita el método de pago preferido.
   * Si se elige **Tarjeta (`[T]`)**, se invoca `pagarConTarjeta(double)`.
   * Si se elige **Efectivo (`[E]`)**, se abre el bucle interactivo de inserción de monedas y billetes.
4. **Confirmación y Dispensado:** Tras validar el pago completo, el controlador ejecuta la reducción física de existencias, dispara el `Notificador` si se alcanzan los umbrales críticos de aviso al supervisor y ordena al `Monedero` despachar el cambio final.

#### **Prefijos de Mensaje del Sistema**
El software estandariza las salidas de texto mediante prefijos específicos para simplificar la auditoría técnica:
* `[!]`: Aviso preventivo de stock bajo en el menú principal.
* `[ALERTA SISTEMA]`: Notificación de stock crítico enviada de forma directa al supervisor.
* `[MONEDERO]`: Validación de monedas ingresadas,
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
