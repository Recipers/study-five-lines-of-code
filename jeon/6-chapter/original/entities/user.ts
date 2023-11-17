export class User {
  constructor(private id: number, private name: string, private accountBalance: number) {}

  getId(): number {
    return this.id;
  }

  getName(): string {
    return this.name;
  }

  getAccountBalance(): number {
    return this.accountBalance;
  }

  setAccountBalance(accountBalance: number): void {
    this.accountBalance = accountBalance;
  }
}
