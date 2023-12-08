import java.util.Arrays;

public class ProgramacaoDinamica {
    static int INF = Integer.MAX_VALUE;
    static int[][] teste;
    static int[] teste3;
    static int[][] teste2;

    public static void main(String[] args) {
        int[] rotas = { 40,36,38,29,32,28,31,35,31,30,32,30,29,39,35,38,39,35,32,38,32,33,29,33,29,39,28};
        int N = 3;
        
        // List<int[]> conjuntoDeTeste = GeradorDeProblemas.geracaoDeRotas(30, 10, 0.5);
        // for (int[] rotas : conjuntoDeTeste) {
        //     for(int i = 0; i < rotas.length; i++){

        //         System.out.print(rotas[i] + " ");
                
        //     }
        //     System.out.println();
            
        // }
        long startTime = System.currentTimeMillis();

        pd(rotas, N);
        
        long endTime = System.currentTimeMillis();
        
        long tempoExecucao = endTime - startTime;
        
        System.out.println("Tempo de execução: " + tempoExecucao + " milissegundos");
        
    }

    public static void pd(int[] rotas, int N) {
        Arrays.sort(rotas);
        teste3 = new int[rotas.length + 1];
        for (int i = 1; i <= rotas.length; i++) {
            teste3[i] = teste3[i - 1] + rotas[i - 1];
        }
        teste = new int[N + 1][rotas.length + 1];
        teste2 = new int[N + 1][rotas.length + 1];
        solve(N, rotas.length);

        int[] trucks = new int[N];
        int routes = rotas.length;
        for (int i = N; i > 0; i--) {
            trucks[i - 1] = teste3[routes] - teste3[teste2[i][routes]];
            routes = teste2[i][routes];
        }
        System.out.println("A quilometragem de cada caminhão é: " + Arrays.toString(trucks));

        // System.out.println("Teste:");
        // for (int i = 0; i < N + 1; i++) {
        //     for (int j = 0; j < rotas.length + 1; j++) {
        //         System.out.print(teste[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        // System.out.println("Teste2:");
        // for (int i = 0; i < N + 1; i++) {
        //     for (int j = 0; j < rotas.length + 1; j++) {
        //         System.out.print(teste2[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        // System.out.println("Teste3:");
        // for (int i = 0; i < rotas.length + 1; i++) {

        //     System.out.print(teste3[i] + " ");

        // }
    }

    static int solve(int trucks, int routes) {
        if (trucks == 1) {
            return teste3[routes];
        }
        if (routes == 0) {
            return 0;
        }
        if (teste[trucks][routes] != 0) {
            return teste[trucks][routes];
        }
        int ans = INF;
        for (int i = 0; i < routes; i++) {
            int temp = Math.max(solve(trucks - 1, i), teste3[routes] - teste3[i]);
            if (temp < ans) {
                ans = temp;
                teste2[trucks][routes] = i;
            }
        }
        return teste[trucks][routes] = ans;
    }
}
