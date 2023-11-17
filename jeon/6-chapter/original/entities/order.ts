import { LineItem } from "./line-item";

export class Order {
  constructor(
    private id: number,
    private userId: number,
    private lineItems: LineItem[] = []
  ) {}

  getId(): number {
    return this.id;
  }

  getUserId(): number {
    return this.userId;
  }

  getLineItems(): LineItem[] {
    return this.lineItems;
  }

  setLineItems(lineItems: LineItem[]): void {
    this.lineItems = lineItems;
  }
}
