package com.company;

import exceptions.CargaProductoException;
import jdk.swing.interop.SwingInterOpUtils;

import java.text.DecimalFormat;
import java.util.Scanner;


public class Main {

    public static Scanner scanner;

    public static void main(String[] args){
        int opc, opcCompra;
        scanner = new Scanner(System.in);
        lecturaEscritura lye = new lecturaEscritura();
        Supermercado superMerca = new Supermercado("La Super Merca");
        lee (lye, superMerca);
        cargarAdmins(superMerca);
        System.out.println("SuperMercado " + superMerca.getNombreSupermercado());
        Usuario usr = Login(superMerca);
        Carrito<Compra>compra;
           if (usr instanceof Admin) {
               do {
                   menuAdmin();
                   opc = scanner.nextInt();
                   scanner.nextLine();
                   switch (opc) {
                       case 1:
                           try {
                               nuevoProducto(superMerca);
                           } catch (CargaProductoException e) {
                               e.getMessage();
                           }
                           break;
                       case 2:
                           System.out.println(superMerca.muestraProductos());
                           break;
                       case 3:
                           muestraPorCategoria(superMerca);
                           break;
                       case 4:
                           restockearProducto(superMerca);
                           break;
                       case 5:
                           bajaProducto(superMerca);
                           break;
                       case 6:
                           altaProducto(superMerca);
                           break;
                       case 7:
                           System.out.println(usr);
                           break;
                       case 8:
                           System.out.println(superMerca.muestraUsuarios());
                           break;
                       case 9:
                           buscaUsuario(superMerca);
                           break;
                       case 10:
                           bajaCliente(superMerca);
                           break;
                       case 11:
                           altaCliente(superMerca);
                           break;
                   }
               } while (opc != 0);
           } else if (usr instanceof Cliente) {
               do {
                   menuCliente();
                   opc = scanner.nextInt();
                   scanner.nextLine();
                   switch (opc) {
                       case 1:
                           modificarDatos(superMerca, usr);
                           break;
                       case 2:
                           System.out.println(usr);
                           break;
                       case 3:
                           compra = new Carrito<>();
                           do {
                               menuCompra();
                               opcCompra = scanner.nextInt();
                               scanner.nextLine();
                               switch (opcCompra) {
                                   case 1:
                                       System.out.println(superMerca.muestraProductosParaCliente());
                                       break;
                                   case 2:
                                       muestraPorCategoria(superMerca);
                                       break;
                                   case 3:
                                        muestraProducto(superMerca);
                                       break;
                                   case 4:
                                       compra = realizarCompra(superMerca, compra);
                                       break;
                                   case 5:
                                       System.out.println(compra);
                                       break;
                                   case 6:
                                       if (finCompra(compra, usr)){
                                           compra = new Carrito<>();
                                       }
                                       break;
                                   case 7:
                                       compra = cancelarCompra();
                                       System.out.println("COMPRA CANCELADA");
                                       break;
                                   case 8:
                                       System.out.println(((Cliente) usr).muestraHistorialCompras());
                                       break;
                               }
                           } while (opcCompra != 0);
                           break;
                       case 4:
                           bajaClienteCliente (superMerca, lye);
                           break;
                       case 5:
                           comentar(superMerca);
                           break;
                   }
               } while (opc != 0);
           }
            graba(lye, superMerca);
    }

    public static void graba(lecturaEscritura lye, Supermercado superMerca){
        lye.grabaClientes(superMerca.getUsuarios());
        lye.grabaProductos(superMerca.getListadoProductos());
    }

    public static void lee (lecturaEscritura lye, Supermercado superMerca){
        superMerca.setUsuarios(lye.leeClientes());
        superMerca.setListadoProductos(lye.leeProductos());
    }

    public static void menuAdmin (){
        System.out.println("MENU ADMINISTRADOR: ");
        System.out.println("1. AGREGA PRODUCTO");
        System.out.println("2. VER LISTA PRODUCTOS");
        System.out.println("3. VER LISTA PRODUCTOS POR CATEGORIA");
        System.out.println("4. RENOVAR STOCK DE UN PRODUCTO");
        System.out.println("5. DAR DE BAJA UN PRODUCTO");
        System.out.println("6. DAR DE ALTA UN PRODUCTO");
        System.out.println("7. VER USUARIO ADMINISTRADOR");
        System.out.println("8. VER LISTA CLIENTES");
        System.out.println("9. BUSCAR CLIENTE");
        System.out.println("10. DAR DE BAJA UN CLIENTE");
        System.out.println("11. DAR DE ALTA UN CLIENTE");
        System.out.println("0. SALIR");
        System.out.println("SELECCIONE UNA OPCION: ");
    }

