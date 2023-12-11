import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EstrategiaGulosa {


    /**
     * Testa as estratégias EG1 e EG2 em conjuntos de teste de diferentes tamanhos.
     *
     * Este método realiza testes para avaliar o desempenho das estratégias Gulosa 1 (EG1) e
     * Gulosa 2 (EG2) em conjuntos de teste de diferentes tamanhos. A média de tempo total
     * para cada estratégia ao longo de vários testes.
     *
     * @param tamanhosConjunto Um array de inteiros representando os tamanhos dos conjuntos de teste.
     */
    public static void testarEstrategias( int[] tamanhosConjunto) {
        int numCaminhoes = 3;
        int numTestes = 10;

        for (int tamanhoConjunto : tamanhosConjunto) {
            double mediaTempoEG1 = 0;
            double mediaTempoEG2 = 0;
            int []  quilometragens;

            for (int teste = 0; teste < numTestes; teste++) {
                List<int[]> conjuntoDeTeste = GeradorDeProblemas.geracaoDeRotas(tamanhoConjunto , 10, 0.5);


                System.out.println("\nEstratégia Gulosa 1:");
                long startTimeEG1 = System.currentTimeMillis();
                quilometragens = conjuntoDeTeste(conjuntoDeTeste,"EG1");
                distribuirRotas(quilometragens, numCaminhoes, "EG1");
                long endTimeEG1 = System.currentTimeMillis();
                Long tempoEG1 = (endTimeEG1 - startTimeEG1) ;
                System.out.println("Tempo EG1: " + tempoEG1 + " ms");

                System.out.println("\nEstratégia Gulosa 2:");
                long startTimeEG2 = System.currentTimeMillis();
                quilometragens = conjuntoDeTeste(conjuntoDeTeste,"EG2");
                distribuirRotas(quilometragens, numCaminhoes, "EG2");
                long endTimeEG2 = System.currentTimeMillis();
                Long tempoEG2 = (endTimeEG2 - startTimeEG2);
                System.out.println("Tempo EG2: " + tempoEG2 + " ms");


                mediaTempoEG1 += tempoEG1;
                mediaTempoEG2 += tempoEG2;
            }

            mediaTempoEG1 /= numTestes;
            mediaTempoEG2 /= numTestes;

            System.out.println("\n" + "Tamanho do Conjunto: " + tamanhoConjunto);
            System.out.println("Média Tempo EG1: " + mediaTempoEG1 + " ms");
            System.out.println("Média Tempo EG2: " + mediaTempoEG2 + " ms");
            System.out.println();
        }
    }


    /**
     * Seleciona e retorna um conjunto de teste com base em uma estratégia específica.
     *
     * Este método recebe uma lista de conjuntos de teste e uma estratégia para selecionar um conjunto.
     * Os conjuntos de teste são representados como arrays de inteiros, onde cada elemento contém informações
     * relevantes para o teste. A estratégia determina a ordenação da lista antes de selecionar o conjunto.
     *
     * @param conjuntoDeTeste Uma lista de arrays de inteiros representando conjuntos de teste.
     * @param estrategia A estratégia de seleção de conjunto a ser utilizada ("EG1" ou "EG2").
     * @return Um array de inteiros representando o conjunto de teste selecionado de acordo com a estratégia.
     *         Retorna null se a estratégia não for reconhecida.
     */
    private static int[] conjuntoDeTeste( List<int[]> conjuntoDeTeste, String estrategia) {
        if ("EG1".equals(estrategia)) {
            conjuntoDeTeste.sort(Comparator.comparingInt(a -> a[0]));
            return conjuntoDeTeste.get(0);
        } else if ("EG2".equals(estrategia)) {
            conjuntoDeTeste.sort(Comparator.comparingInt(a -> -a[0]));
            return conjuntoDeTeste.get(0);
        }
        return null;
    }


    /**
     * Distribui as rotas entre os caminhões com base em uma estratégia específica.
     *
     * Este método recebe um array de quilometragens, o número de caminhões e uma estratégia
     * para distribuir as rotas entre os caminhões. Os caminhões são representados por uma matriz
     * onde cada coluna representa um caminhão e as rotas são distribuídas de acordo com a estratégia.
     *
     * @param quilometragens Um array de inteiros representando as quilometragens a serem distribuídas.
     * @param numCaminhoes O número de caminhões disponíveis.
     * @param estrategia A estratégia de distribuição de rotas a ser utilizada ("EG1" ou "EG2").
     * @return Uma matriz representando a distribuição das rotas entre os caminhões.
     */
    public static int[][] distribuirRotas(int[] quilometragens, int numCaminhoes, String estrategia) {
        int[][] caminhoes = new int[numCaminhoes][quilometragens.length ];
        Arrays.sort(quilometragens);

        if ("EG1".equals(estrategia)) {
            distribuirEG1(quilometragens, caminhoes);
        } else if ("EG2".equals(estrategia)) {
            distribuirEG2(quilometragens, caminhoes);
        }

        imprimirDistribuicao(caminhoes);
        return caminhoes;
    }

    /**
     * Distribui as quilometragens entre os caminhões de forma crescente.
     *
     * Este método recebe um array de quilometragens e uma matriz de caminhões.
     * As quilometragens são distribuídas entre os caminhões, preenchendo cada coluna
     * com os valores correspondentes em ordem crescente.
     *
     * @param quilometragens Um array de inteiros representando as quilometragens a serem distribuídas.
     * @param caminhoes Uma matriz onde cada coluna representa um caminhão e
     *                  as quilometragens são distribuídas de forma crescente em cada coluna.
     */
    private static void distribuirEG1(int[] quilometragens, int[][] caminhoes) {
        List<Integer> quilometragensList = Arrays.stream(quilometragens).boxed().collect(Collectors.toList());
        quilometragensList.sort(Comparator.naturalOrder());
        int i = 0;
        for (int c = 0; c < caminhoes[0].length; c++) {
            for (int l = 0; l < caminhoes.length; l++) {
                if (i == quilometragens.length) {
                    break;
                }
                caminhoes[l][c] = quilometragensList.get(i);
                i++;
            }
        }
    }


    /**
     * Distribui as quilometragens entre os caminhões de forma decrescente.
     *
     * Este método recebe um array de quilometragens e uma matriz de caminhões.
     * As quilometragens são distribuídas entre os caminhões, preenchendo cada coluna
     * com os valores correspondentes em ordem decrescente.
     *
     * @param quilometragens Um array de inteiros representando as quilometragens a serem distribuídas.
     * @param caminhoes Uma matriz onde cada coluna representa um caminhão e
     *                  as quilometragens são distribuídas de forma decrescente em cada coluna.
     */
    private static void distribuirEG2(int[] quilometragens, int[][] caminhoes) {
        List<Integer> quilometragensList = Arrays.stream(quilometragens).boxed().collect(Collectors.toList());
        quilometragensList.sort(Comparator.reverseOrder());
        int i = 0;
        for (int c = 0; c < caminhoes[0].length; c++) {
            for (int l = 0; l < caminhoes.length; l++) {
                if (i == quilometragens.length) {
                    break;
                }
                caminhoes[l][c] = quilometragensList.get(i);
                i++;
            }
        }
    }


    /**
     * Gera e retorna um conjunto de números inteiros com base no valor inicial fornecido.
     *
     * @param init O valor inicial a ser usado para gerar o conjunto.
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

    /**
     * Imprime a distribuição das distâncias percorridas por cada caminhão.
     *
     * @param caminhoes Uma matriz 2D onde cada linha representa um caminhão
     *                  e cada coluna representa a distância percorrida em uma rota.
     */
    private static void imprimirDistribuicao(int[][] caminhoes) {
        for (int l = 0; l < caminhoes.length; l++) {
            System.out.print("Caminhão " + (l + 1) + ": rotas ");
            int totalKm = 0;

            for (int c = 0; c < caminhoes[l].length; c++) {
                if (caminhoes[l][c] != 0) {
                    //System.out.print(caminhoes[l][c]);
                    totalKm += caminhoes[l][c];

                    if (c < caminhoes[l].length - 1 && caminhoes[l][c + 1] != 0) {
                      //  System.out.print(", ");
                    }
                }
            }

            System.out.println(" - total " + totalKm + "km");
        }
    }
}
