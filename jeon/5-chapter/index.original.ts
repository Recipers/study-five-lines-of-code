enum UpperObject {
  Tree,
  Rock,
  Steel,
  WoodWall,
  RockWall,
  SteelWall,
}

type Tile = {
  hasWeed: boolean;
  upperObject: UpperObject | null;
};

const map: Tile[][] = [];

type Point = {
  x: number;
  y: number;
};

export function fire(fire: Point) {
  const queue: Point[] = [fire];
  const visited: boolean[][] = Array.from({ length: map.length }, () =>
    Array.from({ length: map[0].length }, () => false)
  );
  visited[fire.x][fire.y] = true;

  while (queue.length > 0) {
    const curr = queue.shift()!;
    const { x, y } = curr;

    if (
      map[x][y].upperObject === UpperObject.Tree ||
      map[x][y].upperObject === UpperObject.WoodWall ||
      (map[x][y].upperObject === null && !map[x][y].hasWeed)
    ) {
      map[x][y].hasWeed = false;
      map[x][y].upperObject = null;

      if (x > 0 && !visited[x - 1][y]) {
        visited[x - 1][y] = true;
        queue.push({ x: x - 1, y });
      }

      if (x < map.length - 1 && !visited[x + 1][y]) {
        visited[x + 1][y] = true;
        queue.push({ x: x + 1, y });
      }

      if (y > 0 && !visited[x][y - 1]) {
        visited[x][y - 1] = true;
        queue.push({ x, y: y - 1 });
      }

      if (y < map[0].length - 1 && !visited[x][y + 1]) {
        visited[x][y + 1] = true;
        queue.push({ x, y: y + 1 });
      }
    }
  }
}
