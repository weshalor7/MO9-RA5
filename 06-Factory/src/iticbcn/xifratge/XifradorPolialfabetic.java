package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador {

    private final String ALFABET = "aàábcçdeèéfghiíïjklmnñoòópqrstuùúvwxyzAÀÁBCÇDEÈÉFGHIÍÏJKLMNÑOÒÓPQRSTUÙÚVWXYZ";

    private List<Character> permutaAlfabet(Random random) {
        List<Character> list = new ArrayList<>();
        for (char c : ALFABET.toCharArray()) {
            list.add(c);
        }
        Collections.shuffle(list, random);
        return list;
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        long seed;
        try {
            seed = Long.parseLong(clau);
        } catch (Exception e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }

        Random random = new Random(seed);
        StringBuilder sb = new StringBuilder();

        for (char c : msg.toCharArray()) {
            List<Character> perm = permutaAlfabet(random);
            int idx = ALFABET.indexOf(c);
            if (idx != -1) {
                sb.append(perm.get(idx));
            } else {
                sb.append(c);
            }
        }
        return new TextXifrat(sb.toString().getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        long seed;
        try {
            seed = Long.parseLong(clau);
        } catch (Exception e) {
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        }

        if (xifrat == null) return null;

        Random random = new Random(seed);
        String msg = xifrat.toString();
        StringBuilder sb = new StringBuilder();

        for (char c : msg.toCharArray()) {
            List<Character> perm = permutaAlfabet(random);
            int idx = perm.indexOf(c);
            if (idx != -1) {
                sb.append(ALFABET.charAt(idx));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}