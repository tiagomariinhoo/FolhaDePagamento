package folhapagamento;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;

public class FolhaPagamento {
    
    public static class Empregado{
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
        String inicio;
        String fim;
    }
           
    public static void main(String[] args) {
        
        Empregado[] emp = new Empregado[50];
        Scanner scan = new Scanner(System.in);
        int empregados=0;
        
        for(int i=0;i<emp.length;i++){
            emp[i] = new Empregado();
        }
        
        int op=12;
       while(op!=0){ 
        System.out.println("------------ MENU ------------ ");
        System.out.println("1 - Adicionar empregado");
        System.out.println("2 - Remover empregado");
        System.out.println("3 - Lançar um cartão de ponto");
        System.out.println("4 - Lançar um resultado venda");
        System.out.println("5 - Lançar uma taxa de serviço");
        System.out.println("6 - Alterar detalhes de um empregado");
        System.out.println("7 - Rodar a folha de pagamento para hojes");
        System.out.println("8 - Agendar pagamento");
        System.out.println("9 - Undo/Redo");
        System.out.println("10 - Mostrar empregado");
        System.out.println("11 - Criar nova agenda de pagamento");
        System.out.println("0 - Sair");
        
        op = scan.nextInt();
        
            if(op==1){
                adicionarEmpregado(emp,empregados);
                empregados++;
            } else if (op==2){
                scan.nextLine();
                System.out.println("Insira o nome do empregado já existente : ");
                String line;
                line = scan.nextLine();
                
                    if(removerEmpregado(emp,line,empregados)){
                        empregados--;
                    }
                
            } else if (op==3){
                scan.nextLine();
                System.out.println("Insira o nome do empregado já existente : ");
                String line;
                line = scan.nextLine();
                LancarCartaodePonto(emp,line,empregados);
                
            } else if (op==4){
                
            } else if (op==5){
                
            } else if (op==6){
                scan.nextLine();
                System.out.println("Insira o nome do empregado já existente : ");
                String line;
                line = scan.nextLine();
                alterarEmpregado(emp,line,empregados);
            } else if (op==7){
                
            } else if (op==8){
                
            } else if (op==9){
                
            } else if (op==10){
                scan.nextLine();
                System.out.println("Insira o nome do empregado já existente : ");
                String line;
                line = scan.nextLine();
                mostrarEmpregado(emp,line,empregados);
            } else if (op==11){
            
            } else {
                System.out.println("Obrigado por usar o programa!");
                break;
            }
        
       }
    
    
    
    }
    
    public static void adicionarEmpregado(Empregado emp[], int empregados){
            String line;
            int idade;
            double numbers;
            Scanner scan = new Scanner(System.in);
            
            System.out.println("Insira o nome do empregado : ");
            line = scan.nextLine();
            emp[empregados].nome = line;
            
            System.out.println("Insira o endereço : ");
            line = scan.nextLine();
            emp[empregados].endereco = line;
            
            System.out.println("Insira o tipo (Hourly, salaried or commissioned) : ");
            line = scan.nextLine();
            emp[empregados].tipo = line;
            
            System.out.println("Insira a idade : ");
            idade = scan.nextInt();
            emp[empregados].idade = idade;
            
            System.out.println("Insira o salario/hora : ");
            numbers = scan.nextDouble();
            emp[empregados].salario_hor = numbers;
            
            System.out.println("Insira o salario mensal : ");
            numbers = scan.nextDouble();
            emp[empregados].salario_men = numbers;
            
            System.out.println("Insira a comissão : ");
            numbers = scan.nextDouble();
            emp[empregados].comissao = numbers;
            
            emp[empregados].ponto=false;
            
            System.out.println("Empregado cadastrado com sucesso!");
        
    }
    
    public static boolean removerEmpregado(Empregado emp[], String nome, int empregados){
        for(int i=0;i<empregados;i++){
            if(emp[i].nome.equals(nome)){
                emp[i]= new Empregado();
                System.out.println("Empregado removido com sucesso!");
                return true;
            }
        }
        System.out.println("Empregado não encontrado.");
        return false;
    }
    
    public static void mostrarEmpregado (Empregado emp[], String nome, int empregados){
        for(int i=0;i<empregados;i++){
            System.out.println("Nome atual : "  + emp[i].nome);
            if(emp[i].nome.equals(nome)){
                System.out.println("Nome : " + emp[i].nome);
                System.out.println("Endereço : " + emp[i].endereco);
                System.out.println("Tipo : " + emp[i].tipo);
                System.out.println("Idade : " + emp[i].idade);
                System.out.println("Salario/hora : " + emp[i].salario_hor);
                System.out.println("Salario/mês : " + emp[i].salario_men);
                System.out.println("Comissão : " + emp[i].comissao);
                return ;
            }
        }
        System.out.println("Empregado não encontrado.");
    }
    
