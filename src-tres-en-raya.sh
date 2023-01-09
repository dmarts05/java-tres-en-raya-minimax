#!/bin/bash

echo "Bienvenido al 3 en raya con IA Minimax, elige una opción:"
echo
echo "    1. Jugar al 3 en raya."
echo "    2. Ejecutar pruebas de rendimiento de los algoritmos."
echo "    3. Salir."

read option

case $option in
    1)
        # Compilar código Java y ejecutar juego
        mkdir -p bin && javac -d ./bin/ ./src/juego/*.java && java -cp ./bin/ TresEnRaya
    ;;
    
    2)
        # Compilar código Java y ejecutar prueba de rendimiento
        mkdir -p bin && javac -d ./bin/ ./src/rendimiento/*.java && java -cp ./bin/ LaboratorioDeRendimiento
    ;;
    
    3)
        echo "Saliendo..."
    ;;
    
    *)echo "[ERROR] Opción no reconocida.";;
esac