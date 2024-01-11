import styles from "./styles/Product.module.css";

const ProductErrorMessage: React.FC<{errorMessage: string}> = (props) => {
    return (
        <>
            {props.errorMessage && <div className={styles.info}>
                    <div className={styles.error}>
                        {props.errorMessage}
                    </div>
                </div>
            }
        </>
    );
}

export default ProductErrorMessage;