package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XifradorMonoalfabetic implements Xifrador {

    private final String ALFABET = "aàábcçdeèéfghiíïjklmnñoòópqrstuùúvwxyzAÀÁBCÇDEÈÉFGHIÍÏJKLMNÑOÒÓPQRSTUÙÚVWXYZ";
    private List<Character> permuta;

    public XifradorMonoalfabetic() {
        initPermutacio();
    }

    private void initPermutacio() {
        permuta = new ArrayList<>();
        for (char c : ALFABET.toCharArray()) {
            permuta.add(c);
        }
        Collections.shuffle(permuta);
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }

        StringBuilder sb = new StringBuilder();
        for (char c : msg.toCharArray()) {
            int idx = ALFABET.indexOf(c);
            if (idx != -1) {
                sb.append(permuta.get(idx));
            } else {
                sb.append(c);
            }
        }
        return new TextXifrat(sb.toString().getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        if (xifrat == null) return null;

        String msg = xifrat.toString();
        StringBuilder sb = new StringBuilder();
        for (char c : msg.toCharArray()) {
            int idx = permuta.indexOf(c);
            if (idx != -1) {
                sb.append(ALFABET.charAt(idx));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}