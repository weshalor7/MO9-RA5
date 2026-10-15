package iticbcn.xifratge;

public class AlgorismeRotx extends AlgorismeFactory {
    @Override
    public Xifrador creaXifrador() {
        return new XifradorRotX();
    }
}