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
		System.out.println("\nDigite o Valor de Deposito: ");
		double deposito = 0;
		String nome;
		String agencia = null;
		String cpf = null;
		int tentativas = 0;
		boolean teste = false;
		
		try {
			deposito = input.nextDouble();
			
			if (deposito < 1000) {
				System.out.println("É necessário um deposito maior ou igual a R$ 1000,00.");
				operacoes();
			}
			System.out.println(deposito);
			
		}catch(InputMismatchException err){
			System.out.println("Erro! O valor digitado não é válido. Tente novamente!");
			operacoes();
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
		
		System.out.println("\nDigite a semha: ");
		String senha = input.next();
		
		Pessoa cliente = new Pessoa(nome, cpf, senha);
		
		Conta conta = new Conta(cliente);
		
		conta.setAgencia(agencia);
		conta.depositar(deposito);
		contasBancarias.add(conta);
		
		System.out.println(conta);
		
		operacoes();
		
	}
	
	
	
	private static void depositar() {
		
		System.out.println("Digite o nome da agência: ");
		String agencia = input.next();
		

		System.out.println("Digite o Número da Conta: ");
		int numeroConta = input.nextInt();
		
		
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
		

	}
	
	
	private static void listagem() {
		
		
	}

}
