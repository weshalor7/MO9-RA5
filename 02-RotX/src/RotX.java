public class RotX {
   
    private static String abecedari = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    private static char[] minuscules = abecedari.toCharArray();
    private static char[] majuscules = abecedari.toUpperCase().toCharArray();
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

    public static String desxifratRotX(String cadena, int desplaçament){
        int desplaçamentInvers = -desplaçament;
        return xifraRotX(cadena, desplaçamentInvers);
    }

    public static String forcaBrutaRotX(String cadenaXifrada){
        for(int desplaçament = 0; desplaçament < abecedari.length(); desplaçament++){
            String descifrat = desxifratRotX(cadenaXifrada, desplaçament);
            System.out.println("(" + desplaçament + ")" + descifrat);
        }
        return "";
    }

    
}