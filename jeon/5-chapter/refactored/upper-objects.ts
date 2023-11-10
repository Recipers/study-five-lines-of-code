export interface UpperObject {
  isTree(): boolean;
  isRock(): boolean;
  isSteel(): boolean;
  isWoodWall(): boolean;
  isRockWall(): boolean;
  isSteelWall(): boolean;
  burned(): boolean;
}

export class Tree implements UpperObject {
  isTree() { return true }
  isRock() { return false }
  isSteel() { return false }
  isWoodWall() { return false }
  isRockWall() { return false }
  isSteelWall() { return false }

  burned(): boolean { return true }
}

export class Rock implements UpperObject {
  isTree() { return false }
  isRock() { return true }
  isSteel() { return false }
  isWoodWall() { return false }
  isRockWall() { return false }
  isSteelWall() { return false }

  burned(): boolean { return false }
}

export class Steel implements UpperObject {
  isTree() { return false }
  isRock() { return false }
  isSteel() { return true }
  isWoodWall() { return false }
  isRockWall() { return false }
  isSteelWall() { return false }

  burned(): boolean { return false }
}

export class WoodWall implements UpperObject {
  isTree() { return false }
  isRock() { return false }
  isSteel() { return false }
  isWoodWall() { return true }
  isRockWall() { return false }
  isSteelWall() { return false }

  burned(): boolean { return true }
}

export class RockWall implements UpperObject {
  isTree() { return false }
  isRock() { return false }
  isSteel() { return false}
  isWoodWall() { return false }
  isRockWall() { return true }
  isSteelWall() { return false }

  burned(): boolean { return false }
}

export class SteelWall implements UpperObject {
  isTree() { return false }
  isRock() { return false }
  isSteel() { return false}
  isWoodWall() { return false }
  isRockWall() { return false }
  isSteelWall() { return true }

  burned(): boolean { return false }
}