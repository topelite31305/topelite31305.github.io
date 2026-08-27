import pygame
import math

# pygame setup
pygame.init()
screen = pygame.display.set_mode((600, 600))
clock = pygame.time.Clock()
running = True
center = (300, 300)

def rot_det(angle, width, centerx, centery):
    return (width * math.cos(angle) + centerx, width * math.sin(angle) + centery)

# DARK SIDE setup
color_list = ["red", "orange", "yellow", "green", "blue", "purple"]
tri_rot = 0.5


while running:
    # poll for events
    # pygame.QUIT event means the user clicked X to close your window
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

    # fill the screen with a color to wipe away anything from last frame
    screen.fill("black")

    # RENDER YOUR GAME HERE
    # colors
    for i in range(6):
        pygame.draw.line(screen, color_list[i], (600,270 + 10 * i), (300, 190 + 4 * i), 9)
    # white light
    pygame.draw.line(screen, "white", (0, 300), (300, 200), 5)
    # triangle
    centerX = 300
    centerY = 250
    tri_sidelength = 200 * 2/3

    pointA = rot_det(tri_rot, tri_sidelength, centerX, centerY)
    pointB = rot_det(tri_rot + math.radians(120), tri_sidelength, centerX, centerY)
    pointC = rot_det(tri_rot + math.radians(240), tri_sidelength, centerX, centerY)
    tri_list = [pointA, pointB, pointC]
    pygame.draw.polygon(screen, "black", tri_list)
    pygame.draw.polygon(screen, "lightblue", tri_list, 4)
    tri_rot += 0.03

    
    # flip() the display to put your work on screen
    pygame.display.flip()

    clock.tick(60)  # limits FPS to 60

pygame.quit()
