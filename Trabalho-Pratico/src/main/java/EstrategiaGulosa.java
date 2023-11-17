import java.util.Comparator;
import java.util.List;

public class EstrategiaGulosa {

    public static void testarEstrategias(int[] tamanhosConjunto) {
        int numTestes = 10;

        for (int tamanho : tamanhosConjunto) {
            List<int[]> conjuntoDeTeste = GeradorDeProblemas.geracaoDeRotas(tamanho, tamanho * 10, 0.5);

            double mediaEG1 = 0;
            double mediaEG2 = 0;

            for (int teste = 0; teste < numTestes; teste++) {
                int[][] distribuicaoEG1 = distribuirRotas(conjuntoDeTeste, tamanho, "EG1");
                int[][] distribuicaoEG2 = distribuirRotas(conjuntoDeTeste, tamanho, "EG2");

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

    private static double calcularQuilometragemTotal(int[][] distribuicao) {
        double total = 0;
        for (int i = 0; i < distribuicao.length; i++) {
            for (int j = 0; j < distribuicao[0].length; j++) {
                total += distribuicao[i][j];
            }
        }
        return total;
    }


    private static int[][] distribuirRotas(List<int[]> rotas, int numCaminhoes, String estrategia) {
        int[][] distribuicao = new int[numCaminhoes][rotas.get(0).length];

        if ("EG1".equals(estrategia)) {
            estrategiaGulosa1(rotas, numCaminhoes, distribuicao);
        } else if ("EG2".equals(estrategia)) {
            estrategiaGulosa2(rotas, numCaminhoes, distribuicao);
        }

        return distribuicao;
    }

    private static void estrategiaGulosa1(List<int[]> rotas, int numCaminhoes, int[][] distribuicao) {
        // Ordenar rotas em ordem decrescente de quilometragem
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


    private static void estrategiaGulosa2(List<int[]> rotas, int numCaminhoes, int[][] distribuicao) {
        // Ordenar rotas em ordem crescente de quilometragem
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
}
