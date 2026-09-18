public class Rot13{
   
    public static char[] minuscules = {
            'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è',
            'f', 'g', 'h', 'i', 'í', 'ì', 'ï', 'j', 'k', 'l',
            'm', 'n', 'ñ', 'o', 'ó', 'ò', 'p', 'q', 'r', 's',
            't', 'u', 'ú', 'ù', 'ü', 'v', 'w', 'x', 'y', 'z'
    };
      public static char[] majuscules = {'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È',
            'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L',
            'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S',
            'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };

    public static String xifratRot13(String cadena){
        String resultat = "";

        for(int i = 0; i < cadena.length(); i++){
            char lletra = cadena.charAt(i);
            char newLeter = desplaça(lletra, 13);
            resultat += newLeter;

        }
        return resultat;
    }
    
    public static String desxifratRot13(String cadena){
        String resultat = "";
        for(int i = 0; i < cadena.length(); i++){
            char lletra = cadena.charAt(i);
            char newLeter = desplaça(lletra, -13);
            resultat += newLeter;
        }
        return resultat;
   
    }

    public static char desplaça(char lletra, int desplaçament){
        for(int i = 0; i < minuscules.length; i++){
            if(minuscules[i] == lletra){
                int novaPosicio = i + desplaçament;

                if(novaPosicio >= minuscules.length){
                    novaPosicio = novaPosicio - minuscules.length;
                }

                if(novaPosicio < 0){
                    novaPosicio = novaPosicio + minuscules.length;
                }


                return minuscules[novaPosicio];
            }
        }

        for(int i = 0; i < majuscules.length; i++){
            if(majuscules [i] == lletra){
                int novaPosicio = i + desplaçament;

                if(novaPosicio >= majuscules.length){
                    novaPosicio = novaPosicio - majuscules.length;
                }
                
                if(novaPosicio < 0){
                    novaPosicio += majuscules.length;
                }

                return majuscules[novaPosicio];
            }
        }

       
        return lletra;
    }


    public static void main(String[] args){
        String[] paraules = {"ABC", "XYZ" , "Hola, Mr. calçot", "Perdó, per tu què és?"};
        
        String[] xifrats = new String[paraules.length];
        System.out.println("Xifrat\n ---------");

        for(int i = 0; i < paraules.length; i++){
                xifrats[i] = xifratRot13(paraules[i]);
                System.out.println(paraules[i] + " => " + xifrats[i]);
        }

        System.out.println("\nDesxifrat\n ---------");

        for(int i = 0; i < xifrats.length; i++){
            String desxifrat = desxifratRot13(xifrats[i]);
            System.out.println(xifrats[i] + " => " + desxifrat);
        }
    }


}