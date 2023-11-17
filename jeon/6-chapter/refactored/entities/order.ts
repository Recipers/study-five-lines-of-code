import { LineItem } from "./line-item";
import { Product } from "./product";

export class Order {
  private lineItems: LineItem[] = [];

  constructor(private id: number, private userId: number) {}

  addLineItem(product: Product, quantity: number): LineItem {
    const lineItem = product.generateLineItem(this.id, quantity);
    this.applyLineItem(lineItem);
    return lineItem;
  }

  getTotalPrice(): number {
    return this.lineItems.reduce(
      (totalPrice, lineItem) => totalPrice + lineItem.getTotalPrice(),
      0
    );
  }

  private applyLineItem(lineItem: LineItem): void {
    this.lineItems.push(lineItem);
  }
}
