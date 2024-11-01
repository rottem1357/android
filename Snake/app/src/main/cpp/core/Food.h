//
// Created by rottem on 01/11/2024.
//

#ifndef FOOD_H
#define FOOD_H

#include <utility>
#include "Board.h"

class Food {
public:
    Food(const Board& board); // Constructor requires Board reference
    const std::pair<int, int>& getPosition() const;
    void reposition(const Board& board);

private:
    std::pair<int, int> position;
};

#endif // FOOD_H

