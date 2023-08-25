import styles from "./cart.module.css";

export const CartList = ({
  cart,
  checkLists,
  handleQuantity,
  handelRemove,
  handleCheckList,
}) => {
  return (
    <section className={styles.cart_product_list}>
      <input
        type="checkbox"
        onChange={(e) => {
          handleCheckList(e.currentTarget.checked, cart.id);
        }}
        checked={checkLists.includes(cart.id) ? true : false}
      />
      <div className={styles.cart_product_wrap}>
        <div className={styles.cart_product_image}>
          <img src={process.env.PUBLIC_URL + cart.img} alt="menu-img" />
        </div>
        <div className={styles.cart_product_info}>
          <p className={styles.seller_store}>{cart.provider}</p>
          <p className={styles.product_name}>{cart.title}</p>
          <p className={styles.price}>
            {parseInt(cart.price) * cart.quantity}원
          </p>
        </div>
      </div>

      <div className={styles.cart_product_count}>
        <img
          className={styles.minus}
          src="/images/icon-minus-line.svg"
          alt="minus"
          onClick={() => handleQuantity("minus", cart.id, cart.quantity - 1)}
        />

        <div className={styles.count}>
          <span>{cart.quantity}</span>
        </div>
        <img
          className={styles.plus}
          src="/images/icon-plus-line.svg"
          alt="plus"
          onClick={() => handleQuantity("minus", cart.id, cart.quantity + 1)}
        />
      </div>

      <div className={styles.cart_product_price}>
        <p className={styles.total_price}></p>
        <p className={styles.price}>{parseInt(cart.price) * cart.quantity}원</p>
      </div>

      <div
        className={styles.product_remove}
        onClick={() => handelRemove(cart.id)}
      >
        <img src="/images/icon-delete.svg" alt="delete" />
      </div>
    </section>
  );
};
