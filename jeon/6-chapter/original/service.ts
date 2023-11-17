import { LineItem } from "./entities/line-item";
import { Order } from "./entities/order";
import { Product } from "./entities/product";
import {
  ILineItemRepository,
  IOrderRepository,
  IProductRepository,
  IUserRepository,
} from "./types";

export class BuyService {
  constructor(
    private userRepository: IUserRepository,
    private productRepository: IProductRepository,
    private orderRepository: IOrderRepository,
    private lineItemRepository: ILineItemRepository
  ) {}

  process(
    userId: number,
    purchases: { productId: number; quantity: number }[]
  ): Order {
    const order = new Order(123, userId);
    const user = this.userRepository.findOne(userId);

    let products: Product[] = [];
    let lineItems: LineItem[] = [];
    let totalPrice = 0;

    purchases.forEach((purchase) => {
      const product = this.productRepository.findOne(purchase.productId);

      if (product.getInventories() < purchase.quantity) {
        throw new Error("Insufficient quantity");
      }

      product.setInventories(product.getInventories() - purchase.quantity);

      products.push(product);
      lineItems.push(
        new LineItem(product.getId(), order.getId(), purchase.quantity)
      );

      totalPrice += product.getPrice() * purchase.quantity;
    });

    if (user.getAccountBalance() < totalPrice) {
      throw new Error("Insufficient balance");
    }

    user.setAccountBalance(user.getAccountBalance() - totalPrice);
    order.setLineItems(lineItems);

    this.userRepository.save(user);
    this.productRepository.saveAll(products);
    this.orderRepository.save(order);
    this.lineItemRepository.saveAll(lineItems);

    return order;
  }
}