    public static void alterarEmpregado (Empregado emp[], String nome, int empregados){
        for(int i=0;i<empregados;i++){
            if(emp[i].nome.equals(nome)){
                int op=1;
                Scanner scan = new Scanner(System.in);
                    while(op!=0){
                            System.out.println("Selecione o que deseja alterar do empregado : ");
                            System.out.println("1 - Nome");
                            System.out.println("2 - Endereco");
                            System.out.println("3 - Tipo");
                            System.out.println("4 - Metodo de pagamento");
                            System.out.println("5 - Se pertence ao sindicato");
                            System.out.println("6 - Identificação no sindicato");
                            System.out.println("7 - Taxa sindical");
                            System.out.println("0 - Sair");
                            
                            op = scan.nextInt();
                            
                            if(op==1){
                                System.out.println("Digite o novo nome : ");
                                scan.nextLine();
                                String line;
                                line = scan.nextLine();
                                emp[i].nome = line;
                                System.out.println("Alterado com sucesso!");
                            } else if (op==2){
                                System.out.println("Digite o novo endereço : ");
                                scan.nextLine();
                                String line;
                                line = scan.nextLine();
                                emp[i].endereco = line;
                                System.out.println("Alterado com sucesso!");
                            } else if (op==3){
                                System.out.println("Escolha o novo tipo (Hourly,salaried,commissioned) : ");
                                scan.nextLine();
                                String line;
                                line = scan.nextLine();
                                emp[i].tipo = line;
                                System.out.println("Alterado com sucesso!");
                            } else if (op==4){
                                System.out.println("Digite o metodo de pagamento : ");
                                scan.nextLine();
                                String line;
                                line = scan.nextLine();
                                emp[i].pagamento = line;
                                System.out.println("Alterado com sucesso!");
                            } else if (op==5){
                                System.out.println("0 - Sim / 1 - Não");
                                int op2;
                                op2 = scan.nextInt();
                                    if(op2==0){
                                        emp[i].sindicato=true;
                                        System.out.println("Alterado com sucesso!");
                                    } else {
                                        emp[i].sindicato=false;
                                        System.out.println("Alterado com sucesso!");
                                    }
                            } else if (op==6){
                                    if(emp[i].sindicato){
                                        System.out.println("Digite a nova identificação no sindicato : ");
                                        int number;
                                        number = scan.nextInt();
                                        emp[i].id_sindicato = number;
                                        System.out.println("Alterado com sucesso!");
                                    } else {
                                        System.out.println("Esse empregado não pertence ao sindicato!");
                                    }

                            } else if (op==7){
                                    if(emp[i].sindicato){
                                        System.out.println("Digite a nova taxa sindical : ");
                                        int number;
                                        number = scan.nextInt();
                                        emp[i].taxa_sindical = number;
                                        System.out.println("Alterado com sucesso!");
                                    } else {
                                        System.out.println("Esse empregado não pertence ao sindicato!");
                                    }
                                
                            } else {
                                return ;
                            }
                    }
            }
        
        System.out.println("Empregado não encontrado.");
        }
    }
    
    public static void LancarCartaodePonto(Empregado emp[], String nome, int empregados){
        for(int i=0;i<empregados;i++){
            if(emp[i].nome.equals(nome)){
                if(!emp[i].ponto){
                    Locale locale = new Locale("pt","BR");
                    GregorianCalendar calendar = new GregorianCalendar();
                    SimpleDateFormat pux = new SimpleDateFormat ("dd' de 'MMMMM' de 'yyyy' - 'HH':'mm'h'",locale);
                    emp[i].inicio = pux.format(calendar.getTime());
                    System.out.println("Inicio : " + emp[i].inicio);
                    return ;
                } else {
                    Locale locale = new Locale("pt","BR");
                    GregorianCalendar calendar = new GregorianCalendar();
                    SimpleDateFormat pux = new SimpleDateFormat ("dd' de 'MMMMM' de 'yyyy' - 'HH':'mm'h'",locale);
                    emp[i].fim = pux.format(calendar.getTime());
                    System.out.println("Fim : " + emp[i].fim);
                    return ;
                }
                
            }
        }
        System.out.println("Empregado não encontrado.");
    }
    
}
