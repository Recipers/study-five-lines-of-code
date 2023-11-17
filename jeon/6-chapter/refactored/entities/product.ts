import { LineItem } from "./line-item";

export class Product {
  constructor(
    private id: number,
    private name: string,
    private price: number,
    private inventories: number
  ) {}

  sell(quantity: number): void {
    this.checkInventories(quantity);
    this.reduceInventories(quantity);
  }

  getTotalPrice(quantity: number): number {
    return this.price * quantity;
  }

  generateLineItem(orderId: number, quantity: number): LineItem {
    return new LineItem(this.id, orderId, quantity, this);
  }

  private checkInventories(quantity: number): void {
    if (this.inventories < quantity) {
      throw new Error("Insufficient inventories");
    }
  }

  private reduceInventories(quantity: number): void {
    this.inventories -= quantity;
  }
}
