public class TestXifratge {
    public static void main(String[] args) {
        AlgorismeFactory[] aFactory = {
            new AlgorismeAES(), new AlgorismeMonoalfabetic(),
            new AlgorismePoliAlfabetic(), new AlgorismeRotx()
        };

        String[] aNames = {"AES", "Monoalfabetic", "Polialfabetic", "Rotx"};

        String[] msgs = {
            "Test 01: Àlgid, Ülrich, Vàlid",
            "Test 02: Caràcters especials ¡!¿?*-123[]{}@#"
        };

        String[][] claus = {
            {"Clau Secreta 01"},
            {"ErrorClau", null},
            {"ErrorClau2", "123456"},
            {"-1", "13,", "1000", "Errorclau"}
        };

        for (int i = 0; i < aFactory.length; i++) {
            AlgorismeFactory alg = aFactory[i]; // Corregido: AlgorismeFactory
            String nom = aNames[i];

            Xifrador xifrador = alg.creaXifrador();

            System.out.println("=====================");

            for (String msg : msgs) {
                for (String clau : claus[i]) {
                    System.out.println("msg " + msg);
                    System.out.println("Algorisme: " + nom);
                    System.out.println("Clau " + clau);

                    TextXifrat tx = null; // Declaración de la variable tx

                    try {
                        tx = xifrador.xifra(msg, clau);
                    } catch (ClauNoSuportada e) { // Se añade el bloque catch faltante
                        System.err.println(e.getLocalizedMessage());
                    }

                    System.out.println("TextXifrat: " + tx);
                    String desxifrat = null;

                    try {
                        desxifrat = xifrador.desxifra(tx, clau);
                    } catch (ClauNoSuportada e) {
                        System.err.println(e.getLocalizedMessage());
                    }

                    System.out.println("Desxifrat: " + desxifrat);
                    System.out.println("--------------");
                }
            }
        }
    }
}