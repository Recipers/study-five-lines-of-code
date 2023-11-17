import { LineItem } from "./entities/line-item";
import { Order } from "./entities/order";
import { Product } from "./entities/product";
import { User } from "./entities/user";
import {
  ILineItemRepository,
  IOrderRepository,
  IProductRepository,
  IUserRepository,
} from "./types";

type Purchase = { productId: number; quantity: number };

export class BuyService {
  constructor(
    private userRepository: IUserRepository,
    private productRepository: IProductRepository,
    private orderRepository: IOrderRepository,
    private lineItemRepository: ILineItemRepository
  ) {}

  process(userId: number, purchases: Purchase[]): Order {
    const order = new Order(123, userId);
    const user = this.getUser(userId);
    const products = this.getProducts(purchases);
    const lineItems = this.generateLineItems(order, purchases);

    return this.purchase(order, user, products, lineItems);
  }

  private purchase(
    order: Order,
    user: User,
    products: Product[],
    lineItems: LineItem[]
  ): Order {
    user.purchase(order.getTotalPrice());
    this.persist(user, products, order, lineItems);

    return order;
  }

  private generateLineItems(order: Order, purchases: Purchase[]): LineItem[] {
    return purchases.map((purchase) => {
      const product = this.getProduct(purchase.productId);
      const lineItem = order.addLineItem(product, purchase.quantity);

      return lineItem;
    });
  }

  private getProducts(purchases: Purchase[]): Product[] {
    return purchases.map((purchase) => {
      const product = this.productRepository.findOne(purchase.productId);
      product.sell(purchase.quantity);

      return product;
    });
  }

  private getProduct(productId: number): Product {
    const product = this.productRepository.findOne(productId);

    return product;
  }

  private getUser(userId: number): User {
    const user = this.userRepository.findOne(userId);

    return user;
  }

  private persist(
    user: User,
    products: Product[],
    order: Order,
    lineItems: LineItem[]
  ) {
    this.userRepository.save(user);
    this.productRepository.saveAll(products);
    this.orderRepository.save(order);
    this.lineItemRepository.saveAll(lineItems);
  }
}
