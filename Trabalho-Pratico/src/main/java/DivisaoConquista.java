public class DivisaoConquista {


    public static int[] divideConquista(int[] rotas, int numCaminhoes) {
        int[] distribuicao = new int[numCaminhoes];
        int[] alvos = new int[numCaminhoes];
        int somatorioRotas = calcularSomatorio(rotas);
        for (int i = 0; i < numCaminhoes; i++) {
            alvos[i] = somatorioRotas / numCaminhoes;
        }

        distribuirRotas(rotas, alvos, distribuicao, 0, rotas.length - 1, 0);

        return distribuicao;
    }

    private static int calcularSomatorio(int[] rotas) {
        int somatorio = 0;
        for (int rota : rotas) {
            somatorio += rota;
        }
        return somatorio;
    }

    public static void distribuirRotas(int[] rotas, int[] alvos, int[] distribuicao, int inicio, int fim, int caminhaoAtual) {
        if (inicio > fim) {
            return;
        }

        int meio = (inicio + fim) / 2;

        // Verifica se ultrapassa o alvo
        if (distribuicao[caminhaoAtual] + rotas[meio] > alvos[caminhaoAtual]) {
            // Se ultrapassa, muda para o próximo caminhão
            caminhaoAtual = (caminhaoAtual + 1) % distribuicao.length;
        }

        // Atribui a rota ao caminhão atual
        distribuicao[caminhaoAtual] += rotas[meio];

        // Chama recursivamente a função para os subarrays à esquerda e à direita do meio
        distribuirRotas(rotas, alvos, distribuicao, inicio, meio - 1, caminhaoAtual);
        distribuirRotas(rotas, alvos, distribuicao, meio + 1, fim, caminhaoAtual);
    }

    public static void imprimirResultados(int[] distribuicao) {
        for (int i = 0; i < distribuicao.length; i++) {
            System.out.println("Caminhao " + (i + 1) + ": " + distribuicao[i] + "km");
        }
    }
}
