import { Product } from "./product";

export class LineItem {
  constructor(
    private productId: number,
    private orderId: number,
    private quantity: number,
    private product?: Product,
  ) {}

  getTotalPrice(): number {
    return this.product?.getTotalPrice(this.quantity) ?? 0;
  }
}
