package com.company;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class Compra{

	private String nombreProductoC;
	private String marcaProductoC;
	private String categoriaProductoC;
	private double precioProductoC;
	private int cantidad;
	private double precioTotal;

	public Compra() {

	}
	/**
	 * @param producto
	 * @param cantidad
	 * @param precioTotal
	 */
	public Compra(Producto producto, int cantidad, double precioTotal) {
		nombreProductoC = producto.getNombreProducto();
		marcaProductoC = producto.getMarcaProducto();
		categoriaProductoC = producto.getCategoria();
		precioProductoC = producto.getPrecioProducto();
		this.cantidad = cantidad;
		this.precioTotal = precioTotal;
	}
	/**
	 * @return the producto
	 */
	public String getProducto() {
		StringBuilder sb = new StringBuilder();
		sb.append(nombreProductoC + " ");
		sb.append(marcaProductoC + " ");
		sb.append(categoriaProductoC + " ");
		sb.append(precioProductoC + " ");
		return sb.toString();
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(Producto producto) {

		nombreProductoC = producto.getNombreProducto();
		marcaProductoC = producto.getMarcaProducto();
		categoriaProductoC = producto.getCategoria();
		precioProductoC = producto.getPrecioProducto();

	}

	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the precioTotal
	 */
	public double getPrecioTotal() {
		return precioTotal;
	}

	/**
	 * @param precioTotal the precioTotal to set
	 */
	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public void precioTotal(double precioProducto , int cantidad)
	{
		precioTotal = precioProducto * cantidad;
	}

	/*
	public Producto buscaUnProducto (String nombreProducto, Supermercado mercado){
		Producto aux = new Producto();
		for (Producto producto : mercado.getListadoProductos()){
			if (producto.getNombreProducto().equals(nombreProducto) && producto.isActivo()){
				aux = producto;
			}
		}
		return aux;
	}
	*/
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		DecimalFormat formato = new DecimalFormat("#.##");
		sb.append("Nombre: " + nombreProductoC + ", ");
		sb.append("Marca: " + marcaProductoC + ", ");
		sb.append("Categoria: " + categoriaProductoC + ", ");
		sb.append("Precio: $" + formato.format(precioProductoC) + ", ");
		sb.append("Cantidad: " + cantidad + ", ");
		sb.append("Precio Final: $" + formato.format(precioTotal));
		return sb.toString();
	}

}
