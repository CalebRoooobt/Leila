import java.util.ArrayList;

public class LoteMain {
    // Objetos da class
    private int numero;
    private String descricao;
    private LanceMain maiorLance;
    private ArrayList<LanceMain> lances;

    public LoteMain(int numero, String descricao) {
        this.numero = numero;
        this.descricao = descricao;
        this.lances = new ArrayList<>();
    }
    //A lista lances é inicializada no construtor da classe LoteMain e é inserida conforme novos lances são adicionados por meio do método verificaLance().
    public boolean verificaLance(LanceMain lance) {
        if ((maiorLance == null) || (lance.getValor() > maiorLance.getValor())) {
            // Este é o melhor lance até agora.
            maiorLance = lance;
            return true;
        } else {
            return false;
        }
    }
    public String toString() {
        String detalhes = numero + ": " + descricao;
        if (maiorLance != null) {
            detalhes += "    Lance: " + maiorLance.getValor();
        } else {
            detalhes += "    (Nenhum lance)";
        }
        return detalhes;
    }

    public int getNumero() {
        return numero;
    }

    public String getDescricao() {
        return descricao;
    }

    public LanceMain getMaiorLance() {
        return maiorLance;
    }

    public ArrayList<LanceMain> getLances() {
        return lances;
    }
}