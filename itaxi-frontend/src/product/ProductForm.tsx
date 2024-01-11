import React from "react";
import styles from "./styles/Product.module.css";

const ProductForm : React.FC<{onAddEvent:  React.FormEventHandler<HTMLFormElement>, onClose: any}> = (props) => {
    return (<div className={styles.modal} >
            <form className={styles.addProduct} onSubmit={props.onAddEvent}>
                <div className={styles.formField}>
                    <label htmlFor="name">Name: </label>
                    <input id="name" name="name" required></input>
                </div>
                <div className={styles.formField}>
                    <label htmlFor="price">Price: </label>
                    <input id="price" name="price" type="number" min="0.01" step="any" defaultValue={0}></input>
                </div>
                <div className={styles.formButtonsField}>
                    <button className={styles.cancelButton} onClick={props.onClose}> <span>Cancel</span></button>
                    <button className={styles.okButton}><span>Add product</span></button>
                </div>
            </form>
        </div>
        );
}

export default ProductForm;