#!/bin/bash

echo "[SCRIPT] Bienvenido al 3 en raya con IA Minimax, elige una opción:"
echo "    1. Jugar al 3 en raya."
echo "    2. Ejecutar pruebas de rendimiento de los algoritmos."
echo "    3. Salir."

read option

case $option in
    1)
        # Compilar código Java y ejecutar juego
        rm -rf bin && mkdir -p bin && javac -d ./bin/ ./src/juego/*.java && java -cp ./bin/ juego.TresEnRaya
    ;;
    
    2)
        # Compilar código Java y ejecutar laboratorio
        rm -rf bin && mkdir -p bin && javac -d ./bin/ ./src/juego/*.java ./src/laboratorio/*.java && java -cp ./bin/ laboratorio.LaboratorioDeRendimiento
    ;;
    
    3)
        echo "[SCRIPT] Saliendo..."
    ;;
    
    *)echo "[ERROR] Opción no reconocida.";;
esac