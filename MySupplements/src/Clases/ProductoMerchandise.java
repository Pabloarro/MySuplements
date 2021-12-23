package Clases;

public class ProductoMerchandise extends Producto {
	
		
		protected EnumTallas talla;//Al realizar el pedido hay que elegir la talla.POR HACER
		private String material ;
		
		
		//EL CODIGO ES PAR
		
		public ProductoMerchandise(int codigo_Producto, float precio, String nom,String urlimagen,String material) {
			super(codigo_Producto, precio, nom,urlimagen);
			this.talla = null;
			this.material = material;
		}
		
		@Override			//CREAR EL TOSTRING PARA EL FICHERO POR HACER
		public String toString() {
			return "ProductoMerchandise [talla=" + talla + ", material=" + material + "]";
		}
		public EnumTallas getTalla() {
			return talla;
		}
		public void setTalla(EnumTallas talla) {
			this.talla = talla;
		}
		public String getMaterial() {
			return material;
		}
		public void setMaterial(String material) {
			this.material = material;
		}
		
		
}
