package folhapagamento;

import java.util.Date;

public class Empregado {
    
        public int id;
        public String nome;
        public String endereco;
        public String tipo; //Hourly, salaried or comissioned
        public String pagamento;
        public String pag_def;
        public int pag_dia;
        public int pag_sem;
        public int dia_sem; //novaAgendaPagamento
        public int idade;
        public double taxa_sindical;
        public double salario_hor;
        public double salario_men;
        public double comissao;
        public double taxa_serv;
        public boolean sindicato;
        public boolean ponto;
        public int id_sindicato;
        public Date entrada;
        public Date saida;
        public double[] horas_trabalhadas = new double[30];
        public double[] pago_mes = new double[12];
        
        public Venda venda[] = new Venda[100];
        public int qt_vendas;
}
