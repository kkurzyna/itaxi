import { Product } from "../Product";

async function getProducts(): Promise<Response>{
    const response = await fetch('http://localhost:8080/products', {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' }
    });

    if (!response.ok) {
        throw Error("Couldnt get products. Please try again!")
    }

    return response;   
}

async function deleteProduct(productId: string): Promise<Response> {
    const response = await fetch(`http://localhost:8080/products/${productId}`, {
        method: 'DELETE',
        headers: { 'Content-Type': 'application/json' }
    });

    if (!response.ok) {
        throw Error("Couldnt delete product. Please try again!")
    }

    return response;   
}

async function addProduct(name: string, price: number): Promise<Response> {
    let response;
    try {
        response = await fetch('http://localhost:8080/products', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(new Product('', name, price))
        });

    } catch (e) {
        throw Error("Couldnt add new product. Please try again!")
    }

    if (response.status === 400) {
        const errorBody = await response.json()
        throw Error(errorBody.message);
    }

    return response;   
}

export default { getProducts, deleteProduct, addProduct };