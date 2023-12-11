import java.util.Arrays;

public class DivisaoConquista {

    public static int[][] dividirEResolver(int[] rotas, int N) {
        int[][] distribuicao = new int[N][rotas.length / N + 1]; // +1 para garantir espaço extra
        distribuirRotas(rotas, 0, rotas.length - 1, distribuicao, N);
        return distribuicao;
    }

    private static void distribuirRotas(int[] rotas, int inicio, int fim, int[][] distribuicao, int N) {
        if (inicio <= fim) {
            int meio = (inicio + fim) / 2;
            int caminhaoMenorKmIndex = encontrarCaminhaoMenorKm(distribuicao);
            int indiceVazio = encontrarIndiceVazio(distribuicao[caminhaoMenorKmIndex]);

            if (indiceVazio != -1) {
                distribuicao[caminhaoMenorKmIndex][indiceVazio] = rotas[meio];
                distribuirRotas(rotas, inicio, meio - 1, distribuicao, N);
                distribuirRotas(rotas, meio + 1, fim, distribuicao, N);
            }
        }
    }

    private static int encontrarCaminhaoMenorKm(int[][] distribuicao) {
        int indexMenorKm = 0;
        int menorKm = Integer.MAX_VALUE;

        for (int i = 0; i < distribuicao.length; i++) {
            int kmTotal = Arrays.stream(distribuicao[i]).sum();
            if (kmTotal < menorKm) {
                menorKm = kmTotal;
                indexMenorKm = i;
            }
        }

        return indexMenorKm;
    }

    private static int encontrarIndiceVazio(int[] caminhao) {
        for (int i = 0; i < caminhao.length; i++) {
            if (caminhao[i] == 0) {
                return i;
            }
        }

        return -1;
    }

}
