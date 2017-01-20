package folhapagamento;

//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.GregorianCalendar;
//import java.util.Locale;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.logging.Level;
//import java.util.logging.Logger;

public class FolhaPagamento {

    public static void main(String[] args) {

        Date dataSistema = new Date();

        Empregado[] emp = new Empregado[50];
        Empregado[] emp_aux3 = new Empregado[50];
        Empregado emp_aux = new Empregado();
        Empregado emp_aux2 = new Empregado();
        
        int empregados = 0;

        for (int i = 0; i < emp.length; i++) {
            emp[i] = new Empregado();
            emp_aux3[i] = new Empregado();
        }
        
        int op = 12, ult_op, unre = 0, pos = 1, alterado = 0;
        int contador = 0, cont_aux = 0;
        boolean problema=false;
        while (op != 0) {
            Scanner scan = new Scanner(System.in);
            do{
            System.out.println("Insira a data :");
            String data;
            data = scan.nextLine();
            try {
                dataSistema = DateFormat.getDateTimeInstance().parse(data);
                break;
            } catch (ParseException ex) {
                System.out.println("Digite a data corretamente!");
            }
            } while(true);
            
            
            
            System.out.println("------------ MENU ------------ ");
            System.out.println("1 - Adicionar empregado");
            System.out.println("2 - Remover empregado");
            System.out.println("3 - Lançar um cartão de ponto");
            System.out.println("4 - Vendas");
            System.out.println("5 - Lançar uma taxa de serviço");
            System.out.println("6 - Alterar detalhes de um empregado");
            System.out.println("7 - Rodar a folha de pagamento para hoje");
            System.out.println("8 - Agendar pagamento");
            System.out.println("9 - Undo/Redo");
            System.out.println("10 - Mostrar empregado");
            System.out.println("11 - Criar nova agenda de pagamento");
            System.out.println("0 - Sair");

            ult_op = op;
            op = scan.nextInt();

            
            if (op == 1) {
                copiarArray(emp_aux3, emp);
                adicionarEmpregado(emp, empregados);
                empregados++;
                pos++;
                emp_aux = emp[empregados];
                unre = 1;
            } else if (op == 2) {
                scan.nextLine();
                System.out.println("Insira o nome do empregado já existente : ");
                String line;
                line = scan.nextLine();
                emp_aux3 = emp;
                if (removerEmpregado(emp, line, empregados)) {
                    empregados--;
                    unre = 1;
                }

            } else if (op == 3) {
                copiarArray(emp_aux3, emp);
                scan.nextLine();
                System.out.println("Insira o nome do empregado já existente : ");
                String line;
                line = scan.nextLine();
                emp_aux3 = emp;
                LancarCartaodePonto(emp, line, empregados, dataSistema);
                unre = 1;
            } else if (op == 4) {
                copiarArray(emp_aux3, emp);
                scan.nextLine();
                Vendas(emp, empregados);
                unre = 1;
            } else if (op == 5) {

            } else if (op == 6) {
                copiarArray(emp_aux3, emp);
                scan.nextLine();
                System.out.println("Insira o nome do empregado já existente : ");
                String line;
                line = scan.nextLine();
                emp_aux2 = alterarEmpregado(emp, line, empregados);
                unre = 1;
            } else if (op == 7) {
                copiarArray(emp_aux3, emp);
                folhaPagamento(dataSistema,emp);
            } else if (op == 8) {
                agendaPagamento(emp);
            } else if (op == 9) {
                //empregados = UndoAndRedo(emp_aux3,emp_aux,empregados,ult_op,unre,alterado); //Atualizar o numero de empregados caso exclua ou adicione
                if (unre == 1) {
                    copiarArray(emp, emp_aux3);
                    if (ult_op == 1) {
                        empregados--;
                    }
                }
                unre = 0;
            } else if (op == 10) {
                scan.nextLine();
                System.out.println("Insira o nome do empregado já existente : ");
                String line;
                line = scan.nextLine();
                mostrarEmpregado(emp, line, empregados);
            } else if (op == 11) {

            } else {
                System.out.println("Obrigado por usar o programa!");
                break;
            }

        }

    }

