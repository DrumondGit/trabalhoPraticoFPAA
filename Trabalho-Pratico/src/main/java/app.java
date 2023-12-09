import java.util.Arrays;
import java.util.List;

public class app {
    public static void main(String[] args) {
        // int[] tamanhosConjunto = { 40, 36, 38, 29, 32, 28, 31, 35, 31, 30, 32, 30, 29, 39, 35, 38, 39, 35, 32, 38, 32,
        //         33, 29, 33, 29, 39, 28 };
        // EstrategiaGulosa.testarEstrategias(tamanhosConjunto);
        // EstrategiaGulosa.testarEstrategias(EstrategiaGulosa.gerarConjunto(19));

        divisaoConquista();
        // backtracking();
    }

    public static void divisaoConquista() {
        int numRotas = 19;
        int numCaminhoes = 3;
        List<int[]> conjuntosDeRotas = GeradorDeProblemas.geracaoDeRotas(numRotas, 10, 0.5);
        for (int i = 0; i < 10; i++) {
            int[][] distribuicao = DivisaoConquista.dividirEResolver(conjuntosDeRotas.get(i), numCaminhoes);

            // Imprimir a distribuição e o total de cada rota
            for (int j = 0; j < numCaminhoes; j++) {
                int totalCaminhao = Arrays.stream(distribuicao[j]).sum();
                System.out.println(
                        "Caminhão " + (j + 1) + ": " + Arrays.toString(distribuicao[j]) + " - Total: " + totalCaminhao);
            }
        }

    }

    public static void backtracking() {
        int numRotas = 6;
        while (true) {
            // Gera uma lista de conjuntos de rotas
            List<int[]> conjuntosDeRotas = GeradorDeProblemas.geracaoDeRotas(numRotas, 10, 0.5);
            long totalDuration = 0;

            // Para cada conjunto de rotas, distribui as rotas entre os caminhões e imprime
            // a menor diferença de quilometragem
            for (int[] rotas : conjuntosDeRotas) {
                long startTime = System.nanoTime();
                Backtracking distribuicao = new Backtracking(3, rotas);
                distribuicao.distribuirRotas();
                long endTime = System.nanoTime();
                long duration = (endTime - startTime);
                totalDuration += duration;
                for (int i = 0; i < distribuicao.getMelhorRotasPorCaminhao().size(); i++) {
                    System.out.println("Caminhão " + (i + 1) + ": " + distribuicao.getMelhorRotasPorCaminhao().get(i));
                }
                for (int i = 0; i < distribuicao.getQuilometragemPorCaminhao().size(); i++) {
                    System.out.println(
                            "Caminhão " + (i + 1) + ": " + distribuicao.getQuilometragemPorCaminhao().get(i) + " km");
                }
            }

            long averageDuration = totalDuration / conjuntosDeRotas.size();
            System.out.println("Número de rotas: " + numRotas);
            System.out.println("Duração média: " + averageDuration / 1000000 + " ms");

            if (averageDuration > 30000000000L) {
                System.out.println(
                        "O número máximo de rotas que pode ser resolvido em menos de 30 segundos é: " + (numRotas - 1));

                break;
            }

            numRotas++;
        }

    }

    public static void programacaoDinamica() {
        ProgramacaoDinamica.inico();
    }
}
