package Clases;



public class ProductoSuplementos extends Producto {
	
	private int proteinas;
	private int calorias;
	private int grasas;
	private int hidratos;
	
	
	
	public ProductoSuplementos(String codigo_Producto, float precio, String nom,String urlimagen, int proteinas, int calorias,
			int grasas, int hidratos) {
		super(codigo_Producto, precio, nom,urlimagen);
		this.proteinas = proteinas;
		this.calorias = calorias;
		this.grasas = grasas;
		this.hidratos = hidratos;
	}
	

	public ProductoSuplementos(String codigo_Producto, float precio, String nom, String urlimagen) {
		super(codigo_Producto, precio, nom, urlimagen);
		
	}


	@Override
	public String toString() {
		return "ProductoSuplementos [proteinas=" + proteinas + ", calorias=" + calorias + ", grasas=" + grasas
				+ ", hidratos=" + hidratos + "]";
	}
	public int getProteinas() {
		return proteinas;
	}
	public void setProteinas(int proteinas) {
		this.proteinas = proteinas;
	}
	public int getCalorias() {
		return calorias;
	}
	public void setCalorias(int calorias) {
		this.calorias = calorias;
	}
	public int getGrasas() {
		return grasas;
	}
	public void setGrasas(int grasas) {
		this.grasas = grasas;
	}
	public int getHidratos() {
		return hidratos;
	}
	public void setHidratos(int hidratos) {
		this.hidratos = hidratos;
	}
	
}