    public static void adicionarEmpregado(Empregado emp[], int empregados) {
        String line;
        int numb;
        double numbers;
        String emprego;
        Scanner scan = new Scanner(System.in);

        System.out.println("Insira o nome do empregado : ");
        line = scan.nextLine();
        emp[empregados].nome = line;

        System.out.println("Insira o endereço : ");
        line = scan.nextLine();
        emp[empregados].endereco = line;

        System.out.println("Insira o tipo (Hourly, Salaried or Commissioned) : ");
        emprego = scan.nextLine();
        emp[empregados].tipo = emprego;

        System.out.println("Insira o id do empregado : ");
        numb = scan.nextInt();
        emp[empregados].id = numb;

        System.out.println("Insira a idade : ");
        numb = scan.nextInt();
        emp[empregados].idade = numb;
        
        if (emprego.equals("Hourly")){
            System.out.println("Insira o salario/hora : ");
            numbers = scan.nextDouble();
            emp[empregados].salario_hor = numbers;
            emp[empregados].pagamento = "Mensal";
            
        } else if (emprego.equals("Salaried")){
            System.out.println("Insira o salario mensal : ");
            numbers = scan.nextDouble();
            emp[empregados].salario_men = numbers;
            emp[empregados].pagamento = "Mensal";
            
        } else if (emprego.equals("Commissioned")){
            System.out.println("Insira a comissão : ");
            numbers = scan.nextDouble();
            emp[empregados].comissao = numbers;
            emp[empregados].pagamento = "Mensal";
        }

        emp[empregados].ponto = false;
        emp[empregados].qt_vendas = 0;

        for (int i = 0; i < 100; i++) {
            emp[empregados].venda[i] = new Venda();
        }

        System.out.println("Empregado cadastrado com sucesso!");

    }

    public static boolean removerEmpregado(Empregado emp[], String nome, int empregados) {
        for (int i = 0; i < empregados; i++) {
            if (emp[i].nome.equals(nome)) {
                emp[i] = new Empregado();
                System.out.println("Empregado removido com sucesso!");
                return true;
            }
        }
        System.out.println("Empregado não encontrado.");
        return false;
    }

    public static void mostrarEmpregado(Empregado emp[], String nome, int empregados) {
        for (int i = 0; i < empregados; i++) {
            //System.out.println("Nome atual : "  + emp[i].nome);
            if (emp[i].nome.equals(nome)) {
                System.out.println("Nome : " + emp[i].nome);
                System.out.println("Endereço : " + emp[i].endereco);
                System.out.println("Tipo : " + emp[i].tipo);
                System.out.println("Idade : " + emp[i].idade);
                System.out.println("Salario/hora : " + emp[i].salario_hor);
                System.out.println("Salario/mês : " + emp[i].salario_men);
                System.out.println("Comissão : " + emp[i].comissao);
                return;
            }
        }
        System.out.println("Empregado não encontrado.");
    }

    public static Empregado alterarEmpregado(Empregado emp[], String nome, int empregados) {
        for (int i = 0; i < empregados; i++) {
            if (emp[i].nome.equals(nome)) {
                int op = 8, ult_op = 0;
                Scanner scan = new Scanner(System.in);
                while (op != 0) {
                    System.out.println("Selecione o que deseja alterar do empregado : ");
                    System.out.println("1 - Nome");
                    System.out.println("2 - Endereco");
                    System.out.println("3 - Tipo");
                    System.out.println("4 - Metodo de pagamento");
                    System.out.println("5 - Se pertence ao sindicato");
                    System.out.println("6 - Identificação no sindicato");
                    System.out.println("7 - Taxa sindical");
                    //System.out.println("0 - Sair");

                    ult_op = op;
                    op = scan.nextInt();

                    if (op == 1) {
                        System.out.println("Digite o novo nome : ");
                        scan.nextLine();
                        String line;
                        line = scan.nextLine();
                        emp[i].nome = line;
                        System.out.println("Alterado com sucesso!");
                        return emp[i];
                    } else if (op == 2) {
                        System.out.println("Digite o novo endereço : ");
                        scan.nextLine();
                        String line;
                        line = scan.nextLine();
                        emp[i].endereco = line;
                        System.out.println("Alterado com sucesso!");
                        return emp[i];
                    } else if (op == 3) {
                        System.out.println("Escolha o novo tipo (Hourly,salaried,commissioned) : ");
                        scan.nextLine();
                        String line;
                        line = scan.nextLine();
                        emp[i].tipo = line;
                        System.out.println("Alterado com sucesso!");
                        return emp[i];
                    } else if (op == 4) {
                        System.out.println("Digite o metodo de pagamento(Mensal, Bi-semanal, Semanal) : ");
                        scan.nextLine();
                        String line;
                        line = scan.nextLine();
                        emp[i].pagamento = line;
                        System.out.println("Alterado com sucesso!");
                        return emp[i];
                    } else if (op == 5) {
                        System.out.println("0 - Sim / 1 - Não");
                        int op2;
                        op2 = scan.nextInt();
                        if (op2 == 0) {
                            emp[i].sindicato = true;
                            System.out.println("Alterado com sucesso! O empregado agora é do sindicato!");
                            return emp[i];
                        } else {
                            emp[i].sindicato = false;
                            System.out.println("Alterado com sucesso! O empregado não pertence mais ao sindicato!");
                            return emp[i];
                        }
                    } else if (op == 6) {
                        if (emp[i].sindicato) {
                            System.out.println("Digite a nova identificação no sindicato : ");
                            int number;
                            number = scan.nextInt();
                            emp[i].id_sindicato = number;
                            System.out.println("Alterado com sucesso!");
                            return emp[i];
                        } else {
                            System.out.println("Esse empregado não pertence ao sindicato!");
                            return emp[i];
                        }

                    } else if (op == 7) {
                        if (emp[i].sindicato) {
                            System.out.println("Digite a nova taxa sindical : ");
                            int number;
                            number = scan.nextInt();
                            emp[i].taxa_sindical = number;
                            System.out.println("Alterado com sucesso!");
                            return emp[i];
                        } else {
                            System.out.println("Esse empregado não pertence ao sindicato!");
                            return emp[i];
                        }

                    } else {
                        return emp[i];
                    }
                }
            }

            System.out.println("Empregado não encontrado.");
        }
        return null;
    }

