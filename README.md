# StockManager — Inventario Offline

Aplicacion movil (Android / Kotlin) que registra entradas y salidas de mercancia
en almacenes SIN conexion, usando una base de datos local con Room (SQLite).
Cuando el dispositivo recupera la conexion, los movimientos pendientes se
sincronizan con el servidor central.

## Persistencia
- **Room sobre SQLite** (base de datos local en el dispositivo).

## Estructura
- `data/Producto.kt`, `data/MovimientoInventario.kt` — entidades (modelo de datos).
- `data/ProductoDao.kt`, `data/MovimientoDao.kt` — DAO con operaciones CRUD.
- `data/StockDatabase.kt` — base de datos Room.
- `repository/StockRepository.kt` — logica de negocio y sincronizacion.
- `ui/InventarioViewModel.kt` — conexion con la interfaz.

## Operaciones CRUD
Insertar (Create), consultar (Read), actualizar (Update) y eliminar (Delete),
tanto para productos como para movimientos.
