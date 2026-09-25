public class RotX {
    
    private static String abecedari = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    private static char[] minuscules = abecedari.toCharArray();
    private static char[] majuscules = abecedari.toUpperCase().toCharArray();
    
    public static void main(String[] args) {
        RotX rot = new RotX();
        
        int[] desplacaments = {0, 2, 4, 6};
        String[] cadenesXifrar = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};
        String[] cadenesDesxifrar = {"ABC", "ZAÁ", "Ïqoc, Óú. écoèqü", "Úiüht, úiü wx ùxì ív?"};
        
        System.out.println("Xifrat\n-------");
        for (int i = 0; i < cadenesXifrar.length; i++) {
            System.out.println("(" + desplacaments[i] + ")-" + cadenesXifrar[i] + "=>" + rot.xifraRotX(cadenesXifrar[i], desplacaments[i]));
        }

        System.out.println("\nDesxifrat\n---------");
        for (int i = 0; i < cadenesDesxifrar.length; i++) {
            System.out.println("(" + desplacaments[i] + ") " + cadenesDesxifrar[i] + " => " + rot.desxifraRotX(cadenesDesxifrar[i], desplacaments[i]));
        }
        
        System.out.println("\nForça Bruta\n-----------");
        rot.forcaBrutaRotX("Úiüht, úiü wx ùxì ív?");
    }

    public static String xifraRotX(String cadena, int desplaçament){
        String resultat = "";
        for(int i = 0; i < cadena.length(); i++){
            char letra = cadena.charAt(i);
            int posicio = -1;
            
            for(int j = 0; j < minuscules.length; j++){
                if(minuscules[j] == letra){
                    posicio = j;
                    break;
                }
            }
            
            if(posicio != -1){
                int nuevaPosicio = posicio + desplaçament;                
                if(nuevaPosicio >= minuscules.length){
                    nuevaPosicio = nuevaPosicio - minuscules.length;
                }                
                if(nuevaPosicio < 0){
                    nuevaPosicio = nuevaPosicio + minuscules.length;
                }
                
                resultat = resultat + minuscules[nuevaPosicio];
            }
            else{
                posicio = -1;
                for(int j = 0; j < majuscules.length; j++){
                    if(majuscules[j] == letra){
                        posicio = j;
                        break;
                    }
                }
                
                if(posicio != -1){
                    int nuevaPosicio = posicio + desplaçament;
                    
                    if(nuevaPosicio >= majuscules.length){
                        nuevaPosicio = nuevaPosicio - majuscules.length;
                    }                    
                    if(nuevaPosicio < 0){
                        nuevaPosicio = nuevaPosicio + majuscules.length;
                    }
                    
                    resultat = resultat + majuscules[nuevaPosicio];
                }
                else{
                    resultat = resultat + letra;
                }
            }
        }
        
        return resultat;
    }

    public static String desxifraRotX(String cadena, int desplaçament){
        int desplaçamentInvers = -desplaçament;
        return xifraRotX(cadena, desplaçamentInvers);
    }

    public static String forcaBrutaRotX(String cadenaXifrada){
        for(int desplaçament = 0; desplaçament < abecedari.length(); desplaçament++){
            String descifrat = desxifraRotX(cadenaXifrada, desplaçament);
            System.out.println("(" + desplaçament + ")" + descifrat);
        }
        return "";
    }
}