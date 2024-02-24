import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
class Produto{
    private String nome;
    private double preco;
    private int quantidade;

    public Produto(String nome,double preco, int quantidade){
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public double getPreco(){
        return preco;
    }
    public void setPreco(double preco){
        this.preco = preco;
    }
    public int getQuantidade(){
        return quantidade;
    }
    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
    }
}
class Fatura {
    private Map<Produto, Integer> produtos;

    public Fatura() {
        this.produtos = new HashMap<>();
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        produtos.put(produto, quantidade);
    }

    public double calcularTotal() {
        double total = 0;
        for (Map.Entry<Produto, Integer> entry : produtos.entrySet()) {
            Produto produto = entry.getKey();
            int quantidade = entry.getValue();
            total += produto.getPreco() * quantidade;
        }
        return total;
    }

    public Map<Produto, Integer> getProdutos() {
        return produtos;
    }

    public void mostrarEstatisticas() {
        if (produtos.isEmpty()) {
            System.out.println("NÃO HÁ PRODUTOS PARA MOSTRAR");
            return;
        }
        double total = calcularTotal();
        double precoMedio = total / produtos.size();
        double precoMaximo = Double.MIN_VALUE;
        double precoMinimo = Double.MAX_VALUE;
        Produto produtoMaisCaro = null;
        Produto produtoMaisBarato = null;
        for (Map.Entry<Produto, Integer> entry : produtos.entrySet()) {
            Produto produto = entry.getKey();
            double preco = produto.getPreco();
            if (preco > precoMaximo) {
                precoMaximo = preco;
                produtoMaisCaro = produto;
            }
            if (preco < precoMinimo) {
                precoMinimo = preco;
                produtoMaisBarato = produto;
            }
        }

        System.out.println("ESTATÍSTICAS:");
        System.out.println("PREÇO MÉDIO: R$ " + precoMedio);
        System.out.println("PRODUTO MAIS CARO: " + produtoMaisCaro.getNome() + " - R$ " + precoMaximo);
        System.out.println("PRODUTO MAIS BARATO: " + produtoMaisBarato.getNome() + " - R$ " + precoMinimo);
    }

}
    public class Main {
        public static void main(String[] args) {


            Fatura fatura = new Fatura();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                try {
                    System.out.println("INFORME O NOME DO PRODUTO");
                    String produto = scanner.next();
                    System.out.println("INFORME O PREÇO DO PRODUTO");
                    double preco = scanner.nextDouble();
                    System.out.println("INFORME A QUANTIDADE DO PRODUTO");
                    int quantidade = scanner.nextInt();
                    Produto produtoGeral = new Produto(produto, preco, quantidade);
                    fatura.adicionarProduto(produtoGeral, quantidade);
                } catch (InputMismatchException i) {
                    System.out.println("ENTRADA INVÁLIDA, COLOQUE O VALOR CORRETO");
                    scanner.next();
                    break;
                } finally {
                    System.out.println("DESEJA ADICIONAR ALGUM PRODUTO S/N:");
                    String userInput = scanner.next().toLowerCase();
                    if (userInput.equals("n")) {
                        break;
                    }
                }
            }

            System.out.println("Fatura da Virgulino's Enterprise:");
            System.out.println("-------------------------------");
            for (Map.Entry<Produto, Integer> entry : fatura.getProdutos().entrySet()) {
                Produto produto = entry.getKey();
                int quantidade = entry.getValue();
                System.out.println("PRODUTO: " + produto.getNome());
                System.out.println("PREÇO UNITÁRIO: R$ " + produto.getPreco());
                System.out.println("QUANTIDADE: " + quantidade);
                System.out.println("SUBTOTAL: R$ " + (produto.getPreco() * quantidade));
                System.out.println("-------------------------------");
            }
            System.out.printf("Total DA FATURA: R$ %.2f", fatura.calcularTotal());
            fatura.mostrarEstatisticas();
    }
}
