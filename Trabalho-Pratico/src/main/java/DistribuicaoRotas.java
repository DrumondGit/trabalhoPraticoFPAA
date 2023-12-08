import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DistribuicaoRotas {
    private int numCaminhoes;
    private int[] rotas;
    private List<List<Integer>> melhorRotasPorCaminhao;
    private int minDiferenca = Integer.MAX_VALUE;

    public DistribuicaoRotas(int numCaminhoes, int[] rotas) {
        this.numCaminhoes = numCaminhoes;
        this.rotas = rotas;
    }

    public void distribuirRotas() {
        List<List<Integer>> rotasPorCaminhao = new ArrayList<>();
        for (int i = 0; i < numCaminhoes; i++) {
            rotasPorCaminhao.add(new ArrayList<>());
        }
        distribuirRotas(0, rotasPorCaminhao);
    }

    private void distribuirRotas(int rotaAtual, List<List<Integer>> rotasPorCaminhao) {
        if (rotaAtual == rotas.length) {
            int[] caminhoes = new int[numCaminhoes];
            for (int i = 0; i < numCaminhoes; i++) {
                for (int rota : rotasPorCaminhao.get(i)) {
                    caminhoes[i] += rota;
                }
            }

            int max = Arrays.stream(caminhoes).max().getAsInt();
            int min = Arrays.stream(caminhoes).min().getAsInt();
            int diferenca = max - min;
            if (diferenca < minDiferenca) {
                minDiferenca = diferenca;
                melhorRotasPorCaminhao = new ArrayList<>();
                for (List<Integer> rotas : rotasPorCaminhao) {
                    melhorRotasPorCaminhao.add(new ArrayList<>(rotas));
                }
            }
        } else {
            for (int i = 0; i < numCaminhoes; i++) {
                rotasPorCaminhao.get(i).add(rotas[rotaAtual]);

                int total = 0;
                for (int rota : rotasPorCaminhao.get(i)) {
                    total += rota;
                }

                if (total - Arrays.stream(rotas).sum() / numCaminhoes <= minDiferenca) {
                    distribuirRotas(rotaAtual + 1, rotasPorCaminhao);
                }

                rotasPorCaminhao.get(i).remove(rotasPorCaminhao.get(i).size() - 1);
            }
        }
    }

    public int getMinDiferenca() {
        return minDiferenca;
    }

    public List<List<Integer>> getMelhorRotasPorCaminhao() {
        return melhorRotasPorCaminhao;
    }

    public List<Integer> getQuilometragemPorCaminhao() {
        List<Integer> quilometragemPorCaminhao = new ArrayList<>();
        for (List<Integer> rotas : melhorRotasPorCaminhao) {
            int total = 0;
            for (int rota : rotas) {
                total += rota;
            }
            quilometragemPorCaminhao.add(total);
        }
        return quilometragemPorCaminhao;
    }
}
