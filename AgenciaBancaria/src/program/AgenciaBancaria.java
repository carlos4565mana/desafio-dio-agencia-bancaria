package program;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import utils.Utils;


public class AgenciaBancaria {
	static Scanner input = new Scanner(System.in);
	static ArrayList<Conta> contasBancarias;

	public static void main(String[] args) {
		
		contasBancarias = new ArrayList<Conta>();
		operacoes();

	}

	private static void operacoes() {
		
		System.out.println("------------------------------------------------------");
		System.out.println("-------------Bem vindos a nossa Agência---------------");
		System.out.println("------------------------------------------------------");
		System.out.println("***** Selecione uma operação que deseja realizar *****");
		System.out.println("------------------------------------------------------");
		System.out.println("|               Opção 1 - Criar conta                 |");
		System.out.println("|               Opção 2 - Deposito                    |");
		System.out.println("|               Opção 3 - Saque                       |");
		System.out.println("|               Opção 4 - Saldo                       |");
		System.out.println("|               Opção 5 - Transferencia               |");
		System.out.println("|               Opção 6 - Listagem das Contas         |");
		System.out.println("|               Opção 7 - Sair                        |");
		System.out.println("=======================================================");
		
		int operacao = input.nextInt();
		
		switch(operacao) {
		case 1:
			criarConta();
			break;
		
		case 2:
			depositar();
			break;
			
		case 3:
			sacar();
			
			break;
		
		case 4:
			saldo();
			
			break;
			
			
		case 5:
			tranfererir();
			break;
			
			
		case 6:
			listagem();
			break;
			
		case 7:
			
			System.out.println("Saindo do Sistema.......");
			System.exit(0);
		
		default:
			System.out.println("Opção inválida!");
			operacoes();
			break;
			
		}
		
	}

	

	

	private static void criarConta() {
		System.out.println("--------------------------------------------------------------------------------------------------");
		System.out.println("------Para fazer a abertura da conta é necessário um deposito inicial de R$ 1000,00 ou mais: -----");
		System.out.println("--------------------------------------------------------------------------------------------------");
		
		double deposito = 0;
		String nome;
		String agencia = null;
		String cpf = null;
		int tentativas = 0;
		boolean teste = false;
		
		while(true) {
			try {
				System.out.println("\nDigite o Valor de Deposito: ");
				deposito = input.nextDouble();
				if (deposito < 1000) {
					System.out.println("É necessário um deposito maior ou igual a R$ 1000,00.");
					operacoes();
				}
				
			}catch(InputMismatchException err){
				System.out.println("Erro! O valor digitado não é válido. Tente novamente!");
				input.nextLine();
                continue;
			}
			break;
		}
		
		
		
		System.out.println("\nDigite o nome do cliente: ");
		nome = input.next();
		while(teste == false) {
			System.out.println("Digite o nome da agencia: ");
			 agencia = input.next();
			teste = agencia.matches("\\d{3}-\\d{1}");
			if(!teste) {
				tentativas++;
				if(tentativas > 2)operacoes();
			}
		}
	
		
		teste = false;
		tentativas = 0;
		while(!teste) {
			System.out.println("\nDigite um cpf válido!");
			cpf = input.next();
			teste = Utils.validarCpf(cpf);
			if(!teste) {
				tentativas++;
				if(tentativas > 2)operacoes();
			}
		}
		
		System.out.println("\nDigite a senha: ");
		String senha = input.next();
		
		Pessoa cliente = new Pessoa(nome, cpf, senha);
		
		Conta conta = new Conta(cliente);
		
		conta.setAgencia(agencia);
		conta.depositar(deposito);
		contasBancarias.add(conta);
		double saldo = conta.getSaldo();
		
		System.out.printf("Olá %s, obrigado por criar uma conta em nosso banco, sua agência é %s, conta %d \ne seu saldo %.2f já está disponível para saque ",nome, agencia, conta.getNumeroConta(), saldo );
		
		operacoes();
		
	}
	
	
	
	private static void depositar() {
		int numeroConta = 0;
		System.out.println("Digite o nome da agência: ");
		String agencia = input.next();
		
		
		while(true) {
			try {
				
				System.out.println("Digite o Número da Conta: ");
				numeroConta = input.nextInt();
				
			} catch (InputMismatchException  e) {
				
				System.out.println("Erro! O valor digitado não é válido. Tente novamente!");
				input.nextLine();
                continue;
				
			}
			break;
		}
		
		
		Conta conta = encontrarConta(numeroConta, agencia);

		if (conta != null) {
			System.out.println("Qual valor deseja depositar? ");
			Double valorDeposito = input.nextDouble();

			conta.depositar(valorDeposito);
		} else {
			System.out.println("--- Conta não encontrada ---");
		}

		operacoes();
		
	}
	
