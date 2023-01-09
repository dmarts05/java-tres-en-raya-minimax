package juego;
// import static org.junit.Assert.*;
// import java.io.ByteArrayInputStream;
// import java.io.ByteArrayOutputStream;
// import java.io.InputStream;
// import java.io.PrintStream;
// import java.util.Scanner;

// import org.junit.After;
// import org.junit.Before;
// import org.junit.Ignore;
// import org.junit.Test;

// public class MainTests {

// private static InputStream in;
// private static PrintStream out;
// private Tablero tablero;
// private Partida partida;
// private Scanner sc;

// @Before
// public void realizaAntesDeCadaTest() throws Exception {
// tablero = new Tablero();
// partida = new Partida(tablero, 3, sc);
// }

// @Test
// public void test01() {
// char ficha = ' ';
// IA mini = new IA(tablero, ficha);
// assertEquals(mini.obtenerNumeroBusquedas(1), 32914);
// }

// @Test
// public void test02() {
// char ficha = ' ';
// IA mini = new IA(tablero, ficha);
// assertEquals(mini.obtenerNumeroBusquedas(2), 791);
// }

// }