package folhapagamento;
public class Empregado {
    
        public int id;
        public String nome;
        public String endereco;
        public String tipo; //Hourly, salaried or comissioned
        public String pagamento;
        public int idade;
        public int taxa_sindical;
        public double salario_hor;
        public double salario_men;
        public double comissao;
        public boolean sindicato;
        public boolean ponto;
        public int id_sindicato;
        /*String inicio;
        String fim;*/
        public double entrada;
        public double saida;
        public double horas_trabalhadas[] = new double[30];
        public int dias_trabalhados[] = new int[30];
        Venda venda[] = new Venda[100];
        public int qt_vendas;
}
