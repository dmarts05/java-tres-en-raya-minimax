#!/bin/bash

echo "[SCRIPT] Bienvenido al 3 en raya con IA Minimax, elige una opción:"
echo "    1. Jugar al 3 en raya."
echo "    2. Ejecutar laboratorio de pruebas de rendimiento."
echo "    3. Salir."

read option

case $option in
    1)
        # Ejecuta el fichero jar del juego
        java -jar tres-en-raya.jar
    ;;
    
    2)
        # Ejecuta el fichero jar del laboratorio
        java -jar laboratorio-de-rendimiento.jar
    ;;
    
    3)
        echo "[SCRIPT] Saliendo..."
    ;;
    
    *)echo "[ERROR] Opción no reconocida.";;
esac
