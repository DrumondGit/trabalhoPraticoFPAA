import java.util.Comparator;
import java.util.List;

public class EstrategiaGulosa {


    /**
     * Realiza testes para avaliar o desempenho de duas estratégias gulosas na distribuição
     * de rotas para caminhões, comparando a média das quilometragens resultantes.
     *
     * @param Conjunto Um array de inteiros representando os tamanhos dos conjuntos de testes.
     */
    public static void testarEstrategias(int[] Conjunto) {
        int numTestes = 10;
        int num_caminhoes = 3;

        for (int tamanho : Conjunto) {
            int quantRotas = tamanho / 2;
            List<int[]> conjuntoDeTeste = GeradorDeProblemas.geracaoDeRotas(quantRotas, tamanho, 0.5);

            double mediaEG1 = 0;
            double mediaEG2 = 0;

            for (int teste = 0; teste < numTestes; teste++) {
                int[][] distribuicaoEG1 = distribuirRotas(conjuntoDeTeste, num_caminhoes, "EG1");
                int[][] distribuicaoEG2 = distribuirRotas(conjuntoDeTeste, num_caminhoes, "EG2");

                mediaEG1 += calcularQuilometragemTotal(distribuicaoEG1);
                mediaEG2 += calcularQuilometragemTotal(distribuicaoEG2);
            }

            mediaEG1 /= numTestes;
            mediaEG2 /= numTestes;

            System.out.println("Tamanho do Conjunto: " + tamanho);
            System.out.println("Média EG1: " + mediaEG1);
            System.out.println("Média EG2: " + mediaEG2);
            System.out.println();

        }

    }


    /**
     * Calcula a quilometragem total de uma distribuição de rotas para caminhões.
     *
     * @param distribuicao Uma matriz representando a distribuição de rotas para caminhões,
     *                     onde cada linha representa um caminhão e cada coluna representa uma rota.
     * @return A quilometragem total da distribuição de rotas.
     */
    private static double calcularQuilometragemTotal(int[][] distribuicao) {
        double total = 0;
        for (int i = 0; i < distribuicao.length; i++) {
            for (int j = 0; j < distribuicao[0].length; j++) {
                total += distribuicao[i][j];
            }
        }
        return total;
    }


    /**
     * Distribui rotas entre caminhões utilizando uma estratégia gulosa especificada.
     *
     * @param rotas      Uma lista de inteiros representando as quilometragens das rotas.
     * @param numCaminhoes O número de caminhões disponíveis para a distribuição.
     * @param estrategia A estratégia gulosa a ser utilizada ("EG1" ou "EG2").
     * @return Uma matriz representando a distribuição de rotas entre os caminhões.
     */
    private static int[][] distribuirRotas(List<int[]> rotas, int numCaminhoes, String estrategia) {
        int[][] distribuicao = new int[numCaminhoes][rotas.get(0).length];

        if ("EG1".equals(estrategia)) {
            estrategiaGulosa1(rotas, numCaminhoes, distribuicao);
        } else if ("EG2".equals(estrategia)) {
            estrategiaGulosa2(rotas, numCaminhoes, distribuicao);
        }

        return distribuicao;
    }


    /**
     * Aplica a Estratégia Gulosa 1 para distribuição de rotas entre caminhões.
     * A estratégia consiste em ordenar as rotas em ordem decrescente de quilometragem
     * e atribuir cada rota ao caminhão com a menor quilometragem acumulada até o momento.
     *
     * @param rotas         Uma lista de inteiros representando as quilometragens das rotas.
     * @param numCaminhoes  O número de caminhões disponíveis para a distribuição.
     * @param distribuicao  Uma matriz representando a distribuição de rotas entre os caminhões.
     */
    private static void estrategiaGulosa1(List<int[]> rotas, int numCaminhoes, int[][] distribuicao) {
        rotas.sort((a, b) -> Integer.compare(b[0], a[0]));

        for (int j = 0; j < rotas.get(0).length; j++) {
            for (int i = 0; i < numCaminhoes; i++) {
                if (distribuicao[i][j] == 0) {
                    distribuicao[i][j] = rotas.get(j)[i];
                    break;
                }
            }
        }
    }

    /**
     * Aplica a Estratégia Gulosa 2 para distribuição de rotas entre caminhões.
     * A estratégia consiste em ordenar as rotas em ordem crescente de quilometragem
     * e atribuir cada rota ao caminhão com a menor quilometragem acumulada até o momento.
     *
     * @param rotas         Uma lista de inteiros representando as quilometragens das rotas.
     * @param numCaminhoes  O número de caminhões disponíveis para a distribuição.
     * @param distribuicao  Uma matriz representando a distribuição de rotas entre os caminhões.
     */
    private static void estrategiaGulosa2(List<int[]> rotas, int numCaminhoes, int[][] distribuicao) {
        rotas.sort(Comparator.comparingInt(a -> a[0]));

        for (int j = 0; j < rotas.get(0).length; j++) {
            for (int i = 0; i < numCaminhoes; i++) {
                if (distribuicao[i][j] == 0) {
                    distribuicao[i][j] = rotas.get(j)[i];
                    break;
                }
            }
        }
    }

    /**
     * Gera um conjunto de inteiros com base em um valor inicial, aumentando-o em incrementos fixos.
     *
     * @param init O valor inicial para o primeiro elemento do conjunto.
     * @return Um array de inteiros representando o conjunto gerado.
     */
    public static int[] gerarConjunto(int init) {
        int[] conjunto = new int[10];
        for (int i = 0; i < conjunto.length; i++) {
            conjunto[i] = init;
            init += 19;
        }
        return conjunto;
    }
}
