export class LineItem {
  constructor(
    private productId: number,
    private orderId: number,
    private quantity: number,
  ) {}

  getProductId(): number {
    return this.productId;
  }

  getOrderId(): number {
    return this.orderId;
  }

  getQuantity(): number {
    return this.quantity;
  }
}
