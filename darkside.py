import pygame
import random
import math

import pygame_widgets
from pygame_widgets.button import Button

# pygame setup
pygame.init()
window = pygame.display.set_mode((800, 600))
clock = pygame.time.Clock()
running = True
center = (300, 300)

def rot_det(angle, width, centerx, centery):
    return (width * math.cos(angle) + centerx, width * math.sin(angle) + centery)


def toggle_graphic(graphic):
    graphics[graphic] = True

    if graphics[graphic]:
        for name in graphics:
            if name != graphic:
                graphics[name] = False

    # print(graphics)

graphics = {
    "moon": False,
    "wall": False,
}

moonButton = Button(
    # Mandatory Parameters
    window,  # Surface to place button on
    625,  # X-coordinate of top left corner
    125,  # Y-coordinate of top left corner
    150,  # Width
    100,  # Height

    # Optional Parameters
    text= 
"""The Dark Side
 of the Moon""",  # Text to display
    fontSize=25,  # Size of font
    font = pygame.font.SysFont("gloucesterextracondensed", 25),
    margin=20,  # Minimum distance between text/image and edge of button
    inactiveColour=(0, 0, 0),  # Colour of button when not being interacted with
    hoverColour=(150, 0, 0),  # Colour of button when being hovered over
    pressedColour=(0, 200, 20),  # Colour of button when being clicked
    radius=0,  # Radius of border corners (leave empty for not curved)
    onClick=lambda: toggle_graphic("moon"),  # Function to call when clicked on
    textColour="white",
    textHAlign="centre",
    textVAlign ="centre"
)

wallButton = Button(
    window, 625, 250, 150, 100,
    text="The Wall",
    fontSize=25,
    onClick=lambda: toggle_graphic("wall"),
    font = pygame.font.SysFont("arial", 25)
)


# DARK SIDE setup
color_list = ["red", "orange", "yellow", "green", "blue", "purple"]
tri_rot = 0.5
moon = False


# WALL setup
brick_loc = 0
brick_speed = 1
emptybrick = []
emptybrick_timer = 0 # how many frames before the brick moves
emptybrick_timer_reset = 0 # how many frames between brick spawning and brick moving offscreen
# emptybrick code
for i in range(5):
   emptybrick.append((random.randint(-1, 5), random.randint(1, 11)))
emptybrick.sort()

while running:
    # poll for events
    # pygame.QUIT event means the user clicked X to close your window
    events = pygame.event.get()
    for event in events:
        if event.type == pygame.QUIT:
            running = False

    # fill the screen with a color to wipe away anything from last frame
    window.fill("black")

    # RENDER YOUR GAME HERE
    # sidebar
    # sidebar = pygame.rect(600,0,200,600)
   

    # DSOTM
    if graphics["moon"]:
        # draw moon graphics
        # colors
        for i in range(6):
            pygame.draw.line(window, color_list[i], (600,270 + 10 * i), (300, 190 + 4 * i), 9)

        # white light
        pygame.draw.line(window, "white", (0, 300), (300, 200), 5)

        # triangle
        centerX = 300
        centerY = 250
        tri_sidelength = 200 * 2/3

        pointA = rot_det(tri_rot, tri_sidelength, centerX, centerY)
        pointB = rot_det(tri_rot + math.radians(120), tri_sidelength, centerX, centerY)
        pointC = rot_det(tri_rot + math.radians(240), tri_sidelength, centerX, centerY)
        tri_list = [pointA, pointB, pointC]
        pygame.draw.polygon(window, "black", tri_list)
        pygame.draw.polygon(window, "lightblue", tri_list, 4)

        # change in rotation, radians per frame
        tri_rot += 0.01

    if graphics["wall"]:
        if emptybrick_timer_reset == 200:
            emptybrick.pop()
            emptybrick.insert(0, (-2, random.randint(1, 11)))
            emptybrick_timer_reset = 0
        if emptybrick_timer == 100:
            emptybrick = [(x + 1, y) for x, y in emptybrick]
            emptybrick_timer = 0

        for x in range(-1, 7):
            for y in range(12):
                if y % 2 == 0:
                    brick_list = [(x * 100 + brick_loc % 100, y * 50),((x+1)* 100 + brick_loc % 100, y * 50), ((x+1) * 100 + brick_loc % 100, (y+1)*50), (x * 100 + brick_loc % 100, (y+1)*50)]
                else:
                    brick_list = [(x * 100 - 50 + brick_loc % 100, y * 50), ((x+1)* 100 - 50 + brick_loc % 100, y * 50), ((x+1) * 100 - 50 + brick_loc % 100, (y+1)*50), (x * 100 - 50 + brick_loc % 100, (y+1)*50)]

               # uncomment emptybrick up top to use
                if (x,y) in emptybrick:
                    pygame.draw.polygon(window, "black", brick_list)
                else:
                    pygame.draw.polygon(window, "white", brick_list)
                    pygame.draw.polygon(window, "lightblue", brick_list, 2)

        wall_font = pygame.font.SysFont("timesnewroman", 80)

        wall_lines = ["PINK", "FLOYD", "THE", "WALL"]
        line_height = wall_font.get_linesize() * 1.3
        total_height = line_height * len(wall_lines)
        start_y = (600 - total_height) // 2

        for line_number, text in enumerate(wall_lines):
            text_surface = wall_font.render(text, True, "red")
            text_rect = text_surface.get_rect(
                center=(300, start_y + line_number * line_height + line_height // 2)
            )
            window.blit(text_surface, text_rect)

        emptybrick_timer_reset += 1
        emptybrick_timer += 1
        brick_loc += brick_speed

    # sidebar
    pygame.draw.rect(window,"white",pygame.Rect(600,0,200,600))
    
    # flip() the display to put your work on screen
    pygame_widgets.update(events)
    pygame.display.flip()

    clock.tick(120)  # limits FPS to 120

pygame.quit()
