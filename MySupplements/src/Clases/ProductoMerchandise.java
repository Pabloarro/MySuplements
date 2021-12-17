package Clases;

public class ProductoMerchandise extends Producto {
	
		
		protected EnumTallas talla;
		private String material ;
		public ProductoMerchandise(String codigo_Producto, float precio, String nom,String urlimagen, EnumTallas talla,
				String material) {
			super(codigo_Producto, precio, nom,urlimagen);
			this.talla = talla;
			this.material = material;
		}
		
		


		public ProductoMerchandise(String codigo_Producto, float precio, String nom, String urlimagen) {
			super(codigo_Producto, precio, nom, urlimagen);
		}




		@Override
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
