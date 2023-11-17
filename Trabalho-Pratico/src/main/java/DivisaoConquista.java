import java.util.Arrays;

public class DivisaoConquista {
    private int[] rotas;
    private int numCaminhoes;

    public DivisaoConquista(int[] rotas, int numCaminhoes) {
        this.rotas = rotas;
        this.numCaminhoes = numCaminhoes;
    }

    public int calcularDistribuicaoMinima() {
        int max = Arrays.stream(rotas).max().getAsInt();
        int sum = Arrays.stream(rotas).sum();

        while (max < sum) {
            int mid = max + (sum - max) / 2;
            if (verificarPossibilidade(mid)) {
                sum = mid;
            } else {
                max = mid + 1;
            }
        }
        return sum;
    }

    private boolean verificarPossibilidade(int mid) {
        int caminhoesNecessarios = 1;
        int somaAtual = 0;

        for (int i = 0; i < rotas.length; i++) {
            if (somaAtual + rotas[i] > mid) {
                caminhoesNecessarios++;
                somaAtual = rotas[i];
                if (caminhoesNecessarios > numCaminhoes) {
                    return false;
                }
            } else {
                somaAtual += rotas[i];
            }
        }
        return true;
    }
}
