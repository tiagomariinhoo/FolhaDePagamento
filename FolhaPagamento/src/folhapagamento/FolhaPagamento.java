package folhapagamento;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FolhaPagamento {

    public static void main(String[] args) {

        Date dataSistema = new Date();

        Empregado[] emp = new Empregado[50];
        Empregado[] emp_aux3 = new Empregado[50];
        Empregado[] emp_aux2 = new Empregado[50];
        Empregado emp_aux = new Empregado();
        
        int empregados = 0;

        for (int i = 0; i < emp.length; i++) {
            emp[i] = new Empregado();
            emp_aux3[i] = new Empregado();
            emp_aux2[i] = new Empregado();
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
                System.out.println("------ ADICIONAR EMPREGADO ------");
                copiarArray(emp_aux3, emp);
                adicionarEmpregado(emp, empregados);
                empregados++;
                pos++;
                emp_aux = emp[empregados];
                unre = 1;
            } else if (op == 2) {
                System.out.println("------ REMOVER EMPREGADO ------");
                scan.nextLine();
                System.out.println("Insira o id do empregado já existente : ");
                int id;
                id = scan.nextInt();
                emp_aux3 = emp;
                if (removerEmpregado(emp, id, empregados)) {
                    empregados--;
                    unre = 1;
                }

            } else if (op == 3) {
                System.out.println("------ LANÇAR CARTAO DE PONTO ------");
                copiarArray(emp_aux3, emp);
                scan.nextLine();
                System.out.println("Insira o id do empregado já existente : ");
                int id;
                id = scan.nextInt();
                LancarCartaodePonto(emp, id, empregados, dataSistema);
                unre = 1;
            } else if (op == 4) {
                System.out.println("------ VENDAS ------");
                copiarArray(emp_aux3, emp);
                scan.nextLine();
                Vendas(emp, empregados);
                unre = 1;
            } else if (op == 5) {
                System.out.println("------ LANÇAR TAXA DE SERVIÇO ------");
                copiarArray(emp_aux3,emp);
                lancarServico(emp);
                unre = 1;
            } else if (op == 6) {
                System.out.println("------ ALTERAR EMPREGADO ------");
                copiarArray(emp_aux3, emp);
                scan.nextLine();
                System.out.println("Insira o id do empregado já existente : ");
                int id;
                id = scan.nextInt();
                alterarEmpregado(emp, id, empregados);
                unre = 1;
            } else if (op == 7) {
                System.out.println("------ LANÇAR FOLHA DE PAGAMENTO ------");
                copiarArray(emp_aux3, emp);
                folhaPagamento(dataSistema,emp);
                unre = 1;
            } else if (op == 8) {
                System.out.println("------ AGENDAR PAGAMENTO ------");
                agendaPagamento(emp);
            } else if (op == 9) {
                if (unre == 1) {
                    copiarArray(emp_aux2,emp);
                    copiarArray(emp, emp_aux3);
                    if (ult_op == 1) {
                        empregados--;
                    } else if(ult_op == 2){
                        empregados++;
                    }
                    System.out.println("Empregado : " + emp[0].nome);
                    System.out.println("Undo dado com sucesso!");
                } else {
                    copiarArray(emp,emp_aux2);
                    if(ult_op==1){
                        empregados++;
                    } else if(ult_op==2){
                        empregados--;
                    }
                    System.out.println("Empregado : " + emp[0].nome);
                    System.out.println("Id : " + emp[0].id);
                    System.out.println("Redo dado com sucesso!");
                }
                unre = 0;
            } else if (op == 10) {
                System.out.println("------ MOSTRAR EMPREGADO ------");
                scan.nextLine();
                System.out.println("Insira o id do empregado já existente : ");
                int id;
                id = scan.nextInt();
                mostrarEmpregado(emp, id, empregados);
            } else if (op == 11) {
                System.out.println("------ AGENDAR NOVO PAGAMENTO ------");
                novaAgendaPagamento(emp);
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
            emp[empregados].pagamento = "Semanal";
            
        } else if (emprego.equals("Salaried")){
            System.out.println("Insira o salario mensal : ");
            numbers = scan.nextDouble();
            emp[empregados].salario_men = numbers;
            emp[empregados].pagamento = "Mensal";
            
        } else if (emprego.equals("Commissioned")){
            System.out.println("Insira a comissão : ");
            numbers = scan.nextDouble();
            emp[empregados].comissao = numbers;
            emp[empregados].pagamento = "Bi-semanal";
        }

        emp[empregados].ponto = false;
        emp[empregados].qt_vendas = 0;
        emp[empregados].pag_def = "Default";
        
        for (int i = 0; i < 100; i++) {
            emp[empregados].venda[i] = new Venda();
        }
        
        for(int i=0;i<12;i++){
            emp[empregados].pago_mes[i] = 0;
        }
        
        for(int i=0;i<30;i++){
            emp[empregados].horas_trabalhadas[i] = 0;
        }

        System.out.println("Empregado cadastrado com sucesso!");

    }

    public static boolean removerEmpregado(Empregado emp[], int id, int empregados) {
        for (int i = 0; i < empregados; i++) {
            if (emp[i].id==id) {
                System.out.println("Empregado : " + emp[i].nome);
                emp[i] = new Empregado();
                System.out.println("Empregado removido com sucesso!");
                return true;
            }
        }
        System.out.println("Empregado não encontrado.");
        return false;
    }

    public static void mostrarEmpregado(Empregado emp[], int id, int empregados) {
        for (int i = 0; i < emp.length; i++) {
            //System.out.println("Nome atual : "  + emp[i].nome);
                if (emp[i].id==id) {
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

    public static Empregado alterarEmpregado(Empregado emp[], int id, int empregados) {
        for (int i = 0; i < empregados; i++) {
            if (emp[i].id==id) {
                int op = 8, ult_op = 0;
                Scanner scan = new Scanner(System.in);
                while (op != 0) {
                    System.out.println("Selecione o que deseja alterar do empregado " + emp[i].nome);
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
                            System.out.println("Informe a taxa sindical : ");
                            double valor = scan.nextDouble();
                            emp[i].taxa_sindical = valor;
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
                if(emp[i].pag_def.equals("Default")){
                                if (cal.get(Calendar.DAY_OF_MONTH) == 14) {
                            if (emp[i].pagamento.equals("Bi-semananal")) {
                                efetuarPagamento(emp[i],dataSistema);
                                System.out.println("Empregado " + emp[i].nome + " pago com sucesso!");
                                System.out.println("Valor recebido : " + emp[i].salario_men);
                            }

                        } else if (cal.get(Calendar.DAY_OF_MONTH) == 28) {
                                if (emp[i].pagamento.equals("Mensal") || emp[i].pagamento.equals("Bi-semanal")) {

                                if(efetuarPagamento(emp[i],dataSistema)){
                                    System.out.println("Empregado " + emp[i].nome + " pago com sucesso!");
                                    System.out.println("Foi descontado de taxa de serviço : " + emp[i].taxa_serv);

                                        if(emp[i].sindicato){
                                                System.out.println("Foi descontado de taxa sindical : " + emp[i].taxa_sindical);
                                            }
                                    System.out.println("---------------------------");
                                } else {
                                    System.out.println("O empregado " + emp[i].nome + " já recebeu esse mês!");
                                    System.out.println("---------------------------");
                                }

                            }
                        }
                        //System.out.println("Dia da semana : " + cal.get(Calendar.DAY_OF_WEEK));
                        if (cal.get(Calendar.DAY_OF_WEEK) == 6) {
                            if (emp[i].pagamento.equals("Semanal")) {
                                if(efetuarPagamento(emp[i],dataSistema)){
                                    System.out.println("Empregado : " + emp[i].nome + " pago com sucesso!");
                                } else {
                                    System.out.println("Esse empregado já recebeu nesse mês!");
                                }

                                if(cal.get(Calendar.DAY_OF_MONTH) == 28){
                                    System.out.println("Foi descontado de taxa de serviço : " + emp[i].taxa_serv);
                                    if(emp[i].sindicato){
                                        System.out.println("Foi descontado de taxa sindical : " + emp[i].taxa_sindical);
                                    }

                                }
                            }
                        }
                    
                } else if (emp[i].pag_def.equals("Mensal")){
                    if(cal.get(Calendar.DAY_OF_MONTH) == emp[i].pag_dia){
                        
                        if(efetuarPagamento(emp[i],dataSistema)){
                                    System.out.println("Empregado " + emp[i].nome + " pago com sucesso!");
                                    System.out.println("Foi descontado de taxa de serviço : " + emp[i].taxa_serv);

                                        if(emp[i].sindicato){
                                                System.out.println("Foi descontado de taxa sindical : " + emp[i].taxa_sindical);
                                            }
                                    System.out.println("---------------------------");
                                } else {
                                    System.out.println("O empregado " + emp[i].nome + " já recebeu esse mês!");
                                    System.out.println("---------------------------");
                                }
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
                    System.out.println("Empregado não encontrado.");
                }
            }
    }

    public static boolean efetuarPagamento(Empregado emp, Date dataSistema) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dataSistema);
        
        if (emp.tipo.equals("Salaried")) {
            if(emp.pago_mes[cal.get(Calendar.MONTH)]==0){
                double valor = calcPagamentoAssalariado(emp);
                emp.pago_mes[cal.get(Calendar.MONTH)] = valor ;
                System.out.println("---------------------------");
                System.out.println("Valor recebido : " + valor);
                return true;
            } else {
                System.out.println("Mes : " + cal.get(Calendar.MONTH));
                System.out.println("Pago mes : " + emp.pago_mes[cal.get(Calendar.MONTH)]);
                return false;
            }

        } else if (emp.tipo.equals("Commissioned")) {
            double valor = calcPagamentoComissionado(emp);
            emp.pago_mes[cal.get(Calendar.MONTH)] = valor ;
            
            System.out.println("Valor recebido : " + valor);
            return true;

        } else if (emp.tipo.equals("Hourly")) {
            double valor = calcPagamentoHorista(emp);
            emp.pago_mes[cal.get(Calendar.MONTH)] = valor ;
            
            System.out.println("Valor recebido : " + valor);
            return true;
        }
        return true;
    }

    public static double calcPagamentoHorista(Empregado e) {
        double sal = 0;
        for (int i = 0; i < e.horas_trabalhadas.length; i++) {
            System.out.println("Minutos trabalhados : " + e.horas_trabalhadas[i]);
                if(e.horas_trabalhadas[i]>8){
                    double falta=e.horas_trabalhadas[i]-8;
                    e.horas_trabalhadas[i]-=falta;
                    
                    sal += (e.horas_trabalhadas[i] * (e.salario_hor)) + (falta*(e.salario_hor * 1.5));
                    
                } else {
                    sal += e.horas_trabalhadas[i] * (e.salario_hor);
                }
                e.horas_trabalhadas[i]=0;
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

    public static void LancarCartaodePonto(Empregado emp[], int id, int empregados, Date dataSistema) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dataSistema);

        for (int i = 0; i < empregados; i++) {
           //if(emp[i].nome!=null){
               if (emp[i].id==id) {
                    if (!emp[i].ponto) {
                        Scanner scan = new Scanner(System.in);
                        try {
                            System.out.println("Insira o horario de entrada (dd/mm/yyyy hh:mm:ss) do empregado " + emp[i].nome);
                            String dataEntrada = scan.nextLine();
                            emp[i].entrada = DateFormat.getDateTimeInstance().parse(dataEntrada);
                            emp[i].ponto = true;

                        } catch (ParseException ex) {
                            System.out.println("Digite a data corretamente!");
                            return ;
                        }
                    } else {
                         Scanner scan = new Scanner(System.in);
                        try {
                            System.out.println("Insira o horario de saida (dd/mm/yyyy hh:mm:ss) do empregado " + emp[i].nome);
                            String dataSaida = scan.nextLine();
                            emp[i].saida = DateFormat.getDateTimeInstance().parse(dataSaida);
                            cal.setTime(emp[i].saida);

                            int minutosTrabalhados = (int) TimeUnit.MILLISECONDS.toMinutes(emp[i].saida.getTime() - emp[i].entrada.getTime());
                            emp[i].horas_trabalhadas[cal.get(Calendar.DAY_OF_MONTH)] =(double) minutosTrabalhados/60;
                            System.out.println("Horas trabalhadas do empregado " + emp[i].nome + " : " + emp[i].horas_trabalhadas[cal.get(Calendar.DAY_OF_MONTH)]);
                             System.out.println("No dia : " + cal.get(Calendar.DAY_OF_MONTH));

                            emp[i].ponto = false;
                            double total =(double) minutosTrabalhados/60;
                            System.out.println("Tempo trabalhado  : " + total);

                        } catch (ParseException ex) {
                            System.out.println("Digite a data corretamente!");
                            return ;
                        }
                    }
                } else {
                    System.out.println("Empregado não encontrado.");
                    break;
                }
           
           //}
            
        }
    }

    public static void Vendas(Empregado emp[], int empregados) {
        System.out.println("Digite o id do empregado : ");
        Scanner scan = new Scanner(System.in);
        int id;
        id = scan.nextInt();

        for (int i = 0; i < empregados; i++) {
            if (emp[i].id==id) {
                System.out.println("----------------------");
                System.out.println("1 - Registrar Venda");
                System.out.println("2 - Ver produtos vendidos por este empregado");
                int op = scan.nextInt();
                if (op == 1) {
                    RegistrarVenda(id, emp, empregados);
                } else {
                    LancarComprovanteVenda(emp, i, emp[i].qt_vendas);
                }

            } else {
                System.out.println("Empregado não encontrado.");
            }
        }

    }

    public static void RegistrarVenda(int id, Empregado emp[], int empregados) {
        Scanner scan = new Scanner(System.in);

        for (int i = 0; i < empregados; i++) {
            if (emp[i].id == id) {
                System.out.println("Empregado : " + emp[i].nome);
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
                System.out.println("Id do vendedor : " + emp[pos].id);
                System.out.println("Nome do produto : " + emp[pos].venda[i].nome);
                System.out.println("Valor do produto : " + emp[pos].venda[i].valor);
                System.out.println("Quantidade de vezes que foi dividido : " + emp[pos].venda[i].dividido);

            }
        } else {
            System.out.println("Esse empregado ainda não realizou nenhuma venda.");
        }

    }

    public static void copiarArray(Empregado emp[], Empregado emp_aux[]) {
        for (int i = 0; i < emp_aux.length; i++) {

            emp[i] = new Empregado();

            if (emp_aux[i] != null) {
                emp[i].nome = emp_aux[i].nome;
                emp[i].id = emp_aux[i].id;
                emp[i].endereco = emp_aux[i].endereco;
                emp[i].tipo = emp_aux[i].tipo;
                emp[i].pagamento = emp_aux[i].pagamento;
                emp[i].pag_def = emp_aux[i].pag_def;
                emp[i].pag_dia = emp_aux[i].pag_dia;
                emp[i].salario_hor = emp_aux[i].salario_hor;
                emp[i].salario_men = emp_aux[i].salario_men;
                emp[i].comissao = emp_aux[i].comissao;
                emp[i].taxa_serv = emp_aux[i].taxa_serv;
                emp[i].sindicato = emp_aux[i].sindicato;
                emp[i].ponto = emp_aux[i].ponto;
                emp[i].id_sindicato = emp_aux[i].id_sindicato;
                emp[i].entrada = emp_aux[i].entrada;
                emp[i].saida = emp_aux[i].saida;
                emp[i].idade = emp_aux[i].idade;
                emp[i].taxa_sindical = emp_aux[i].taxa_sindical;
                
                
                for(int j=0;j<12;j++){
                    emp[i].pago_mes[j] = emp_aux[i].pago_mes[j];
                }
                
                for(int j=0;j<30;j++){
                    emp[i].horas_trabalhadas[j] = emp_aux[j].horas_trabalhadas[j];
                }
                
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
    
    public static void lancarServico(Empregado emp[]){
        
        System.out.println("Digite o id do empregado existente : ");
        Scanner scan = new Scanner(System.in);
        int id = scan.nextInt();
        
        for(int i=0;i<emp.length;i++){
            if(emp[i].id==id){
                System.out.println("Informe o valor da taxa de serviço : ");
                double valor = scan.nextDouble();
                emp[i].taxa_serv = valor;
                System.out.println("Taxa de serviço lançada!");
                
                return ;
            }
        }
        System.out.println("Empregado não encontrado.");
    }
    
    public static void novaAgendaPagamento (Empregado emp[]){ //Funcionando apenas para o pagamento mensal
        System.out.println("Digite o id do empregado existente : ");
        Scanner scan = new Scanner(System.in);
        
        int id = scan.nextInt();
        
        for(int i=0;i<emp.length;i++){
            if(emp[i].id==id){
                System.out.println("Escolha o novo metodo de pagamento : ");
                System.out.println("1 - Mensal");
                System.out.println("2 - Bi-semanal");
                System.out.println("3 - Semanal");
                int op = scan.nextInt();
                
                    if(op==1){
                        emp[i].pag_def = "Mensal";
                        System.out.println("Digita o dia que deseja ser pago : ");
                        emp[i].pag_dia = scan.nextInt();
                    }
                
                
                return;
            }
        }
        System.out.println("Empregado não encontrado.");
    }

}
