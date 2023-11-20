import java.util.Arrays;

public class ProgramacaoDinamica {
    static int INF = 1000000007;
    static int[][] teste;
    static int[] teste3;
    static int[][] teste2;

    public static void programacaoDinamica(String[] args) {
        int[] rotas = {35, 34, 33, 23, 21, 20, 30};
        int N = 3;
        Arrays.sort(rotas);
        teste3 = new int[rotas.length+1];
        for (int i = 1; i <= rotas.length; i++) {
            teste3[i] = teste3[i-1] + rotas[i-1];
        }
        teste = new int[N+1][rotas.length+1];
        teste2 = new int[N+1][rotas.length+1];
        for (int[] row : teste) {
            Arrays.fill(row, 0);
        }
        solve(N, rotas.length);
        
        int[] trucks = new int[N];
        int routes = rotas.length;
        for (int i = N; i > 0; i--) {
            trucks[i-1] = teste3[routes] - teste3[teste2[i][routes]];
            routes = teste2[i][routes];
        }
        System.out.println("A quilometragem de cada caminhão é: " + Arrays.toString(trucks));

        for(int i = 0; i < N+1; i++){
            for(int j = 0; j < rotas.length+1; j++){
                System.out.print(teste2[i][j] + " ");
            }
            System.out.println();
        }
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
            int temp = Math.max(solve(trucks-1, i), teste3[routes] - teste3[i]);
            if (temp < ans) {
                ans = temp;
                teste2[trucks][routes] = i;
            }
        }
        return teste[trucks][routes] = ans;
    }
}
