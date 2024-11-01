//
// Created by rottem on 01/11/2024.
//
#ifndef SNAKE_H
#define SNAKE_H

#include <vector>
#include <utility>

enum class Direction { UP, DOWN, LEFT, RIGHT };

class Snake {
public:
    Snake();
    void move();
    void grow();
    bool collidesWith(const std::pair<int, int>& position) const;
    bool collidesWithSelf() const;
    void setDirection(Direction newDirection);
    const std::pair<int, int>& getHead() const;  // New method to get the head position
    const std::vector<std::pair<int, int>>& getSegments() const;

private:
    std::vector<std::pair<int, int>> segments; // Each segment is a coordinate (x, y)
    Direction direction;
    bool isOppositeDirection(Direction newDirection) const;
};

#endif // SNAKE_H