    public static void folhaPagamento(Date dataSistema, Empregado emp[]) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dataSistema);

        for (int i = 0; i < emp.length; i++) {
            if(emp[i].pagamento!=null){
                  if (cal.get(Calendar.DAY_OF_MONTH) == 14) {
                    if (emp[i].pagamento.equals("Bi-semananal")) {
                        efetuarPagamento(emp[i]);
                        System.out.println("Empregado " + emp[i].nome + " pago com sucesso!");
                        System.out.println("Valor recebido : " + emp[i].salario_men);
                    }

                } else if (cal.get(Calendar.DAY_OF_MONTH) == 28) {
                        if (emp[i].pagamento.equals("Mensal") || emp[i].pagamento.equals("Bi-semanal")) {
                        
                        efetuarPagamento(emp[i]);
                        System.out.println("Empregado " + emp[i].nome + " pago com sucesso!");
                        
                    }
                }

                if (cal.get(Calendar.DAY_OF_WEEK) == 6) {
                    if (emp[i].pagamento.equals("Semanal")) {
                        efetuarPagamento(emp[i]);
                        System.out.println("Empregado : " + emp[i].nome + " pago com sucesso!");
                    }
                }
            
            
            }
            

        }

    }
    
    public static void agendaPagamento (Empregado emp[]){
        System.out.println("Digite um empregado já existente : ");
        String nome;
        Scanner scan = new Scanner (System.in);
            nome = scan.nextLine();
        for(int i=0;i<emp.length;i++){
                if(emp[i].nome.equals(nome)){
                    System.out.println("Digite o metodo desejado (Mensal, Semanal, Bi-semanal) : ");
                    nome = scan.nextLine();
                    emp[i].pagamento = nome;
                } else {
                    System.out.println("Empregado não encotnrado.");
                }
            }
    }

    public static void efetuarPagamento(Empregado emp) {
        if (emp.tipo.equals("Salaried")) {
            double valor = calcPagamentoAssalariado(emp);
            System.out.println("Valor recebido : " + valor);

        } else if (emp.tipo.equals("Commissioned")) {
            double valor = calcPagamentoComissionado(emp);
            System.out.println("Valor recebido : " + valor);

        } else if (emp.tipo.equals("Hourly")) {
            double valor = calcPagamentoHorista(emp);
            System.out.println("Valor recebido : " + valor);
        }
    }

    public static double calcPagamentoHorista(Empregado e) {
        double sal = 0;
        for (int i = 0; i < e.minutos_trabalhadas.length; i++) {
            sal += e.minutos_trabalhadas[i] * (e.salario_hor);
        }

        return sal;
    }

    public static double calcPagamentoAssalariado(Empregado e) {
        return e.salario_men;
    }

    public static double calcPagamentoComissionado(Empregado e) {
        double total = 0;
           for(int i=0;i<e.qt_vendas;i++){
               total += (e.venda[i].valor) * (e.comissao/100) ;
               System.out.println("Total : " + total);
           }
        return total;
    }

    public static void LancarCartaodePonto(Empregado emp[], String nome, int empregados, Date dataSistema) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dataSistema);

        for (int i = 0; i < empregados; i++) {
            if (emp[i].nome.equals(nome)) {
                if (!emp[i].ponto) {
                    Scanner scan = new Scanner(System.in);
                    try {
                        System.out.println("Insira o horario de entrada: (dd/mm/yyyy hh:mm:ss)");
                        String dataEntrada = scan.nextLine();
                        emp[i].entrada = DateFormat.getDateTimeInstance().parse(dataEntrada);
                        emp[i].ponto = true;

                    } catch (ParseException ex) {
                        Logger.getLogger(FolhaPagamento.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                     Scanner scan = new Scanner(System.in);
                    try {
                        System.out.println("Insira o horario de saida : (dd/mm/yyyy hh:mm:ss)");
                        String dataSaida = scan.nextLine();
                        emp[i].saida = DateFormat.getDateTimeInstance().parse(dataSaida);
                        cal.setTime(emp[i].saida);

                        int minutosTrabalhados = (int) TimeUnit.MILLISECONDS.toMinutes(emp[i].saida.getTime() - emp[i].entrada.getTime());
                        emp[i].minutos_trabalhadas[cal.get(Calendar.DAY_OF_MONTH)] = minutosTrabalhados/60;
                        System.out.println("Horas trabalhadas do empregado " + emp[i].nome + " : " + emp[i].minutos_trabalhadas[cal.get(Calendar.DAY_OF_MONTH)]);
                         System.out.println("No dia : " + cal.get(Calendar.DAY_OF_MONTH));
                         
                        emp[i].ponto = false;
                        System.out.println("Tempo trabalhado  : " + (minutosTrabalhados/60));

                    } catch (ParseException ex) {
                        Logger.getLogger(FolhaPagamento.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                System.out.println("Empregado não encontrado.");
                break;
            }
        }
    }

    public static void Vendas(Empregado emp[], int empregados) {
        System.out.println("Digite o nome do empregado : ");
        Scanner scan = new Scanner(System.in);
        String nome = scan.nextLine();

        for (int i = 0; i < empregados; i++) {
            if (emp[i].nome.equals(nome)) {
                System.out.println("----------------------");
                System.out.println("1 - Registrar Venda");
                System.out.println("2 - Ver produtos vendidos por este empregado");
                int op = scan.nextInt();
                if (op == 1) {
                    RegistrarVenda(nome, emp, empregados);
                } else {
                    LancarComprovanteVenda(emp, i, emp[i].qt_vendas);
                }

            } else {
                System.out.println("Empregado não encontrado.");
            }
        }

    }

    public static void RegistrarVenda(String nome, Empregado emp[], int empregados) {
        Scanner scan = new Scanner(System.in);

        for (int i = 0; i < empregados; i++) {
            if (emp[i].nome.equals(nome)) {
                System.out.println("Insira o nome do produto :");
                emp[i].venda[emp[i].qt_vendas].nome = scan.nextLine();
                System.out.println("Insira o valor da venda :");
                emp[i].venda[emp[i].qt_vendas].valor = scan.nextDouble();
                System.out.println("Insira a quantidade de vezes que foi dividido :");
                emp[i].venda[emp[i].qt_vendas].dividido = scan.nextInt();

                System.out.println("Venda salva com sucesso!");
                System.out.println("------------------------");
                emp[i].qt_vendas++;
            } else {
                System.out.println("Empregado não encontrado.");
            }
        }
    }

    public static void LancarComprovanteVenda(Empregado emp[], int pos, int qt_vendas) {
        if (qt_vendas > 0) {
            for (int i = 0; i < qt_vendas; i++) {
                System.out.println("----------- VENDA -----------");
                System.out.println("Nome do vendedor : " + emp[pos].nome);
                System.out.println("Nome do produto : " + emp[pos].venda[i].nome);
                System.out.println("Valor do produto : " + emp[pos].venda[i].valor);
                System.out.println("Quantidade de vezes que foi dividido : " + emp[pos].venda[i].dividido);

            }
        } else {
            System.out.println("Esse empregado ainda não realizou nenhuma venda.");
        }

    }

    public static void copiarArray(Empregado emp[], Empregado emp_aux[]) {
        for (int i = 0; i < emp.length; i++) {

            emp[i] = new Empregado();

            if (emp_aux[i] != null) {
                emp[i].nome = emp_aux[i].nome;
                emp[i].id = emp_aux[i].id;
                emp[i].endereco = emp_aux[i].endereco;
                emp[i].tipo = emp_aux[i].tipo;
                emp[i].pagamento = emp_aux[i].pagamento;
                emp[i].idade = emp_aux[i].idade;
                emp[i].taxa_sindical = emp_aux[i].taxa_sindical;
                emp[i].salario_hor = emp_aux[i].salario_hor;

                for (int j = 0; i < emp[i].qt_vendas; j++) {
                    if (emp_aux[i].venda[j] != null) {
                        emp[i].venda[j] = emp_aux[i].venda[j];
                    } else {
                        emp[i].venda[j] = new Venda();
                    }

                }
            }

        }
    }

}
