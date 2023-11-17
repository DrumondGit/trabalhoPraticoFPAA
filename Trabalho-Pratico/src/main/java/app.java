public class app {
    public static void main(String[] args) {
        int[] tamanhosConjunto = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        EstrategiaGulosa.testarEstrategias(tamanhosConjunto);
        divisaoConquista();
    }

    public static void divisaoConquista() {
        int[] rotas = {35, 34, 33, 23, 21, 32, 35, 19, 26, 42};
        int numCaminhoes = 3;
        DivisaoConquista distribuicao = new DivisaoConquista(rotas, numCaminhoes);
        System.out.println("A distribuição mínima é: " + distribuicao.calcularDistribuicaoMinima());
    }
}
