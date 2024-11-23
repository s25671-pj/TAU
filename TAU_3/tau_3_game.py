import random

board_size = 5
obstacles_count = 5

def generate_board(size, obstacles_count):
    """
    Funkcja generująca planszę.
    start i end - punkty rozpoczynające i kończące grę; board gry domyślnie to 5x5 (board_size), start zawsze jest z lewej strony, end z prawej, wysokość jest losowana (indeks tablicy)
    start w grze oznacza "A"
    end w grze oznacza "B"
    Na podstawie liczby przeszkód (obstacles_count) umieszczane są na planszy przeszkody ("X") w losowych pustych miejscach.
    """
    board = [[" " for _ in range(size)] for _ in range(size)]

    start = (random.randint(0, size - 1), 0)
    end = (random.randint(0, size - 1), size - 1)

    board[start[0]][start[1]] = "A"
    board[end[0]][end[1]] = "B"

    placed_obstacles = 0
    while placed_obstacles < obstacles_count:
        x, y = random.randint(0, size - 1), random.randint(0, size - 1)
        if board[x][y] == " ":
            board[x][y] = "X"
            placed_obstacles += 1

    return board, start, end

def display_board(board, player_pos):
    """
    Wyświetla planszę
    Gracz to "O"
    """
    print("=========")
    for x in range(len(board)):
        for y in range(len(board[x])):
            if (x, y) == player_pos:
                print("O", end=" ")
            else:
                print(board[x][y], end=" ")
        print()
    print("=========")

def move_player(direction, player_pos, board):
    """
    Obsługa ruchów gracz - stary, dobry WASD
    Ruchy sprawdzają, czy ich wykonanie nie wiąże się z wejściem w przeszkodę
    """
    x, y = player_pos
    if direction == "w" and x > 0 and board[x - 1][y] != "X":
        x -= 1
    elif direction == "s" and x < len(board) - 1 and board[x + 1][y] != "X":
        x += 1
    elif direction == "a" and y > 0 and board[x][y - 1] != "X":
        y -= 1
    elif direction == "d" and y < len(board[0]) - 1 and board[x][y + 1] != "X":
        y += 1
    return (x, y)

def main():
    board, start_pos, end_pos = generate_board(board_size, obstacles_count)
    player_pos = start_pos

    print()
    print("Poruszanie: 'w' = w górę , 's' = w dół, 'a' = w lewo, 'd' = w prawo. Musisz dotrzeć do 'B' aby wygrać.")
    print("Legenda: 'O' - gracz, 'A' - start, 'B' - meta, 'X' - przeszkoda.")
    print()

    while True:
        display_board(board, player_pos)

        if player_pos == end_pos:
            print("Gratulacje! Wygrałeś!")
            break

        move = input("Twój ruch: ").lower()
        if move not in ["w", "s", "a", "d"]:
            print("Nieprawidłowy ruch! Spróbuj ponownie.")
            continue

        new_pos = move_player(move, player_pos, board)
        player_pos = new_pos

if __name__ == "__main__":
    main()