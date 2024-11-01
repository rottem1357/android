#include "SnakeRenderer.h"
#include "core/SnakeGame.h"
#include <EGL/egl.h>

SnakeRenderer::SnakeRenderer(android_app* app)
        : app(app), screenWidth(0), screenHeight(0), cellSize(0.05f) {
    initOpenGL();
}

SnakeRenderer::~SnakeRenderer() {
    // Cleanup OpenGL resources, if any
}

void SnakeRenderer::initOpenGL() {
    // Set a black background
    glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

    // Retrieve display dimensions
    if (app->window) {
        screenWidth = ANativeWindow_getWidth(app->window);
        screenHeight = ANativeWindow_getHeight(app->window);
        glViewport(0, 0, screenWidth, screenHeight);
    }
}

void SnakeRenderer::render() {
    // Clear the screen
    glClear(GL_COLOR_BUFFER_BIT);

    // Access the SnakeGame instance from userData
    auto* snakeGame = reinterpret_cast<SnakeGame*>(app->userData);
    if (snakeGame) {
        drawSnake(snakeGame->getSnake());
        drawFood(snakeGame->getFood());
    }

    // Swap buffers
    eglSwapBuffers(eglGetCurrentDisplay(), eglGetCurrentSurface(EGL_DRAW));
}

void SnakeRenderer::drawSquare(float x, float y, float size) {
    GLfloat vertices[] = {
            x, y,
            x + size, y,
            x, y + size,
            x + size, y + size
    };

    // Set up a vertex buffer to draw the square
    GLuint vbo;
    glGenBuffers(1, &vbo);
    glBindBuffer(GL_ARRAY_BUFFER, vbo);
    glBufferData(GL_ARRAY_BUFFER, sizeof(vertices), vertices, GL_STATIC_DRAW);

    // Enable vertex attributes
    glEnableVertexAttribArray(0);
    glVertexAttribPointer(0, 2, GL_FLOAT, GL_FALSE, 0, nullptr);

    // Draw the square
    glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);

    // Clean up
    glDisableVertexAttribArray(0);
    glBindBuffer(GL_ARRAY_BUFFER, 0);
    glDeleteBuffers(1, &vbo);
}

void SnakeRenderer::drawSnake(const Snake& snake) {
    glColor4f(0.0f, 1.0f, 0.0f, 1.0f); // Green color for the snake
    for (const auto& segment : snake.getSegments()) {
        float x = segment.first * cellSize;
        float y = segment.second * cellSize;
        drawSquare(x, y, cellSize);
    }
}

void SnakeRenderer::drawFood(const Food& food) {
    glColor4f(1.0f, 0.0f, 0.0f, 1.0f); // Red color for food
    float x = food.getPosition().first * cellSize;
    float y = food.getPosition().second * cellSize;
    drawSquare(x, y, cellSize);
}

void SnakeRenderer::handleInput