	private static void sacar() {
		
		System.out.println("Digite o nome da agência: ");
		String agencia = input.next();
		
		System.out.println("\nDigite o Número da Conta: ");
		int numeroConta = input.nextInt();
		
		Conta conta = encontrarConta(numeroConta, agencia);
		if(conta == null) {
			System.out.println("conta não encontrada!");
			operacoes();
		}
		
		System.out.println("Conta = "+conta.getNumeroConta());
		System.out.println("Nome = "+conta.getPessoa().getNome());
		
		System.out.println("Digite o valor do saque: ");
		double valorSaque = input.nextDouble();
		
		System.out.println("\nDigite a senha: ");
		String senha= input.next();
		
		if (senha.equals(conta.getPessoa().getSenha())) {
			
			if (valorSaque <= conta.getSaldo()) {
				conta.sacar(valorSaque);
				operacoes();
			} else {
				System.out.println("Valor indisponivel!");
				operacoes();
			}
		} else {
			System.out.println("Senha incorreta!");
			operacoes();
		}
		
		
	}
	
	
	private static void saldo() {
	
		int numeroConta = 0;
		System.out.println("Digite o nome da agência: ");
		String agencia = input.next();
		
		System.out.println("Digite o Número da Conta: ");
		try {
			 numeroConta = input.nextInt();
			
		} catch (InputMismatchException err) {
			System.out.println("Erro! O valor digitado não é válido. Tente novamente!");
			operacoes();
		}
		
		
		System.out.println("Digite a Senha: ");
		String senha = input.next();
		
		Conta conta = encontrarConta(numeroConta, agencia);
		if (conta != null) {
			if (senha.equals(conta.getPessoa().getSenha())) {
				System.out.println("Conta = "+ conta.getNumeroConta());
				System.out.println("Nome  = "+ conta.getPessoa().getNome());
				System.out.println("Saldo = "+ conta.getSaldo());

			} else {
				System.out.println("Senha incorreta!!!");
				operacoes();
			}
		} else {
			System.out.println("Conta não encontrada!");
		}
		operacoes();
		
	}


	private static Conta encontrarConta(int numeroConta, String agencia) {
		
		Conta conta = null;
		if (contasBancarias.size() > 0) {
			for (Conta cont : contasBancarias) {
				if (cont.getNumeroConta() == numeroConta && cont.getAgencia().equals(agencia))  {
					conta = cont;
				}
			}
		}
		return conta;
		
	}
	
	private static void tranfererir() {
		
		System.out.println("\nDigite o número da conta para transferência:");
		int numeroContaRemetente = input.nextInt();
		
		System.out.println("Digite o nome da agência: ");
		String agencia = input.next();
		
        Conta contaRemetente = encontrarConta(numeroContaRemetente, agencia);
        if(contaRemetente != null) {
        	
        	System.out.println("\nDigite o número da conta do destinatário: ");
        	int numeroContaDestinatario = input.nextInt();
        	System.out.println("Digite o nome da agência: ");
    		String agenciaDestinatario = input.next();
        	
        	Conta contaDestinatario = encontrarConta(numeroContaDestinatario, agenciaDestinatario);
        	if(contaDestinatario != null) {
        		System.out.println("Valor da transferência: ");
                Double valor = input.nextDouble();
                
                System.out.println("Digite a senha:");
                String senha = input.next();
                
                if(senha.equals(contaDestinatario.getPessoa().getSenha())) {
                	contaDestinatario.transferir(contaRemetente, valor);
                }
        	}

        	
        }else {
        	 System.out.println("--- Conta para transferência não encontrada ---");
        	
        }
		operacoes();

	}
	
	
	private static void listagem() {
		
		System.out.println("Digite a senha de administrador!");
		String senha = input.next();
		
		if(senha.equals("admin")) {
			if (contasBancarias.size() > 0) {
				for (Conta conta : contasBancarias) {
					System.out.println(conta);
				}
			} else {
				System.out.println("--- Não há contas cadastradas ---");
			}
		}else {
			System.out.println("Você não tem privilégio de  administrador!");
		}
		
		operacoes();
		
	}

}
