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
}