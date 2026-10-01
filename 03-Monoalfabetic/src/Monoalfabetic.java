import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {

    private static String abecedari = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    private static char[] majuscules = abecedari.toUpperCase().toCharArray();
    private static char[] alfabetPermutat;

    public static String xifraMonoAlfa(String cadena) {
        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char letra = cadena.charAt(i);
            int posicio = -1;

            for (int j = 0; j < majuscules.length; j++) {
                if (majuscules[j] == Character.toUpperCase(letra)) {
                    posicio = j;
                    break;
                }
            }

            if (posicio != -1) {
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

    public static String desxifraMonoAlfa(String cadena) {
        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char letra = cadena.charAt(i);
            int posicio = -1;

            for (int j = 0; j < alfabetPermutat.length; j++) {
                if (alfabetPermutat[j] == Character.toUpperCase(letra)) {
                    posicio = j;
                    break;
                }
            }

            if (posicio != -1) {
                char desxifrada = majuscules[posicio];

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

    public static char[] permutaAlfabet(char[] alfabet){
        List<Character> llista = new ArrayList<>();

        for(int i = 0; i < alfabet.length; i++){
            llista.add(alfabet[i]);
        }

        Collections.shuffle(llista);

        char[] permutat = new char[llista.size()];

        for(int i = 0; i < llista.size(); i++){
            permutat[i] = llista.get(i);
        }
        return permutat;
    }

    public static void main(String[] args){
        alfabetPermutat = permutaAlfabet(majuscules);

        String[] paraules = {"Test 01 àrbitre, coixí, Perímetre" , 
                             "Test 02 Taüll, DÍA, año" , 
                             "Test 03 Peça, Òrrius, Bòlivia"};

        String[] xifrats = new String[paraules.length];

        System.out.println("Xifratge\n---------");

        for(int i = 0; i < paraules.length; i ++){
            xifrats[i] = xifraMonoAlfa(paraules[i]);
            System.out.println(paraules[i] + " => " + xifrats[i]);
        }

        System.out.println("\nDesxifratge\n---------");
        
        for(int i = 0; i < xifrats.length; i++){
            String deisxifrat = desxifraMonoAlfa(xifrats[i]);
            System.out.println(xifrats[i] + " => " + deisxifrat);
        }
    }
}