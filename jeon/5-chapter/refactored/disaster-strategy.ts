import { Tile } from "./tile";

export interface DisasterStrategy {
  check(tile: Tile): boolean;
  impact(tile: Tile): void;
}

export class FireDisasterStrategy implements DisasterStrategy {
  check(tile: Tile): boolean {
    return (
      (tile.getUpperObject() === null && tile.getHasWeed()) ||
      (tile.getUpperObject() !== null && tile.getUpperObject()!.burned())
    );
  }

  impact(tile: Tile): void {
    tile.empty();
  }
}
