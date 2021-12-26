package Clases;



public class ProductoSuplementos extends Producto {
	
	private int proteinas;
	private int calorias;
	private int grasas;
	private int hidratos;
	
	
	//el codigo del producto es IMPAR
	public ProductoSuplementos(int codigo_Producto, float precio, String nom,String urlimagen, int proteinas,
			int grasas, int hidratos, int calorias) {
		super(codigo_Producto, precio, nom,urlimagen);
		this.proteinas = proteinas;
		this.grasas = grasas;
		this.hidratos = hidratos;
		this.calorias = calorias;
	}
	
	@Override		//crear el toString para ponerlo en el txt POR HACER
	public String toString() {
		return  getNombre()+", Precio: "+getPrecio()+"€ , Proteinas: "+ proteinas+", Grasas: "+grasas+", Hidratos: "+hidratos+", Calorías: "+calorias;
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
