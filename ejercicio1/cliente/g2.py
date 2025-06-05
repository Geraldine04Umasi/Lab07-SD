from zeep import Client
from zeep.exceptions import Fault

# URL del servicio WSDL
url = 'http://localhost:1516/WS/Lab07?wsdl'

# Crea el cliente SOAP
client = Client(url)

def inicializar_productos():
    """Inicializa productos de prueba en el servicio"""
    try:
        client.service.init()
        print("Productos inicializados correctamente.")
    except Fault as e:
        print(f"Error al inicializar productos: {e}")

def listar_productos():
    try:
        # Llama al método getProducts del servicio
        response = client.service.getProducts()
        
        if not response:
            print("No hay productos disponibles.")
            return
            
        print("\n--- PRODUCTOS DISPONIBLES ---")
        for producto in response:
            id_producto = producto['id']
            nombre_producto = producto['name']
            precio_producto = producto['price']
            print(f"ID: {id_producto}, Nombre: {nombre_producto}, Precio: ${precio_producto}")
        print("------------------------------\n")
    except Fault as e:
        print(f"Error al listar productos: {e}")

def realizar_compra():
    try:
        listar_productos()
        productId = input("Ingrese el ID del producto que desea comprar: ")
        quantity = int(input("Ingrese la cantidad que desea comprar: "))
        
        # Llama al método makeSale del servicio
        sale = {'productId': productId, 'quantity': quantity}
        client.service.makeSale(sale)
        print("Compra realizada con éxito.")
        
        # Mostrar todas las ventas
        print("\n--- HISTORIAL DE VENTAS ---")
        try:
            gsale = client.service.getSales()
            if gsale:
                for venta in gsale:
                    id_producto = venta['productId']
                    cantidad = venta['quantity']
                    print(f"Producto ID: {id_producto}, Cantidad: {cantidad}")
            else:
                print("No hay ventas registradas.")
        except Fault as e:
            print(f"Error al listar ventas: {e}")
        print("---------------------------\n")
            
    except ValueError:
        print("Error: La cantidad debe ser un número entero.")
    except Fault as e:
        print(f"Error al realizar la compra: {e}")

if __name__ == "__main__":
    print("=== SISTEMA DE VENTAS SOAP ===")
    print("Conectando al servicio...")
    
    # Inicializar productos al comenzar
    inicializar_productos()
    
    # Menú interactivo
    while True:
        print("\nMenú:")
        print("1. Listar productos")
        print("2. Realizar una compra")
        print("3. Ver historial de ventas")
        print("4. Salir")
        choice = input("Seleccione una opción: ")
        
        if choice == "1":
            listar_productos()
        elif choice == "2":
            realizar_compra()
        elif choice == "3":
            try:
                ventas = client.service.getSales()
                if ventas:
                    print("\n--- HISTORIAL DE VENTAS ---")
                    for venta in ventas:
                        print(f"Producto ID: {venta['productId']}, Cantidad: {venta['quantity']}")
                    print("---------------------------\n")
                else:
                    print("No hay ventas registradas.")
            except Fault as e:
                print(f"Error al obtener ventas: {e}")
        elif choice == "4":
            print("Saliendo del programa.")
            break
        else:
            print("Opción no válida. Intente de nuevo.")