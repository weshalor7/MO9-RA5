package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {

    private final String LOWERCASE = "aàábcçdeèéfghiíïjklmnñoòópqrstuùúvwxyz";
    private final String UPPERCASE = "AÀÁBCÇDEÈÉFGHIÍÏJKLMNÑOÒÓPQRSTUÙÚVWXYZ";

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int rot;
        try {
            rot = Integer.parseInt(clau);
        } catch (Exception e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un enter de 0 a 40");
        }

        if (rot < 0 || rot > 40) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un enter de 0 a 40");
        }

        StringBuilder sb = new StringBuilder();
        for (char c : msg.toCharArray()) {
            sb.append(xifraChar(c, rot));
        }
        return new TextXifrat(sb.toString().getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (xifrat == null) return null;

        int rot;
        try {
            rot = Integer.parseInt(clau);
        } catch (Exception e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un enter de 0 a 40");
        }

        if (rot < 0 || rot > 40) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un enter de 0 a 40");
        }

        String msg = xifrat.toString();
        StringBuilder sb = new StringBuilder();
        for (char c : msg.toCharArray()) {
            sb.append(desxifraChar(c, rot));
        }
        return sb.toString();
    }

    private char xifraChar(char c, int rot) {
        int idx = LOWERCASE.indexOf(c);
        if (idx != -1) {
            return LOWERCASE.charAt((idx + rot) % LOWERCASE.length());
        }
        idx = UPPERCASE.indexOf(c);
        if (idx != -1) {
            return UPPERCASE.charAt((idx + rot) % UPPERCASE.length());
        }
        return c;
    }

    private char desxifraChar(char c, int rot) {
        int idx = LOWERCASE.indexOf(c);
        if (idx != -1) {
            int newIdx = (idx - rot) % LOWERCASE.length();
            if (newIdx < 0) newIdx += LOWERCASE.length();
            return LOWERCASE.charAt(newIdx);
        }
        idx = UPPERCASE.indexOf(c);
        if (idx != -1) {
            int newIdx = (idx - rot) % UPPERCASE.length();
            if (newIdx < 0) newIdx += UPPERCASE.length();
            return UPPERCASE.charAt(newIdx);
        }
        return c;
    }
}