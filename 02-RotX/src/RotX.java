public class RotX {

    private static final String abecedari = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    private static final char[] minuscules = abecedari.toCharArray();
    private static final char[] majuscules = abecedari.toUpperCase().toCharArray();

    public static void main(String[] args) {
        int[] desplacaments = {0, 2, 4, 6};
        String[] cadenesXifrar = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};
        String[] cadenesDesxifrar = {"ABC", "ZAÁ", "Ïqoc, Óú. écoèqü", "Úiüht, úiü wx ùxì ív?"};

        System.out.println("Xifrat\n-------");
        for (int i = 0; i < cadenesXifrar.length; i++) {
            System.out.println("(" + desplacaments[i] + ")-" + cadenesXifrar[i] + "=>" + xifraRotX(cadenesXifrar[i], desplacaments[i]));
        }

        System.out.println("\nDesxifrat\n---------");
        for (int i = 0; i < cadenesDesxifrar.length; i++) {
            System.out.println("(" + desplacaments[i] + ") " + cadenesDesxifrar[i] + " => " + desxifraRotX(cadenesDesxifrar[i], desplacaments[i]));
        }

        System.out.println("\nForça Bruta\n-----------");
        forcaBrutaRotX("Úiüht, úiü wx ùxì ív?");
    }

    public static String xifraRotX(String cadena, int desplaçament) {
        StringBuilder resultat = new StringBuilder();
        int mida = minuscules.length;

        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);
            int posicio = trobaPosicio(lletra, minuscules);

            if (posicio != -1) {
                int novaPosicio = ((posicio + desplaçament) % mida + mida) % mida;
                resultat.append(minuscules[novaPosicio]);
            } else {
                posicio = trobaPosicio(lletra, majuscules);
                if (posicio != -1) {
                    int novaPosicio = ((posicio + desplaçament) % mida + mida) % mida;
                    resultat.append(majuscules[novaPosicio]);
                } else {
                    resultat.append(lletra);
                }
            }
        }
        return resultat.toString();
    }

    public static String desxifraRotX(String cadena, int desplaçament) {
        return xifraRotX(cadena, -desplaçament);
    }

    public static void forcaBrutaRotX(String cadenaXifrada) {
        for (int desplaçament = 0; desplaçament < abecedari.length(); desplaçament++) {
            String descifrat = desxifraRotX(cadenaXifrada, desplaçament);
            System.out.println("(" + desplaçament + ") " + descifrat);
        }
    }

    private static int trobaPosicio(char lletra, char[] alfabet) {
        for (int i = 0; i < alfabet.length; i++) {
            if (alfabet[i] == lletra) return i;
        }
        return -1;
    }
}