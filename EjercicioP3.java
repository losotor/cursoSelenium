import java.util.Scanner;

public class EjercicioP3 {

    public static void main (String args []) {

        Scanner input = new Scanner(System.in);
        System.out.println("Ingrese un numero: ");
        int num = input.nextInt();

        int duplicar = calcularDoble(num);
        System.out.println("El doble del numero ingresado es: " + duplicar);

    }

    public static int calcularDoble (int num) {
        return num * 2;
    }

}
