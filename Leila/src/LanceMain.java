//A classe LanceMain representa um lance feito por um licitante em um leilão.
public class LanceMain
{
    private PessoaMain licitante;
    private long valor;

    public LanceMain(PessoaMain licitante, long valor)
    {
        this.licitante = licitante;
        this.valor = valor;
    }

    public PessoaMain getLicitante()
    {
        return licitante;
    }

    public long getValor()
    {
        return valor;
    }
}