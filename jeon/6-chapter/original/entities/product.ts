export class Product {
  constructor(private id: number, private name: string, private price: number, private inventories: number) {}

  getId(): number {
    return this.id;
  }

  getName(): string {
    return this.name;
  }

  getPrice(): number {
    return this.price;
  }

  getInventories(): number {
    return this.inventories;
  }

  setInventories(inventories: number): void {
    this.inventories = inventories;
  }
}
