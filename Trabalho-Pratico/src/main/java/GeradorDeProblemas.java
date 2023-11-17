import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeradorDeProblemas {
    static Random aleatorio = new Random(42);
    static final int TAM_BASE = 13;

    public static List<int[]> geracaoDeRotas(int quantRotas, int tamConjunto, double dispersao){
        List<int[]> conjuntoDeTeste = new ArrayList<>(tamConjunto);
        int tam_max = (int)Math.ceil(TAM_BASE * (1+dispersao));
        for(int i=0; i<tamConjunto; i++){
            int[] rotas = new int[quantRotas];
            for (int j = 0; j < quantRotas; j++) {
                rotas[j] = aleatorio.nextInt(tam_max - TAM_BASE + 1) + TAM_BASE;
            }
            conjuntoDeTeste.add(rotas);
        }
        return conjuntoDeTeste;
    }
}
