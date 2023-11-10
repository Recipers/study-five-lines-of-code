import { UpperObject } from "./upper-objects";

export class Tile {
  constructor(
    private hasWeed: boolean,
    private upperObject: UpperObject | null
  ) {}

  getHasWeed() {
    return this.hasWeed;
  }

  getUpperObject() {
    return this.upperObject;
  }

  empty() {
    this.hasWeed = false;
    this.upperObject = null;
  }
}
