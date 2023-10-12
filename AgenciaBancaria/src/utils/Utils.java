package utils;

public class Utils {

	  public static  boolean validarCpf(String cpf) {
			boolean testCpf = false;
		 	testCpf = cpf.matches("\\d{3}.\\d{3}.\\d{3}-\\d{2}");
			return testCpf;
		}
		
		 static boolean validarEmail(String email) {
			String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
			boolean result = email.matches(regex);
			return result;
		}

}
