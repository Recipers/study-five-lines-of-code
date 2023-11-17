export class User {
  constructor(private id: number, private name: string, private accountBalance: number) {}

  purchase(balance: number): void {
    this.checkBalance(balance);
    this.reduceBalance(balance);
  }

  private checkBalance(balance: number): void {
    if (this.accountBalance < balance) {
      throw new Error("Insufficient balance");
    }
  }

  private reduceBalance(balance: number): void {
    this.accountBalance -= balance;
  }
}
