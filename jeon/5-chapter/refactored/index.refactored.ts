import { Disaster } from "./disaster";
import { FireDisasterStrategy } from "./disaster-strategy";
import { Tile } from "./tile";

export type Point = {
  x: number;
  y: number;
};

const map: Tile[][] = [];

export function strikeLightning(point: Point) {
  const disaster = new Disaster(map, new FireDisasterStrategy());

  disaster.start(point);
}
