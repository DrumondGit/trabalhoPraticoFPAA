import java.util.Arrays;
import java.util.List;

public class ProgramacaoDinamica {
    static int INF = Integer.MAX_VALUE;
    static int[][] resultadosInter;
    static int[] distancias;
    static int[][] rotasSolucao;

    public static void inico() {
        // int[] rotas = {35, 34, 33, 23, 21, 32, 35, 19, 26, 42};
        int N = 3;
        double tempoTotal = 0.0;
        int qtdRotas = 19;
        List<int[]> conjuntoDeTeste = GeradorDeProblemas.geracaoDeRotas(qtdRotas, 10, 0.5);
        for (int j = 0; j < 10; j++) {
            for (int[] rotas : conjuntoDeTeste) {
                //   for (int i = 0; i < rotas.length; i++) {

                //       System.out.print(rotas[i] + " ");

                //   }
                System.out.println();
                long startTime = System.currentTimeMillis();
                pd(rotas, N);
                long endTime = System.currentTimeMillis();
                long tempoExecucao = endTime - startTime;
                System.out.println("Tempo de execução: " + tempoExecucao + " milissegundos");
                System.out.println();
                tempoTotal += tempoExecucao;

            }
            
            System.out.println("Média do tempo de execução do conjunto de " + qtdRotas + ": " + tempoTotal/10);
            qtdRotas += 19;
            conjuntoDeTeste = GeradorDeProblemas.geracaoDeRotas(qtdRotas, 10, 0.5);
            tempoTotal = 0;
        }
    }

    public static void pd(int[] rotas, int N) {
        Arrays.sort(rotas);
        distancias = new int[rotas.length + 1];
        for (int i = 1; i <= rotas.length; i++) {
            distancias[i] = distancias[i - 1] + rotas[i - 1];
        }
        resultadosInter = new int[N + 1][rotas.length + 1];
        rotasSolucao = new int[N + 1][rotas.length + 1];
    
        for (int caminhoes = 1; caminhoes <= N; caminhoes++) {
            for (int nRotas = 1; nRotas <= rotas.length; nRotas++) {
                resultadosInter[caminhoes][nRotas] = -1;
            }
        }
    
        solve(N, rotas.length);
    
        int[] caminhoes = new int[N];
        int nRotas = rotas.length;
        for (int i = N; i > 0; i--) {
            caminhoes[i - 1] = distancias[nRotas] - distancias[rotasSolucao[i][nRotas]];
            nRotas = rotasSolucao[i][nRotas];
        }
        System.out.println("A quilometragem de cada caminhão é: " + Arrays.toString(caminhoes));
    }
    
    static int solve(int caminhoes, int nRotas) {
        if (resultadosInter[caminhoes][nRotas] != -1) {
            return resultadosInter[caminhoes][nRotas];
        }
    
        if (caminhoes == 1) {
            return distancias[nRotas];
        }
    
        if (nRotas == 0) {
            return 0;
        }
    
        int ans = INF;
        for (int i = 1; i < nRotas; i++) {
            int temp = Math.max(solve(caminhoes - 1, i), distancias[nRotas] - distancias[i]);
            if (temp < ans) {
                ans = temp;
                rotasSolucao[caminhoes][nRotas] = i;
            }
        }
    
        resultadosInter[caminhoes][nRotas] = ans;
        return ans;
    }
    
    
    
}
