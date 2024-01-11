import React from "react";
import { Product } from "./Product";
import styles from "./styles/Product.module.css";

const ProductTile: React.FC<{product: Product, onDeleteEvent:  React.MouseEventHandler<HTMLButtonElement>}> = (props) => {
    return (
    <div className={styles.productListBlock}>
        <p>Name: {props.product.name}</p>
        <p>Price: {props.product.price}</p>
        <p>
            <button className={styles.deleteButton} onClick={props.onDeleteEvent}>
            </button>
        </p>
    </div>
    );
}

export default ProductTile;