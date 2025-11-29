CREATE TABLE Stats (
    "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "attack" INTEGER NOT NULL,
    "defense" INTEGER NOT NULL,
    "hit_points" INTEGER NOT NULL
);

CREATE TABLE Weapon (
    "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "name" TEXT NOT NULL,
    "attack" INTEGER NOT NULL
);

CREATE TABLE Armor (
    "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "name" TEXT NOT NULL,
    "defense" INTEGER NOT NULL
);

CREATE TABLE Helm (
    "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "name" TEXT NOT NULL,
    "hit_points" INTEGER NOT NULL 
);

CREATE TABLE Hero (
    "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "name" TEXT NOT NULL,
    "lv" INTEGER NOT NULL,
    "xp" INTEGER NOT NULL,
    "stats_id" INTEGER NOT NULL,
    "weapon_id" INTEGER,
    "armor_id" INTEGER,
    "helm_id" INTEGER,

    FOREIGN KEY("stats_id") REFERENCES Stats("id"),
    FOREIGN KEY("weapon_id") REFERENCES Weapon("id"),
    FOREIGN KEY("armor_id") REFERENCES Armor("id"),
    FOREIGN KEY("helm_id") REFERENCES Helm("id")
);