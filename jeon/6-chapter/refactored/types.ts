import { User } from "./entities/user";
import { Product } from "./entities/product";
import { Order } from "./entities/order";
import { LineItem } from "./entities/line-item";

export interface IUserRepository {
  findOne(id: number): User;

  save(user: User): void;
}

export interface IProductRepository {
  findOne(id: number): Product;

  saveAll(products: Product[]): void;
}

export interface IOrderRepository {
  save(order: Order): void;
}

export interface ILineItemRepository {
  saveAll(lineItems: LineItem[]): void;
}