    public static void menuCliente (){
        System.out.println("MENU CLIENTE");
        System.out.println("1. MODIFICAR DATOS PERSONALES");
        System.out.println("2. VER CLIENTE ACTUAL");
        System.out.println("3. COMPRAR");
        System.out.println("4. DAR DE BAJA LA CUENTA");
        System.out.println("6. DEJAR UN COMENTARIO DEL PRODUCTO");
        System.out.println("0. SALIR");
        System.out.println("SELECCIONE UNA OPCION: ");
    }

    public static void menuCompra (){
        System.out.println("MENU COMPRA");
        System.out.println("1. VER LISTA PRODUCTOS");
        System.out.println("2. VER LISTA DE PRODUCTOS POR CATEGORIA");
        System.out.println("3. VER UN PRODUCTO");
        System.out.println("4. CARGAR PRODUCTO AL CARRITO");
        System.out.println("5. VER CARRITO");
        System.out.println("6. FINALIZAR PEDIDO");
        System.out.println("7. CANCELAR PEDIDO");
        System.out.println("8. VER HISTORIAL DE COMPRA");///SE PODRIA MODIFICAR
        System.out.println("0. ATRAS");
        System.out.println("SELECCIONE UNA OPCION: ");
    }

    public static Usuario Login (Supermercado mercado) {
        String usuario, contrasena;
        char opc;
        Usuario usr;
        System.out.println("Login");
        System.out.println("Usuario");
        usuario = scanner.nextLine();
        System.out.println("Contraseña");
        contrasena = scanner.nextLine();
        usr = mercado.buscarUsuarioLogin(usuario, contrasena);
        if (usr != null) {
            if (usr instanceof Cliente){
                if (!((Cliente) usr).getActivo()){
                    System.out.println("EL CLIENTE ESTA DADO DE BAJA");
                    System.exit(1);
                }
            }
            System.out.println("Logeado con exito!");
            System.out.println("Bienvenido " + usr.getNombre());
        } else {
            System.out.println("Usuario o Contraseña incorrectos!!");
            do {
                System.out.println("Desea crear un nuevo usuario? S/N");
                opc = scanner.nextLine().charAt(0);
                if (opc == 's' || opc == 'S') {
                    usr = nuevoCliente(mercado);
                    mercado.nuevoUsuario(usr);
                    System.out.println("Cliente creado con exito!");
                } else if (opc == 'n' || opc == 'N') {
                    System.out.println("Hasta pronto!");
                }
            } while (opc != 's' && opc != 'S' && opc != 'n' && opc != 'N');
        }
        return usr;
    }

    public static Usuario nuevoCliente (Supermercado mercado){
        boolean flag = false;
        String usuario;
        Cliente cliente = new Cliente();
        System.out.println("Nombre: ");
        cliente.setNombre(scanner.nextLine());
        System.out.println("Apellido: ");
        cliente.setApellido(scanner.nextLine());
        System.out.println("DNI");
        cliente.setDni(scanner.nextLine());
        do{
            System.out.println("E-Mail");
            cliente.setMailUsuario(scanner.nextLine());
            if (cliente.getMailCliente().contains("@")){
                flag = true;
            }else{
                System.out.println("Error, mail no valido, reintente...");
            }
        }while (!flag);
        System.out.println("Localidad: ");
        cliente.setLocalidadCliente(scanner.nextLine());
        System.out.println("Categoria: ");
        cliente.setCategoria(scanner.nextLine());
        do{
            System.out.println("Nombre de usuario: ");
            usuario = scanner.nextLine();
            if (mercado.buscarPorNombreUsuarioLogin(usuario)){
                System.out.println("Error, usuario ya registrado.");
            }
            else{
                cliente.setUsuario(usuario);
            }
        }while (mercado.buscarPorNombreUsuarioLogin(usuario));
        System.out.println("contraseña: ");
        cliente.setContrasena(scanner.nextLine());
        cliente.setActivo(true);
        return cliente;
    }

    public static void cargarAdmins (Supermercado mercado){
        Usuario admin1 = new Admin("Juan Ignacio", "Zappulla", "41928220", "juani99", "8914122", "Jefe");
        Usuario admin2= new Admin("Nahuel Ariel","Zamudio","41542799","nahuel98","741852","jefe2");
        mercado.nuevoUsuario(admin1);
        mercado.nuevoUsuario(admin2);
    }

