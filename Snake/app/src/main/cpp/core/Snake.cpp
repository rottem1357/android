//
// Created by rottem on 01/11/2024.
//
#include "Snake.h"

Snake::Snake() : direction(Direction::RIGHT) {
    segments.push_back({10, 10}); // Initial position in the middle of the board
}

void Snake::move() {
    auto head = segments.front();
    switch (direction) {
        case Direction::UP: head.second--; break;
        case Direction::DOWN: head.second++; break;
        case Direction::LEFT: head.first--; break;
        case Direction::RIGHT: head.first++; break;
    }
    segments.insert(segments.begin(), head);
    segments.pop_back();
}

void Snake::grow() {
    segments.push_back(segments.back()); // Add an extra segment
}

bool Snake::collidesWith(const std::pair<int, int>& position) const {
    return segments.front() == position;
}

bool Snake::collidesWithSelf() const {
    auto head = segments.front();
    return std::count(segments.begin() + 1, segments.end(), head) > 0;
}

void Snake::setDirection(Direction newDirection) {
    if (!isOppositeDirection(newDirection)) {
        direction = newDirection;
    }
}

const std::pair<int, int>& Snake::getHead() const {
    return segments.front();
}

const std::vector<std::pair<int, int>>& Snake::getSegments() const {
    return segments;
}

bool Snake::isOppositeDirection(Direction newDirection) const {
    return (direction == Direction::UP && newDirection == Direction::DOWN) ||
           (direction == Direction::DOWN && newDirection == Direction::UP) ||
           (direction == Direction::LEFT && newDirection == Direction::RIGHT) ||
           (direction == Direction::RIGHT && newDirection == Direction::LEFT);
}
