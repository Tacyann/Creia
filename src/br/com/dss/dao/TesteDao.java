package br.com.dss.dao;

public class TesteDao {

	public static void main(String[] args) {
		ArquivoDao c =new ArquivoDao();
		try {
			var a = c.ListarLotes();
			for(var item : a) {
				System.out.println(item.getProtocolo());
				for(var item2 : item.getGuia()) {
					System.out.println(item2.getBeneficiario() + item2.getCarteira());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
