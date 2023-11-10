import { DisasterStrategy } from "./disaster-strategy";
import { Point } from "./index.refactored";
import { Tile } from "./tile";

export class Disaster {
  constructor(
    private readonly map: Tile[][],
    private readonly strategy: DisasterStrategy
  ) {}

  start(point: Point) {
    const queue: Point[] = [point];
    const visited: boolean[][] = Array.from({ length: this.map.length }, () =>
      Array.from({ length: this.map[0].length }, () => false)
    );
    visited[point.x][point.y] = true;

    while (queue.length > 0) {
      const curr = queue.shift()!;

      this.spread(queue, visited, curr);
    }
  }

  private spread(queue: Point[], visited: boolean[][], curr: Point) {
    if (this.strategy.check(this.map[curr.x][curr.y])) {
      this.strategy.impact(this.map[curr.x][curr.y]);

      this.spread4Directions(queue, visited, curr);
    }
  }

  // bfs 코드를 원칙을 준수하도록 작성하는게 의미가 있을까?
  private spread4Directions(queue: Point[], visited: boolean[][], curr: Point) {
    const { x, y } = curr;

    if (x > 0 && !visited[x - 1][y]) {
      visited[x - 1][y] = true;
      queue.push({ x: x - 1, y });
    }

    if (x < this.map.length - 1 && !visited[x + 1][y]) {
      visited[x + 1][y] = true;
      queue.push({ x: x + 1, y });
    }

    if (y > 0 && !visited[x][y - 1]) {
      visited[x][y - 1] = true;
      queue.push({ x, y: y - 1 });
    }

    if (y < this.map[0].length - 1 && !visited[x][y + 1]) {
      visited[x][y + 1] = true;
      queue.push({ x, y: y + 1 });
    }
  }
}
