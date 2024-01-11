import { useEffect, useState } from "react";
import { Product } from "./Product";
import ProductTile  from "./ProductTile";
import ProductForm from "./ProductForm";
import styles from "./styles/Product.module.css";
import ProductErrorMessage from "./ProductErrorMessage";
import productApi from "./utils/ProductApi";

export function ProductList() {
    const [products, setProducts] = useState<Product[]>([]);
    const [errorMsg, setErrorMsg] = useState<string>("");
    const [showForm, setShowForm] = useState<boolean>(false);

    useEffect(() => {
        const apiCall = async () => {
            try {
                let response = await productApi.getProducts();
                let jsonData = await response.json()
                setProducts(jsonData)
                setErrorMsg("")
            } catch (e: any) {
                setErrorMsg(e.message)
            }
        }

        apiCall()
    }, []);

    function onDeleteEvent(product: Product) {
        const apiCall = async () => {
            try {
                await productApi.deleteProduct(product.id);
                setProducts(products.filter(prod => prod.id !== product.id))
                setErrorMsg("")
            } catch (e: any) {
                setErrorMsg(e.message)
            }
        }

        apiCall()
    }

    function onAddEvent(event: React.SyntheticEvent) {
        event.preventDefault()
        const target = event.target as typeof event.target & {
            name: { value: string };
            price: { value: number };
        };
        const apiCall = async() => {
            try {
                await productApi.addProduct(target.name.value, target.price.value);
                const productsResponse = await productApi.getProducts();
                const jsonData = await productsResponse.json()
                setProducts(jsonData)
                target.name.value = ""
                target.price.value = 0
                setErrorMsg("")
                setShowForm(false);
            } catch (e: any) {
                setErrorMsg(e.message)
            }
        }
        apiCall();
    }
    
    return (<>
        <main className={styles.productList}>
            <ProductErrorMessage errorMessage={errorMsg}/>
            {(!products || products.length == 0 )&& <div className={styles.info}><span>You have no products yet! Please add some.</span></div>}
            {products && products.map(prod => <ProductTile key={prod.id} product={prod} onDeleteEvent={() => onDeleteEvent(prod)} />)}
            <div className={styles.productListBlock} onClick={() => setShowForm(true)}>
                <p><button className={styles.addButton} ></button></p>
            </div>
            <div>
                {showForm && <ProductForm onAddEvent={onAddEvent} onClose={() => setShowForm(false)} />}
            </div>
        </main>
    </>);
}