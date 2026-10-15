import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Polialfabetic {

    private static final String clauSecreta = "HolaImad1234";

    private static String abecedari = "aáàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz";
    private static char[] majuscules = abecedari.toUpperCase().toCharArray();
    
    private static char[] alfabetPermutat;

    private static Random random;

    public static void initRandom(String clau) {
        random = new Random(clau.hashCode());
    }

  
    public static void permutaAlfabet() {
        List<Character> llista = new ArrayList<>();

        for (int i = 0; i < majuscules.length; i++) {
            llista.add(majuscules[i]);
        }

        Collections.shuffle(llista, random);

        alfabetPermutat = new char[llista.size()];

        for (int i = 0; i < llista.size(); i++) {
            alfabetPermutat[i] = llista.get(i);
        }
    }

     
    public static String xifraPoliAlfa(String msg) {
        String resultat = "";

        for (int i = 0; i < msg.length(); i++) {
            char letra = msg.charAt(i);
            int posicio = -1;

            for (int j = 0; j < majuscules.length; j++) {
                if (majuscules[j] == Character.toUpperCase(letra)) {
                    posicio = j;
                    break;
                }
            }

            if (posicio != -1) {
                permutaAlfabet();
                char xifrada = alfabetPermutat[posicio];

                if (Character.isLowerCase(letra)) {
                    resultat += Character.toLowerCase(xifrada);
                } else {
                    resultat += xifrada;
                }
            } else {
                resultat += letra;
            }
        }
        return resultat;
    }

        public static String desxifraPoliAlfa(String msgXifrat) {
        String resultat = "";

        for (int i = 0; i < msgXifrat.length(); i++) {
            char letra = msgXifrat.charAt(i);
            int posicio = -1;
            for (int j = 0; j < majuscules.length; j++) {
                if (majuscules[j] == Character.toUpperCase(letra)) {
                    posicio = j;
                    break;
                }
            }

            if (posicio != -1) {
                permutaAlfabet();
                int posicioPermutada = -1;
                for (int j = 0; j < alfabetPermutat.length; j++) {
                    if (alfabetPermutat[j] == Character.toUpperCase(letra)) {
                        posicioPermutada = j;
                        break;
                    }
                }

                char desxifrada = majuscules[posicioPermutada];

                if (Character.isLowerCase(letra)) {
                    resultat += Character.toLowerCase(desxifrada);
                } else {
                    resultat += desxifrada;
                }
            } else {
                resultat += letra;
            }
        }

        return resultat;
    }

    public static void main(String[] args) {
        String msgs[] = {
            "Test 01 àrbitre, coixí, Perímetre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila"
        };
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n---------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("\nDesxifratge:\n------------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }
}