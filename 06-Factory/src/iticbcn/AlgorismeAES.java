package iticbcn;

public class AlgorismeAES extends AlgorismeFactory {
    @Override
    public Xifrador creaXifrador() {
        return new XifradorAES();
    }
}