    public static void nuevoProducto (Supermercado mercado) {
        Producto producto = new Producto();
        System.out.println("Nombre del producto: ");
        producto.setNombreProducto(scanner.nextLine());
        System.out.println("Marca del producto: ");
        producto.setMarcaProducto(scanner.nextLine());
        System.out.println("Precio del producto: ");
        producto.setPrecioProducto(scanner.nextDouble());
        System.out.println("Stock Actual del producto: ");
        producto.setStockProducto(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Comentario: ");
        producto.setComentario(scanner.nextLine());
        System.out.println("Categoria: ");
        producto.setCategoria(scanner.nextLine());
        mercado.nuevoProducto(producto);
    }

    public static void muestraPorCategoria(Supermercado mercado){
        String categoria;
        System.out.println("INGRESE CATEGORIA: ");
        categoria = scanner.nextLine();
        System.out.println(mercado.muestraProductosPorCategoria(categoria));
    }

    public static void muestraProducto(Supermercado mercado){
        String nombre;
        String producto;
        System.out.println("INGRESE NOMBRE DEL PRODUCTO: ");
        nombre = scanner.nextLine();
        producto = mercado.buscaUnSoloProducto(nombre);
        if (producto.length()>0){
            System.out.println(producto);
        }
        else{
            System.out.println("NO SE ENCUENTRA EL PRODUCTO O FUE DADO DE BAJA...");
        }
    }

    public static void muestraPorCategoriaCliente(Supermercado mercado){
        String categoria;
        System.out.println("INGRESE CATEGORIA: ");
        categoria = scanner.nextLine();
        System.out.println(mercado.muestraProductosPorCategoriaCliente(categoria));
    }

    public static void buscaUsuario(Supermercado mercado) {
        String usuario;
        System.out.println("INGRESE DNI DE USUARIO: ");
        usuario = scanner.nextLine();
        Usuario usr = mercado.buscarUsuario(usuario);
        if ( usr instanceof Cliente) {
            System.out.println("Id:" + usr.getId());
            System.out.println(usr);
        } else if (usr instanceof Admin) {
            System.out.println("Id:" + usr.getId());
            System.out.println(usr);
        }
    }

    public static void restockearProducto (Supermercado mercado){
        int idProducto;
        int cantidad;
        boolean flag;
        System.out.println("Ingrese id de producto: ");
        idProducto = scanner.nextInt();
        flag = mercado.BuscaProducto(idProducto);
        if (!flag){
            System.out.println("ERROR, ID INCORRECTO O SE DESACTIVÓ EL PRODUCTO");
        }
        else{
            System.out.println("Ingrese cantidad para reestockear");
            cantidad = scanner.nextInt();
            flag = mercado.restockProducto(idProducto, cantidad);
            if(!flag){
                System.out.println("ERROR, REINTENTE");
            }
            else{
                System.out.println("INGRESO DE STOCK CORRECTO");
            }
        }
    }

    public static void bajaProducto (Supermercado mercado){
        int idProducto;
        boolean flag;
        System.out.println("Ingrese id de producto: ");
        idProducto = scanner.nextInt();
        flag = mercado.bajaDeProducto(idProducto);
        if (!flag){
            System.out.println("ERROR, EL PRODUCTO NO SE ENCUENTRA O YA ESTÁ DESACTIVADO");
        }
        else{
            System.out.println("PRODUCTO DADO DE BAJA CON EXITO");
        }
    }

    public static void altaProducto (Supermercado mercado){
        int idProducto;
        boolean flag;
        if (mercado.muestraDadosDeBajaProductos().length()>0) {
            System.out.println("PRODUCTOS DADOS DE BAJA");
            System.out.println(mercado.muestraDadosDeBajaProductos());
            System.out.println("Ingrese id de producto: ");
            idProducto = scanner.nextInt();
            flag = mercado.altaDeProducto(idProducto);
            if (!flag) {
                System.out.println("ERROR, EL PRODUCTO NO SE ENCUENTRA O YA ESTÁ DADO DE ALTA");
            } else {
                System.out.println("PRODUCTO DADO DE ALTA CON EXITO");
            }
        }
        else{
            System.out.println("NO HAY PRODUCTOS DADOS DE BAJA");
        }
    }

    public static void bajaCliente (Supermercado mercado){
        String dni;
        boolean flag;
        System.out.println("Ingrese DNI de cliente: ");
        dni = scanner.nextLine();
        flag = mercado.bajaDeCliente(dni);
        if (!flag){
            System.out.println("ERROR, EL CLIENTE NO SE ENCUENTRA O YA ESTÁ DADO DE BAJA");
        }
        else{
            System.out.println("CLIENTE DADO DE BAJA CON EXITO");
        }
    }

    public static void bajaClienteCliente (Supermercado mercado, lecturaEscritura lye){
        String dni;
        boolean flag;
        System.out.println("Ingrese DNI de cliente: ");
        dni = scanner.nextLine();
        flag = mercado.bajaDeCliente(dni);
        if (!flag){
            System.out.println("ERROR, EL CLIENTE NO SE ENCUENTRA O YA ESTÁ DADO DE BAJA");
        }
        else{
            System.out.println("CLIENTE DADO DE BAJA CON EXITO");
            graba(lye, mercado);
            System.exit(1);
        }
    }

    public static void altaCliente (Supermercado mercado){
        String dni;
        boolean flag;

        if (mercado.muestraDadosDeBajaUsuarios().length()>0){
            System.out.println("CLIENTES DADOS DE BAJA");
            System.out.println(mercado.muestraDadosDeBajaUsuarios());
            System.out.println("Ingrese DNI del cliente: ");
            dni = scanner.nextLine();
            flag = mercado.altaDeCliente(dni);
            if (!flag){
                System.out.println("ERROR, EL CLIENTE NO SE ENCUENTRA O YA ESTÁ DADO DE ALTA");
            }
            else{
                System.out.println("USUARIO DADO DE ALTA CON EXITO");
            }
        }
        else{
            System.out.println("NO HAY CLIENTES DADOS DE BAJA");
        }
    }

    public static boolean finCompra(Carrito<Compra>carrito, Usuario usr){
    	int tipoPago;
    	int aux;
        DecimalFormat formato = new DecimalFormat("#.##");

        if (carrito.mostrarCarrito().length() > 0){
            do {
                System.out.println("seleccione el metodo de pago:\n 1. Efectivo\n 2. Tarjeta Credito\n 3. Tarjeta Debito\n 4. MercadoPago");
                tipoPago=scanner.nextInt();
                if(tipoPago==1)
                {
                    carrito.setTipoPago("Efectivo");
                }
                else if(tipoPago==2)
                {
                    carrito.setTipoPago("Tarjeta Credito");
                }
                else if(tipoPago==3){
                    carrito.setTipoPago("Tarjeta Debito");
                }
                else if(tipoPago==4){
                    carrito.setTipoPago("MercadoPago");
                }
                else
                {
                    System.out.println("EL TIPO DE PAGO ES INCORRECTO, REINTENTE...");
                    tipoPago=0;
                }
            }while(tipoPago==0);

            System.out.println("EL TOTAL A PAGAR ES:" + formato.format(PrecioTotalConDescuento(carrito)));
            System.out.println("PARA REALIZAR EL PAGO PRESIONE 1, DE LO CONTRARIO PRESIONE 2: ");
            aux=scanner.nextInt();
            if(aux==1)
            {
                carrito.setPago(true);
                carrito.setPrecioTotalCompra(PrecioTotalConDescuento(carrito));
                ((Cliente) usr).agregarHistorial(carrito);
                return true;
            }
            else
            {
                carrito.setPago(false);
            }
        }
        else{
            System.out.println("ERROR, DEBE CARGAR PRODUCTOS PRIMERO");
        }
        return false;
    }

	public static double PrecioTotalConDescuento(Carrito<Compra>unProducto) {
		 double total=0;
		 double aplicarDescuento=0;
		 double descuentoAplicado=0;
		 for (int i=0;i<unProducto.getLista().size();i++)
		 {
			 
			total+=unProducto.getLista().get(i).getPrecioTotal();
          
		 }
		 if (unProducto.getTipoPago().equals("Efectivo"))
		 {
			 unProducto.setDescuento(0.10);
			 aplicarDescuento = total * unProducto.getDescuento();
			 descuentoAplicado = total - aplicarDescuento;
		 }
		 else if (unProducto.getTipoPago().equals("Tarjeta Credito")){
             unProducto.setDescuento(0.15);
             aplicarDescuento = total * unProducto.getDescuento();
             descuentoAplicado = total + aplicarDescuento;
         }
		 else if (unProducto.getTipoPago().equals("Tarjeta Debito")){
             unProducto.setDescuento(0.10);
             aplicarDescuento = total * unProducto.getDescuento();
             descuentoAplicado = total - aplicarDescuento;
         }
		 else if (unProducto.getTipoPago().equals("MercadoPago")) {
             unProducto.setDescuento(0.20);
             aplicarDescuento = total * unProducto.getDescuento();
             descuentoAplicado = total + aplicarDescuento;
         }
		 else {
			 descuentoAplicado=(float)total;
		 }
		 return descuentoAplicado;
	 }

	 public static Carrito<Compra> cancelarCompra(){
        return new Carrito<>();
     }

    public static Carrito<Compra> realizarCompra(Supermercado supermercado, Carrito<Compra> carrito){
        Compra compraProducto;
        Producto producto;
        String nombreProducto;
        int cantidad;
        boolean controlStock;
        char continuar;
        do {
            System.out.println("ingrese el producto que desee comprar:");
            nombreProducto= scanner.nextLine();
            compraProducto = new Compra();
            producto = supermercado.buscarProducto(nombreProducto);
            if(producto.getNombreProducto() != null){
                System.out.println("indique la cantidad a comprar:");
                cantidad=scanner.nextInt();
                scanner.nextLine();
                controlStock = supermercado.controlStrockProducto(producto.getIdProducto(), cantidad);
                if(controlStock){
                    compraProducto.setCantidad(cantidad);
                    compraProducto.precioTotal(producto.getPrecioProducto(), cantidad);
                    compraProducto.setProducto(producto);
                    carrito.agregarCarrito(compraProducto);
                    System.out.println("cargado con exito!");
                }
                else{
                    System.out.println("NO HAY STOCK SUFICIENTE");
                }
            }
            else{
                System.out.println("EL PRODUCTO SELECCIONADO NO EXISTE O FUE DADO DE BAJA");
            }
            System.out.println("Desea continuar: S/N ");
            continuar= scanner.nextLine().charAt(0);
        }while(continuar == 's');

        return carrito;
    }

    public static void modificarDatos(Supermercado mercado, Usuario usr) {
        int opc;
        boolean flag = false;
        do {
            System.out.println("SELECCIONE EL DATO A MODIFICAR:");
            System.out.println(" 1. nombre\n 2. apellido\n 3. dni\n 4. E-Mail\n 5. localidad\n 6. categoria\n 7. idUsuario\n 8. contraseÃ±a\n 0. Atras\nELIJA UNA OPCION:");
            opc = scanner.nextInt();
            scanner.nextLine();
            System.out.println("OPCION ELEGIDA: " + opc);
            String usuario;
            switch (opc) {
                case 1:
                    System.out.println("Nombre: ");
                    usr.setNombre(scanner.nextLine());
                    break;
                case 2:
                    System.out.println("Apellido: ");
                    usr.setApellido(scanner.nextLine());
                    break;
                case 3:
                    System.out.println("DNI");
                    usr.setDni(scanner.nextLine());
                    break;
                case 4:
                    do {
                        System.out.println("E-Mail");
                        ((Cliente) usr).setMailUsuario(scanner.nextLine());
                        if (((Cliente) usr).getMailCliente().contains("@")) {
                            flag = true;
                        } else {
                            System.out.println("Error, mail no valido, reintente...");
                        }
                    } while (!flag);
                    break;
                case 5:
                    System.out.println("Localidad: ");
                    ((Cliente) usr).setLocalidadCliente(scanner.nextLine());
                    break;
                case 6:
                    System.out.println("Categoria: ");
                    ((Cliente) usr).setCategoria(scanner.nextLine());
                    break;
                case 7:
                    do {
                        System.out.println("Nombre de usuario: ");
                        usuario = scanner.nextLine();
                        if (mercado.buscarPorNombreUsuarioLogin(usuario)) {
                            System.out.println("Error, usuario ya registrado.");
                        } else {
                            usr.setUsuario(usuario);
                        }
                    } while (mercado.buscarPorNombreUsuarioLogin(usuario));
                    break;
                case 8:
                    System.out.println("contraseña: ");
                    usr.setContrasena(scanner.nextLine());
                    ((Cliente) usr).setActivo(true);
                    break;
            }
        }while (opc != 0);
    }

    public static void comentar(Supermercado mercado){
	int i;
	String nombre, comentario;
	System.out.println("ingrese el nombre del producto");
	nombre = scanner.nextLine();
	i = mercado.buscarProductoNombre(nombre);
	if (i < 0){
        System.out.println("ERROR, EL PRODUCTO NO EXISTE O FUE DADO DE BAJA...");
    }
	else{
        System.out.println("Deje su comentario");
        comentario=scanner.nextLine();
        mercado.dejarUnComentario(comentario, i);
    }

}

}
    
   

